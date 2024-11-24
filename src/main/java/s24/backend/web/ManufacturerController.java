package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.ProductRepository;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerrepo;

    @Autowired
    private ProductRepository productRepository;

    // Adding a new manufacturer
    @RequestMapping(value = "/addManufacturer", method = RequestMethod.GET)
    public String addManufacturer(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "addManufacturer";
    }

    //showing the manufacturers
    @GetMapping("/manufacturerlist")
    public String showManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "manufacturerlist";
    }

    //delete manufacturer
    @GetMapping("/manufacturer/delete/{id}")
    public String deleteManufacturer(@PathVariable("id") Long id, Model model) {
        manufacturerrepo.deleteById(id);
        return "redirect:/manufacturerlist";
    }

    // Saving the information to a new manufacturer object
    /* 
     * Ugly catcher for unique manufacturer name
     * because Thymeleaf does not handle validation from unique constraints without a separate method
    */

    @RequestMapping(value = "/saveManufacturer", method = RequestMethod.POST)
    public String saveManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
			return "addManufacturer";
		}
        try {
            manufacturerrepo.save(manufacturer);
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("manufacturername", "error.manufacturer", "Manufacturer name must be unique.");
            return "addManufacturer";
        }
        return "redirect:/manufacturerlist";
    }

    @GetMapping("/manufacturer/{id}/products")
    public String getProductsByManufacturer(@PathVariable("id") Long manufacturerId, Model model) {

    Manufacturer manufacturer = manufacturerrepo.findById(manufacturerId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid manufacturer Id:" + manufacturerId));
    
    model.addAttribute("manufacturer", manufacturer);
    model.addAttribute("products", productRepository.findByManufacturer(manufacturer));

    return "manufacturerProducts";
}

}
