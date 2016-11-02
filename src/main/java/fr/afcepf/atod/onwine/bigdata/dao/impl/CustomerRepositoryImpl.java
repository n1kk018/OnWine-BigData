package fr.afcepf.atod.onwine.bigdata.dao.impl;

import java.util.Random;

import javax.inject.Inject;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import fr.afcepf.atod.onwine.bigdata.dao.api.CustomerRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.domain.Customer;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    private final MongoTemplate mongoTemplate;
    
    @Inject
    public CustomerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public Customer getRandomCustomer(int nb_order) {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
            Aggregation.limit((new Random().nextInt(((nb_order*70)/100)-1))+1),
            Aggregation.sort(Sort.Direction.DESC,"id"),
            Aggregation.limit(1)),
            Customer.class,Customer.class).getUniqueMappedResult();
    }

}
