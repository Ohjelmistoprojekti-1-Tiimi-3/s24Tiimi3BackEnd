package s24.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long customerid;
    private String customername;
    private String customerlastname;
    private String customeremail;

    @ManyToOne
    private AppUser appUser;

    public Customer(String customername, String customerlastname, String customeremail) {
        this.customername = customername;
        this.customerlastname = customerlastname;
        this.customeremail = customeremail;
    }

    public Customer(){
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerlastname() {
        return customerlastname;
    }

    public void setCustomerlastname(String customerlastname) {
        this.customerlastname = customerlastname;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }

    @Override
    public String toString() {
        return "customerid = " + customerid + ", customername = " + customername + ", customerlastname = "
                + customerlastname + ", customeremail = " + customeremail;
    }

    

    
}
