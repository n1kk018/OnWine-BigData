/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.onwine.bigdata.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author ronan
 */
@Document(collection = "order_detail")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = -4233672529081477853L;
    
	@Id
    private String Id;
    private int quantite;
    @Field("converted_unit_price")
    private Double convertedUnitPrice;
    @Field("product_ordered")
    private Product productOrdered;
    
    // ------ Constructors ------- //

    public OrderDetail() {
    }
    
    public OrderDetail(int paramQuantite, Double paramConvertedUnitPrice,
            Product paramProductOrdered) {
        super();
        quantite = paramQuantite;
        convertedUnitPrice = paramConvertedUnitPrice;
        productOrdered = paramProductOrdered;
    }
    
    
    // ------ Getters && Setters ------//

   
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Product getProductOrdered() {
        return productOrdered;
    }

    public void setProductOrdered(Product product) {
        this.productOrdered = product;
    }

    public Double getConvertedUnitPrice() {
        return convertedUnitPrice;
    }

    public void setConvertedUnitPrice(Double paramConvertedUnitPrice) {
        convertedUnitPrice = paramConvertedUnitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail [Id=" + Id + ", quantite=" + quantite +  ", convertedUnitPrice="
                + convertedUnitPrice + ", productOrdered=" + productOrdered + "]";
    }  
}
