package s24.backend.web;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import org.springframework.web.bind.annotation.RequestParam;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
>>>>>>> 389320cee8b9829e6b99230d7f7bfb8fee3ece09

@Controller
public class ProductController {

<<<<<<< HEAD


=======
    @Autowired
    private ProductRepository productrepo;

    @RequestMapping(value="/productList", method=RequestMethod.GET)
    public String showProducts(Model model) {

        model.addAttribute("products", productrepo.findAll());
        return "productList";
    }
    

    @RequestMapping(value="addProduct", method=RequestMethod.GET)
    public String addProduct(Model model) {
        
        model.addAttribute("product", new Product());
        return "redirect:productList";
    }

    @RequestMapping(value="/saveProduct", method=RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        
        productrepo.save(product);
        return "redirect:productList";
    }

     // Poistetaan tuote
     @GetMapping("delete/{id}")
     public String deleteProduct(@PathVariable("id") Long id, Model model) {
         productrepo.deleteById(id);
         return "redirect:productlist";
     }
     
    
    
>>>>>>> 389320cee8b9829e6b99230d7f7bfb8fee3ece09
}
