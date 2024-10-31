package s24.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import s24.backend.domain.SizeRepository;
import s24.backend.domain.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String showProducts(Model model) {
        model.addAttribute("products", productrepo.findAll());
        return "productList";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("sizes", sizerepo.findAll());
        model.addAttribute("types", typerepo.findAll());
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "addProduct";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("manufacturerId") Long manufacturerId) {
        productrepo.save(product);
        Manufacturer manufacturer = manufacturerrepo.findById(manufacturerId).orElse(null);
        product.setManufacturer(manufacturer); //Asettaa valmistajan muokatulle tuotteelle
        return "redirect:productList";
    }

    // Poistetaan tuote
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productrepo.deleteById(id);
        return "redirect:productlist";
    }

    // Muokataan tuotetta
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editproduct", productrepo.findById(id));
        model.addAttribute("manufacturers", manufacturerrepo.findAll()); //Lisää valmistajien listan 
        return "edit";
    }

}