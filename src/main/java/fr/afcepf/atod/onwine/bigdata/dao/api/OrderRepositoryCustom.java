package fr.afcepf.atod.onwine.bigdata.dao.api;

import java.util.List;

import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public interface OrderRepositoryCustom {
    List<KeyValDTO> aggregateOrderByCurrency();
    ChartFormatter getCurrencieschartData();
    List<KeyValDTO> aggregateOrderByCountry();
    ChartFormatter getCountrieschartData();
    List<KeyValDTO> aggregateByTypes();
    ChartFormatter getTypesChartData();
    List<KeyValDTO> aggregateTopProducts();
    ChartFormatter topProductsChartData();
}
