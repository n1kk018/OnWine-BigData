package fr.afcepf.atod.onwine.bigdata.repository;

import java.util.List;

import javax.inject.Inject;

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
import fr.afcepf.atod.onwine.bigdata.dto.ChartRowVal;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

@EnableAutoConfiguration
@ComponentScan("fr.afcepf.atod.onwine.bigdata")
public class QueryTester implements CommandLineRunner {
    @Inject
    private MongoTemplate mongoTemplate;
    @Inject
    private ProductRepository productRepo;
    @Inject
    private OrderRepository orderRepo;
    @Inject
    private CountryRepository countryRepo;
    @Inject
    private CustomerRepository customerRepo;
    
    /*public static void main(String[] args) {
        SpringApplication.run(QueryTester.class, args);
    }*/
    
    @Override
    public void run(String... paramArg0) throws Exception {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.unwind("orderDetails"),
                Aggregation.group("orderDetails.productOrdered.name")
                .last("orderDetails.productOrdered.name").as("key")
                .sum("orderDetails.quantite").as("val"),
                Aggregation.sort(Sort.Direction.DESC,"val"),
                Aggregation.limit(10)
        );
        //Convert the aggregation result into a List
        AggregationResults<KeyValDTO> test
                = mongoTemplate.aggregate(agg, Order.class, KeyValDTO.class);
        //ChartRowVal result = groupResults.getUniqueMappedResult();
        System.out.println(test.getMappedResults());
    }

}
