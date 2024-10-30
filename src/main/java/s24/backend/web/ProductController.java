package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;

@Controller
public class ProductController {

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
    
    
}
