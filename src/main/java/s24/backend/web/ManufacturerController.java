package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerrepo;

    // Adding a new manufacturer
    @RequestMapping(value = "/addManufacturer", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        model.addAttribute("manufacturers", manufacturerrepo.findAll());
        return "addManufacturer";
    }

    // Saving the information to a new manufacturer object
    // Useless bindingresult as "Unique" does not translate to validation
    // Added just for future reference and when we need to add a working method to DAO class to parse uniques
    @RequestMapping(value = "/saveManufacturer", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
			return "addManufacturer";
		}
        manufacturerrepo.save(manufacturer);
        return "redirect:addManufacturer";
    }
}
