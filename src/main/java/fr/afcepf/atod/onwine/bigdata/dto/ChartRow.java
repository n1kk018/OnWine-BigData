package fr.afcepf.atod.onwine.bigdata.dto;

import java.util.List;

public class ChartRow {
    private List<ChartRowVal> c;
    
    

    public ChartRow() {
        super();
    }

    public ChartRow(List<ChartRowVal> paramC) {
        super();
        c = paramC;
    }

    public List<ChartRowVal> getC() {
        return c;
    }

    public void setC(List<ChartRowVal> paramC) {
        c = paramC;
    }
    
    
}
