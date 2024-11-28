package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import s24.backend.domain.Customer;
import s24.backend.domain.CustomerRepository;
import s24.backend.domain.OrderRepository;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderrepo;

    @Autowired
    private CustomerRepository customerrepo;

    @GetMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("orders", orderrepo.findAll());
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String showCustomerOrders(@PathVariable("id") Long id,Model model) {
        Customer c = customerrepo.findById(id).get();
        model.addAttribute("orders", orderrepo.findByCustomer(c));
        return "customerorder";
    }
}
