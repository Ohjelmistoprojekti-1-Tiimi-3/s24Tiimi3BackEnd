package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s24.backend.domain.Order;
import s24.backend.domain.OrderRepository;

@RestController
@RequestMapping("/api")
public class RestOrderController {

    @Autowired
    private OrderRepository orderrepo;

    //Get all orders
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/orders")
    public Iterable<Order> getOrders() {
        
        return orderrepo.findAll();
    }

    // Adding a new order
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addorder")
    public Order newOrder(@RequestBody Order newOrder) {
        
        return orderrepo.save(newOrder);
    }

    // Deleting an order
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteorder/{id}")
    public Iterable<Order> deleteOrder(@PathVariable("id") Long orderid) {

        orderrepo.deleteById(orderid);
        return orderrepo.findAll();
    }

    // Editing an order
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/order/{id}")
	Order editOrder(@RequestBody Order editOrder, @PathVariable("id") Long orderid) {
		
        editOrder.setOrderid(orderid);
		return orderrepo.save(editOrder);
	}

}
