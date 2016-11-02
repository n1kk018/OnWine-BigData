package fr.afcepf.atod.onwine.bigdata.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * an Order
 */
@Document(collection = "order")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1192893252389460956L;
	@Id
	private String id;
	@Field("created_at")
	private Date createdAt;
	@Field("paid_at")
	private Date paidAt;
	@Field("shipping_fees")
    private Double shippingFees;
    private Double taxes;
    private String currency;
	private Customer customer;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	// ------------ Constructors ----------------//

	/**
	 * Default constructor
	 */
	public Order() {
	}
	
	

	
	public Order(Date paramCreatedAt, Date paramPaidAt, Double paramShippingFees, Double paramTaxes,
            String paramCurrency, Customer paramCustomer) {
        super();
        createdAt = paramCreatedAt;
        paidAt = paramPaidAt;
        shippingFees = paramShippingFees;
        taxes = paramTaxes;
        currency = paramCurrency;
        customer = paramCustomer;
    }


    // ----------- Getters && Setters -----------//
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(Date paidAt) {
		this.paidAt = paidAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> paramOrderDetails) {
        orderDetails = paramOrderDetails;
    }

    public void addOrderDetail(OrderDetail paramOrderDetail) {
        orderDetails.add(paramOrderDetail);
    }
	
	public String getCurrency() {
        return currency;
    }

    public void setCurrency(String paramCurrency) {
        currency = paramCurrency;
    }


    public Double getShippingFees() {
        return shippingFees;
    }


    public void setShippingFees(Double paramShippingFees) {
        shippingFees = paramShippingFees;
    }


    public Double getTaxes() {
        return taxes;
    }


    public void setTaxes(Double paramTaxes) {
        taxes = paramTaxes;
    }


    @Override
    public String toString() {
        return "Order [id=" + id + ", createdAt=" + createdAt + ", paidAt=" + paidAt + ", shippingFees=" + shippingFees
                + ", taxes=" + taxes + ", currency=" + currency + ", customer=" + customer + ", orderDetails="
                + orderDetails + "]";
    }
}