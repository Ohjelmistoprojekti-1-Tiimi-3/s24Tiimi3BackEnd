package s24.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;
    private String status;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    public Order() {
    }

    public Order(String status, Integer amount, Customer customer, Product product) {
        this.status = status;
        this.amount = amount;
        this.customer = customer;
        this.product = product;
    }



    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order [orderid=" + orderid + ", status=" + status + ", amount=" + amount + ", customer=" + customer
                + ", product=" + product + "]";
    }

}
