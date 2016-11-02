package fr.afcepf.atod.onwine.bigdata.dto;

public class ChartRowVal {
    private Object v;
    private String f;
    
    public ChartRowVal() {
        super();
    }
    
    public ChartRowVal(Object paramV) {
        super();
        v = paramV;
    }

    @Override
    public String toString() {
        return "ChartRowVal [v=" + v + ", f=" + f + "]";
    }

    public Object getV() {
        return v;
    }

    public void setV(Object paramV) {
        v = paramV;
    }

    public String getF() {
        return f;
    }

    public void setF(String paramF) {
        f = paramF;
    }
    
    
}
