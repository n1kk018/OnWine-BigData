package fr.afcepf.atod.onwine.bigdata.mahout.api;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

import fr.afcepf.atod.onwine.bigdata.domain.Customer;
import fr.afcepf.atod.onwine.bigdata.domain.Product;




public interface ItemRecommenderService {

	/**
	 * Returns a list of recommended items for an item
	 * @param shopper
	 * @return
	 * @throws TasteException 
	 * @throws Exception 
	 */
    List<Product> getRecommendedItemsByOrdersSimilarity(Integer paramId) throws Exception;

}
