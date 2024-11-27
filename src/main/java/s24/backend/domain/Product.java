package s24.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productid;

    @NotEmpty(message = "Product name has to be given")
    private String productname;
    @PositiveOrZero(message = "Price has to be positive or zero")
    private Float price;
    private String color;
    private String info;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "manufacturerid")
    @JsonIgnoreProperties({"manufacturerid", "products", "manufacturerinfo"})
    private Manufacturer manufacturer;


    @ManyToOne
    @JoinColumn(name = "typeid")
    @JsonIgnoreProperties({"typeid", "products"})
    private Type type;

    @ManyToOne
    @JoinColumn(name = "sizeid")
    @JsonIgnoreProperties({ "sizeid", "products" })
    private Size size;


/*
 * @ManyToMany
 * 
 * @JoinTable(name = "product_type", joinColumns = @JoinColumn(name =
 * "productid"), inverseJoinColumns = @JoinColumn(name = "typeid"))
 * 
 * @JsonIgnoreProperties({"typeid", "products"})
 * private Set<Type> type;
 * 
 * @ManyToMany
 * 
 * @JoinTable(name = "product_size", joinColumns = @JoinColumn(name =
 * "productid"), inverseJoinColumns = @JoinColumn(name = "sizeid"))
 * 
 * @JsonIgnoreProperties({"sizeid", "products"})
 * private Set<Size> size;
 */

    public Product(String productname, Float price, String color, String info,
            Manufacturer manufacturer, Type type, Size size, Integer quantity) {
        this.productname = productname;
        this.price = price;
        this.color = color;
        this.info = info;
        this.manufacturer = manufacturer;
        this.type = type;
        this.size = size;
        this.quantity = quantity;
    }

    public Product() {
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(long productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "product id = " + productid + ", product name = " + productname + ", price = " + price + ", color = "
                + color + ", info = " + info + ", manufacturer = " + manufacturer + ", type = " + type + ", size = "
                + size;
    }

}
