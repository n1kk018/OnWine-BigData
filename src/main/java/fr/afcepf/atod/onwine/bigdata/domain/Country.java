package fr.afcepf.atod.onwine.bigdata.domain;
import java.io.Serializable;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * by roro
 */
@Document(collection = "country")
public class Country implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -473519643276156347L;
    @Id
    private String id;
    private String code;
    private String currency;
    private String name;
    
    // ------- Constructors -------//
    
    /**
     * Default constructor
     */
    public Country() {
    }

    public Country(String code, String currency, String name) {
        this.code = code;
        this.currency = currency;
        this.name = name;
    }
    
    // ------ Getters && Setters --------- //
    

    
    

    
    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String paramCurrency) {
        currency = paramCurrency;
    }

    public String getId() {
        return id;
    }

    public void setId(String paramId) {
        id = paramId;
    }

    public void setCode(String paramCode) {
        code = paramCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [id=" + id + ", code=" + code + ", currency=" + currency + ", name=" + name + "]";
    }

    
    
    
}