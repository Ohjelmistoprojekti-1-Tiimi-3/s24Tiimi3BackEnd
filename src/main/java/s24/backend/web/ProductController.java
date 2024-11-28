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
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import s24.backend.domain.SizeRepository;
import s24.backend.domain.TypeRepository;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productrepo;

    @Autowired
    private SizeRepository sizerepo;

    @Autowired
    private TypeRepository typerepo;

    @Autowired
    private ManufacturerRepository manufacturerrepo;

    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String showProducts(Model model) {
        model.addAttribute("products", productrepo.findAll());
        return "productList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("sizes", sizerepo.findAll());
        model.addAttribute("types", typerepo.findAll());
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "addProduct";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("sizes", sizerepo.findAll());
            model.addAttribute("types", typerepo.findAll());
            model.addAttribute("manufacturers", manufacturerrepo.findAll());
			return "addProduct";
		}
        productrepo.save(product);
        return "redirect:productList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productrepo.deleteById(id);
        return "redirect:/productList";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editproduct", productrepo.findById(id));
        model.addAttribute("sizes", sizerepo.findAll());
        model.addAttribute("types", typerepo.findAll());
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "editProduct";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/saveEditedProduct", method = RequestMethod.POST)
    public String saveEditedProduct(@Valid @ModelAttribute("editproduct") Product product, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("sizes", sizerepo.findAll());
            model.addAttribute("types", typerepo.findAll());
            model.addAttribute("manufacturers", manufacturerrepo.findAll());
			return "editProduct";
		}

        // Jotta quantity pysyy samana
        Product existingProduct = productrepo.findById(product.getProductid()).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + product.getProductid()));
        product.setQuantity(existingProduct.getQuantity());
        productrepo.save(product);
        return "redirect:productList";
    }

    // Tuotemäärän päivitykseen, oikeudet vain adminilla
    @PreAuthorize("hasRole('ADMIN')")
@GetMapping("/admin/updateQuantity/{id}")
public String showUpdateQuantityPage(@PathVariable("id") long id, Model model) {
    Product product = productrepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
    model.addAttribute("product", product);
    return "updateQuantity"; // Tässä viitataan updateQuantityyn
}

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/admin/saveQuantity", method = RequestMethod.POST)
public String saveUpdatedQuantity(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
    /* if (bindingResult.hasErrors()) {
        return "updateQuantity";
    } */
    Product existingProduct = productrepo.findById(product.getProductid())
        .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + product.getProductid()));
    existingProduct.setQuantity(product.getQuantity());
    productrepo.save(existingProduct);
    return "redirect:/productList";
}


}