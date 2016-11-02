package fr.afcepf.atod.onwine.bigdata.dto;

import java.util.List;

public class ChartFormatter {
    private List<ChartCol> cols;
    private List<ChartRow> rows;
    
    
    
    public ChartFormatter() {
        super();
    }



    public ChartFormatter(List<ChartCol> paramCols, List<ChartRow> paramRows) {
        super();
        cols = paramCols;
        rows = paramRows;
    }



    public List<ChartCol> getCols() {
        return cols;
    }



    public void setCols(List<ChartCol> paramCols) {
        cols = paramCols;
    }



    public List<ChartRow> getRows() {
        return rows;
    }



    public void setRows(List<ChartRow> paramRows) {
        rows = paramRows;
    }
    
    
    
}
