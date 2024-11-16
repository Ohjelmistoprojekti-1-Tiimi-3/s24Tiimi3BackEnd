package s24.backend.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerid;

    @Column(unique=true)
    private String manufacturername;
    
    private String manufacturerinfo;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    private Set<Product> products;

    public Manufacturer(String manufacturername, String manufacturerinfo) {
        this.manufacturername = manufacturername;
        this.manufacturerinfo = manufacturerinfo;
    }

    public Manufacturer() {
    }

    public Long getManufacturerid() {
        return manufacturerid;
    }

    public void setManufacturerid(Long manufacturerid) {
        this.manufacturerid = manufacturerid;
    }

    public String getManufacturername() {
        return manufacturername;
    }

    public void setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername;
    }

    public String getManufacturerinfo() {
        return manufacturerinfo;
    }

    public void setManufacturerinfo(String manufacturerinfo) {
        this.manufacturerinfo = manufacturerinfo;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "manufacturer id = " + manufacturerid + ", manufacturer name = " + manufacturername
                + ", manufacturer info =" + manufacturerinfo;
    }

}
