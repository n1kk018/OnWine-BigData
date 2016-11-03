package fr.afcepf.atod.onwine.bigdata.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;
import fr.afcepf.atod.onwine.bigdata.repository.ProductRepository;

@RestController
@RequestMapping(value="/products", produces="application/json")
public class ProductMetrics {
    
    @Autowired
    private ProductRepository productRepository; 
    
    @RequestMapping(value="/types", method=RequestMethod.GET)
    public List<KeyValDTO> typesAggregate() {
        return productRepository.aggregateByTypes();
    }
    
    @RequestMapping(value="/typesChartData", method=RequestMethod.GET)
    public ChartFormatter typesChartData() {
        return productRepository.getTypesChartData();
    }
}
