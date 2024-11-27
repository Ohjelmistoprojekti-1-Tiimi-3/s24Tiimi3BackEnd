package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import s24.backend.domain.Customer;
import s24.backend.domain.CustomerRepository;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerrepo;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/customerList", method = RequestMethod.GET)
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerrepo.findAll());
        return "customerList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customers", customerrepo.findAll());
        return "addCustomer";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerrepo.findAll());
			return "addCustomer";
		}
        customerrepo.save(customer);
        return "redirect:customerList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        customerrepo.deleteById(id);
        return "redirect:/customerList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("customer/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editcustomer", customerrepo.findById(id));
        return "editCustomer";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/saveEditedCustomer", method = RequestMethod.POST)
    public String saveEditedCustomer(@Valid @ModelAttribute("editcustomer") Customer customer, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerrepo.findAll());
			return "editCustomer";
		}
        customerrepo.save(customer);
        return "redirect:customerList";
    }

}