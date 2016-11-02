package fr.afcepf.atod.onwine.bigdata.dto;

/**
 * @author nikko
 *
 */
public class ChartCol {
    private String id;
    private String label;
    private String type;
    
    
    public ChartCol() {
        super();
    }

    
    public ChartCol(String paramId, String paramLabel, String paramType) {
        super();
        id = paramId;
        label = paramLabel;
        type = paramType;
    }


    public String getId() {
        return id;
    }


    public void setId(String paramId) {
        id = paramId;
    }


    public String getLabel() {
        return label;
    }


    public void setLabel(String paramLabel) {
        label = paramLabel;
    }


    public String getType() {
        return type;
    }


    public void setType(String paramType) {
        type = paramType;
    }
    
    
}
