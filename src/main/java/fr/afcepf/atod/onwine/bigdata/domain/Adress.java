package fr.afcepf.atod.onwine.bigdata.domain;

import java.io.Serializable;
import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * an Adress
 */
@Document(collection = "adress")
public class Adress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -172263256055543724L;
	@Id
	private String id;
	private String street;
	private Integer number;
    private String zipcode;
    private String city;
    private Country country;	
	private boolean billing;
    //private Customer customer;
	

	// ---------- constructors ----------- //

	/**
	 * Default constructor
	 */
	public Adress() {
	}

	/**
	 * constructeur surcharge.
	 * @param id
	 * @param street
	 * @param number
	 * @param billing
	 * @param city
	 */
	public Adress(String street, Integer number, String zipcode, String city, Country country, boolean billing) {
		this.street = street;
		this.number = number;
		this.zipcode = zipcode;
		this.city = city;
		this.country = country;
		this.billing = billing;
	}

	// ------------ getters && setters -------- //

	

	public String getStreet() {
		return street;
	}

	public String getId() {
        return id;
    }

    public void setId(String paramId) {
        id = paramId;
    }

    public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean isBilling() {
		return billing;
	}

	public void setBilling(boolean billing) {
		this.billing = billing;
	}

	public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer paramCustomer) {
        customer = paramCustomer;
    }*/

    @Override
    public String toString() {
        return "Adress [id=" + id + ", street=" + street + ", number=" + number + ", zipcode=" + zipcode + ", city="
                + city + ", country=" + country + ", billing=" + billing + "]";
    }
}