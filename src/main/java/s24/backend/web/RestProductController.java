package s24.backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;



@RestController
@RequestMapping("/api")
public class RestProductController {

    @Autowired
    private ProductRepository productrepo;
    @Autowired
    private ManufacturerRepository manufacturerrepo;

    //Get all products
    @CrossOrigin
    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return productrepo.findAll();
    }

    // Get products by type
    // URL end /searchByType?type={typename}
    @GetMapping("/searchByType")
    public List<Product> getProductByName(@RequestParam("type") String typename) {
        return productrepo.findByType_typenameContainingIgnoreCase(typename);
    }

    // Get products by manufacturer
    // URL end /searchByManufacturer?manufacturer={manufacturername}
    @GetMapping("/searchByManufacturer")
    public List<Product> getProductByManufacturer(@RequestParam("manufacturer") String manufacturername) {

        Manufacturer manufacturer = manufacturerrepo.findByManufacturername(manufacturername);
        return productrepo.findByManufacturer(manufacturer);
    }
}
