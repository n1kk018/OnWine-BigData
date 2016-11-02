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
import fr.afcepf.atod.onwine.bigdata.repository.OrderRepository;

@Component
@RestController
@RequestMapping(value="/orders", produces="application/json")
@CrossOrigin
public class OrderMetrics {
    @Autowired
    private OrderRepository orderRepository; 
    
    @RequestMapping(value="/currencies",method=RequestMethod.GET)
    public List<KeyValDTO> currencyAggregate() {
        return orderRepository.aggregateOrderByCurrency();
    }
    
    @RequestMapping(value="/currenciesChartData", method=RequestMethod.GET)
    public ChartFormatter currenciesChartData() {
        return orderRepository.getCurrencieschartData();
    }
    
    @RequestMapping(value="/countries", method=RequestMethod.GET)
    public List<KeyValDTO> countryAggregate() {
        return orderRepository.aggregateOrderByCountry();
    }
    
    @RequestMapping(value="/countriesChartData", method=RequestMethod.GET)
    public ChartFormatter countriesChartData() {
        return orderRepository.getCountrieschartData();
    }
    
    @RequestMapping(value="/types", method=RequestMethod.GET)
    public List<KeyValDTO> typesAggregate() {
        return orderRepository.aggregateByTypes();
    }
    
    @RequestMapping(value="/typesChartData", method=RequestMethod.GET)
    public ChartFormatter typesChartData() {
        return orderRepository.getTypesChartData();
    }
    
    @RequestMapping(value="/topProducts", method=RequestMethod.GET)
    public List<KeyValDTO> topProductsAggregate() {
        return orderRepository.aggregateTopProducts();
    }

    @RequestMapping(value="/topProductsChartData", method=RequestMethod.GET)
    public ChartFormatter topProductsChartData() {
        return orderRepository.topProductsChartData();
    }
    
}
