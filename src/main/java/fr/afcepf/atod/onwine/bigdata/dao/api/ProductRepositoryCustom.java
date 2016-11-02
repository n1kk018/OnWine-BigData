package fr.afcepf.atod.onwine.bigdata.dao.api;

import java.util.List;
import java.util.Map;

import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public interface ProductRepositoryCustom {
    List<KeyValDTO> aggregateByTypes();

    ChartFormatter getTypesChartData();
}
