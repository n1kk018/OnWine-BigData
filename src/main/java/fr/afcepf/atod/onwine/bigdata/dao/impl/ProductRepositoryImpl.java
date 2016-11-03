package fr.afcepf.atod.onwine.bigdata.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import fr.afcepf.atod.onwine.bigdata.dao.api.ProductRepositoryCustom;
import fr.afcepf.atod.onwine.bigdata.dao.utils.ChartDataFormatter;
import fr.afcepf.atod.onwine.bigdata.domain.Product;
import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
    public List<KeyValDTO> aggregateByTypes() {
        return mongoTemplate.aggregate(Aggregation.newAggregation(
                Aggregation.group("productType")
                .last("productType").as("key")
                .count().as("val"),
                Aggregation.sort(Sort.Direction.ASC, "key")
        ), Product.class,KeyValDTO.class).getMappedResults();
    }
    
    @Override
    public ChartFormatter getTypesChartData() {
        List<KeyValDTO> list = aggregateByTypes();
        return ChartDataFormatter.twoColsChart(list, "Types de vins", "Nombre de références");
    }

}
