package fr.afcepf.atod.onwine.bigdata.dto;

public class KeyValDTO {
    private Object val;
    private String key;
    
    public KeyValDTO() {
        super();
    }

    public KeyValDTO(Object paramVal, String paramKey) {
        super();
        val = paramVal;
        key = paramKey;
    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object paramVal) {
        val = paramVal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String paramKey) {
        key = paramKey;
    }

    @Override
    public String toString() {
        return "KeyValDTO [val=" + val + ", key=" + key + "]";
    }
    
    

   
    
}
