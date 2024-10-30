package s24.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProductController {


    // Poistetaan tuote
    @GetMapping("delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model) {
		log.info("delete product " + id);
		ProductRepository.deleteById(id);
		return "redirect:/";
	}
    

}
