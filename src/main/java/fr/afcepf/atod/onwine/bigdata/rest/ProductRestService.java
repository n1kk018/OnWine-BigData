package fr.afcepf.atod.onwine.bigdata.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.mahout.api.ItemRecommenderService;

@RestController
@RequestMapping(value="/product", produces="application/json")
public class ProductRestService {
    @Autowired
    private ItemRecommenderService service;
    
    @RequestMapping(value="/recommender",method=RequestMethod.GET)
    public List<Product> getWinesRecommandations(@RequestParam(value="id") Integer id) {
        List<Product> list = new ArrayList<Product>();
        try {
            list = service.getRecommendedItemsByOrdersSimilarity(id);
        } catch (Exception paramE) {
            // TODO Auto-generated catch block
            paramE.printStackTrace();
        }
        return list;
    }
}
