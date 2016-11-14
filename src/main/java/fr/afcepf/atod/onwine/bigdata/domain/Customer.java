package fr.afcepf.atod.onwine.bigdata.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * a Customer
 */
@Document(collection = "customer")
public class Customer implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 708890365167036937L;
	
	@Id
    private String id;
	@Field("jpa_id")
	private Long jpaId;
	private  String civility;
    private  String lastname;
    private  String firstname;
    @Field("created_at")
    private  Date createdAt;
    private List<Adress> adresses = new ArrayList<Adress>();
    
    public Customer() {
        
    }
    
    
    // ------------ Getters && setters --------------//

    public Customer(Long paramJpaId, String paramCivility, String paramLastname,
            String paramFirstname, Date paramCreatedAt) {
        super();
        jpaId = paramJpaId;
        civility = paramCivility;
        lastname = paramLastname;
        firstname = paramFirstname;
        createdAt = paramCreatedAt;
    }
    
    
    

    public Date getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(Date paramCreatedAt) {
        createdAt = paramCreatedAt;
    }


    public String getCivility() {
        return civility;
    }


    public void setCivility(String paramCivility) {
        civility = paramCivility;
    }


    public String getLastname() {
        return lastname;
    }


    public void setLastname(String paramLastname) {
        lastname = paramLastname;
    }


    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String paramFirstname) {
        firstname = paramFirstname;
    }

    public String getId() {
        return id;
    }


    public void setId(String paramId) {
        id = paramId;
    }


    public List<Adress> getAdresses() {
        return adresses;
    }


    public void setAdresses(List<Adress> paramAdresses) {
        adresses = paramAdresses;
    }
    
    public void addAdress(Adress paramAdress) {
        adresses.add(paramAdress);
    }
    
    
    
    public Long getJpaId() {
        return jpaId;
    }


    public void setJpaId(Long paramJpaId) {
        jpaId = paramJpaId;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", jpaId=" + jpaId + ", civility=" + civility + ", lastname=" + lastname + ", firstname=" + firstname
                + ", createdAt=" + createdAt  + "]";
    }        
}