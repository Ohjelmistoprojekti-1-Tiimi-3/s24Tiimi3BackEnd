package s24.backend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;

@RestController
@RequestMapping("/api")
public class RestManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerrepo;

    //Get all manufacturers
    @GetMapping("/manufacturers")
    public Iterable<Manufacturer> getManufacturers() {
        return manufacturerrepo.findAll();
    }
}
