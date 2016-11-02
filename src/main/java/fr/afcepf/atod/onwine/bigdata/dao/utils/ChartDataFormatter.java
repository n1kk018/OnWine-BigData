package fr.afcepf.atod.onwine.bigdata.dao.utils;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.atod.onwine.bigdata.dto.ChartCol;
import fr.afcepf.atod.onwine.bigdata.dto.ChartFormatter;
import fr.afcepf.atod.onwine.bigdata.dto.ChartRow;
import fr.afcepf.atod.onwine.bigdata.dto.ChartRowVal;
import fr.afcepf.atod.onwine.bigdata.dto.KeyValDTO;

public final class ChartDataFormatter {
    public static ChartFormatter twoColsChart(List<KeyValDTO> list, String label1, String label2) {
        List<ChartRow> rows = new ArrayList<ChartRow>();
        for (int i = 0; i < list.size(); i++) {
            ChartRow row = new ChartRow();
            row.setC(new ArrayList<ChartRowVal>());
            row.getC().add(new ChartRowVal(list.get(i).getKey()));
            row.getC().add(new ChartRowVal(list.get(i).getVal()));
            rows.add(row);
        }
        List<ChartCol> cols = new ArrayList<ChartCol>();
        cols.add(new ChartCol("", label1 , "string"));
        cols.add(new ChartCol("",label2, "number"));
        return new ChartFormatter(cols,rows);
    }
}
