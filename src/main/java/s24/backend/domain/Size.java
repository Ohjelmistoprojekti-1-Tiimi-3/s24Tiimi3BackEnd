package s24.backend.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sizeid;
    private String size;

    @ManyToMany(mappedBy = "size")
    @JsonIgnoreProperties("size")
    private Set<Product> products;

    public Size(String size) {
        this.size = size;
    }

    public Size() {
    }

    public Long getSizeid() {
        return sizeid;
    }

    public void setSizeid(Long sizeid) {
        this.sizeid = sizeid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "size id = " + sizeid + ", size = " + size;
    }

}
