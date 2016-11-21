package fr.afcepf.atod.onwine.bigdata.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import fr.afcepf.atod.onwine.bigdata.domain.Order;
import fr.afcepf.atod.onwine.bigdata.domain.OrderDetail;
import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.dto.ChartRowVal;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;
import fr.afcepf.atod.onwine.bigdata.mahout.api.ItemRecommenderService;

@ComponentScan("fr.afcepf.atod.onwine.bigdata")
public class QueryTester implements CommandLineRunner {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ItemRecommenderService service;
    
    /*public static void main(String[] args) {
        SpringApplication.run(QueryTester.class, args);
    }*/
    
    @Override
    public void run(String... paramArg0) throws Exception {
        List<Product> list = service.getRecommendedItemsByOrdersSimilarity(50);
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
