package fr.afcepf.atod.onwine.bigdata.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * A product
 */
@Document(collection = "product")
public class Product implements Serializable {
	private static final long serialVersionUID = -1084257720250486898L;
	
	@Id
	private String id;
	private String name;
	private String appellation;
	private Double price;
	@Field("product_type")
    private String productType;
	@Field("product_vintage")
    private String productVintage;
	@Field("product_varietal")
    private String productVarietal;

	// -------- Constructors ------------ //

	/**
	 * Default constructor
	 */
	public Product() {
	}

    public Product(String paramName, Double paramPrice, String paramAppellation,
            String paramProductType, String paramProductVintage, String paramProductVarietal) {
        super();
        name = paramName;
        price = paramPrice;
        appellation = paramAppellation;
        productType = paramProductType;
        productVintage = paramProductVintage;
        productVarietal = paramProductVarietal;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String paramId) {
        id = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String paramName) {
        name = paramName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double paramPrice) {
        price = paramPrice;
    }

    
    public String getProductType() {
        return productType;
    }

    public void setProductType(String paramProductType) {
        productType = paramProductType;
    }

    public String getProductVintage() {
        return productVintage;
    }

    public void setProductVintage(String paramProductVintage) {
        productVintage = paramProductVintage;
    }

    public String getProductVarietal() {
        return productVarietal;
    }

    public void setProductVarietal(String paramProductVarietal) {
        productVarietal = paramProductVarietal;
    }
    
    

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String paramAppellation) {
        appellation = paramAppellation;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", appellation=" + appellation + ", price=" + price
                + ", productType=" + productType + ", productVintage=" + productVintage + ", productVarietal="
                + productVarietal + "]";
    }
}