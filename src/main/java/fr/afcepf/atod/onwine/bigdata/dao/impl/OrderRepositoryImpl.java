package fr.afcepf.atod.onwine.bigdata.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import fr.afcepf.atod.onwine.bigdata.dao.api.OrderRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.dao.utils.ChartDataFormatter;
import fr.afcepf.atod.onwine.bigdata.domain.Order;
import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public OrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<KeyValDTO> aggregateOrderByCurrency() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.match(Criteria.where("currency").ne("EUR")),
                Aggregation.group("currency")
                .last("currency").as("key")
                .count().as("val"),
                Aggregation.sort(Sort.Direction.DESC,"val")
        ), Order.class,KeyValDTO.class).getMappedResults();
    }

    @Override
    public ChartFormatter getCurrencieschartData() {
        List<KeyValDTO> list = aggregateOrderByCurrency();
        return ChartDataFormatter.twoColsChart(list, "Devises utilisées pour les paiements", "Nombre de commandes");
    }

    @Override
    public List<KeyValDTO> aggregateOrderByCountry() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("customer.adresses"),
                Aggregation.match(Criteria.where("customer.adresses.billing").in(true)),
                Aggregation.group("customer.adresses.country.code")
                .last("customer.adresses.country.code").as("key")
                .count().as("val")
        ), Order.class,KeyValDTO.class).getMappedResults();
        
    }

    @Override
    public ChartFormatter getCountrieschartData() {
        List<KeyValDTO> list = aggregateOrderByCountry();
        return ChartDataFormatter.twoColsChart(list, "Pays", "Nombre de commandes");
    }

    @Override
    public List<KeyValDTO> aggregateByTypes() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("orderDetails"),
                Aggregation.group("orderDetails.productOrdered.productType")
                .last("orderDetails.productOrdered.productType").as("key")
                .sum("orderDetails.quantite").as("val")
        ), Order.class,KeyValDTO.class).getMappedResults();
    }

    @Override
    public ChartFormatter getTypesChartData() {
        List<KeyValDTO> list = aggregateByTypes();
        return ChartDataFormatter.twoColsChart(list, "Types de vins", "Nombre de ventes");
    }

    @Override
    public List<KeyValDTO> aggregateTopProducts() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("orderDetails"),
                Aggregation.group("orderDetails.productOrdered.name")
                .last("orderDetails.productOrdered.name").as("key")
                .sum("orderDetails.quantite").as("val"),
                Aggregation.sort(Sort.Direction.DESC,"val"),
                Aggregation.limit(10)
        ), Order.class,KeyValDTO.class).getMappedResults();
    }

    @Override
    public ChartFormatter topProductsChartData() {
        List<KeyValDTO> list = aggregateTopProducts();
        return ChartDataFormatter.twoColsChart(list, "Nom", "Nombre de ventes");
    }

}
