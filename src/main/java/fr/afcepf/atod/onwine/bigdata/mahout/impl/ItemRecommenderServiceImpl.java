package fr.afcepf.atod.onwine.bigdata.mahout.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afcepf.atod.onwine.bigdata.domain.Customer;
import fr.afcepf.atod.onwine.bigdata.domain.Order;
import fr.afcepf.atod.onwine.bigdata.domain.OrderDetail;
import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.mahout.api.ItemRecommenderService;
import fr.afcepf.atod.onwine.bigdata.repository.CustomerRepository;
import fr.afcepf.atod.onwine.bigdata.repository.OrderRepository;
import fr.afcepf.atod.onwine.bigdata.repository.ProductRepository;

@Service
public class ItemRecommenderServiceImpl implements ItemRecommenderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    
	@Override
	public List<Product> getRecommendedItemsByOrdersSimilarity(Integer id) throws Exception {

		List<Product> products = new ArrayList<Product>();

		DataModel model = new GenericDataModel(getUserData());

		TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(model);

		GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, sim);
		
        for(LongPrimitiveIterator items = model.getItemIDs(); items.hasNext();) {
            long itemId = items.nextLong();
            List<RecommendedItem>recommendations = recommender.mostSimilarItems(itemId, 5);
            
            for(RecommendedItem recommendation : recommendations) {
                if(itemId==id) {
                    products.add(productRepository.findOneByJpaId(recommendation.getItemID()));
                }
                //System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
            }
        }
		
		return products;
	}

	/**
	 * Returns user data for recommendations
	 * NOTE: This should not be used as production code.
	 * @return
	 */
	private FastByIDMap<PreferenceArray> getUserData() {
		FastByIDMap<PreferenceArray> preferences = new FastByIDMap<PreferenceArray>();

		for (Customer shopper : customerRepository.findAll()) {
		    PreferenceArray userPreferences = new GenericUserPreferenceArray(20);
			int i = 0;
			for (Order order : orderRepository.findByCustomerJpaId(shopper.getJpaId())) {
			    for (OrderDetail ligneComm : order.getOrderDetails()) {
			        Product purchasedItem = ligneComm.getProductOrdered();
					userPreferences.setUserID(i, shopper.getJpaId());
					userPreferences.setItemID(i, purchasedItem.getJpaId());
					userPreferences.setValue(i, 1); // set the value to 1, assuming order is not considered
					i++;
			    }
			}
			preferences.put(shopper.getJpaId(), userPreferences);
		}

		return preferences;
	}

}
