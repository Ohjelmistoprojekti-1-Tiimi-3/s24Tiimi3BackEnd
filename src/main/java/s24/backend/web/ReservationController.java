
package s24.backend.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import s24.backend.domain.Reservation;
import s24.backend.domain.ReservationRepository;

@Controller
public class ReservationController {

    @Autowired
    private ProductRepository productrepo;

    @Autowired
    private ReservationRepository reservationrepo;

    @GetMapping("/reservation/{id}")
    public String showReservationPage(@PathVariable("id") Long id, Model model) {
        Product product = productrepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        model.addAttribute("reservation", new Reservation());
        return "reservation";  
    }

    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation, Model model) {
        if (reservation.getProduct() == null || reservation.getProduct().getProductid() <= 0) {
            model.addAttribute("error", "Tuotteen ID puuttuu tai on virheellinen!");
            return "reservation"; 
        }

        Product product = productrepo.findById(reservation.getProduct().getProductid())
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + reservation.getProduct().getProductid()));

        if (product.getQuantity() >= reservation.getQuantity()) {
            product.setQuantity(product.getQuantity() - reservation.getQuantity());
            productrepo.save(product);

            reservation.setStatus("odottaa");
            reservation.setReservationDate(LocalDateTime.now());
            reservationrepo.save(reservation);

            model.addAttribute("success", "Varaus onnistui.");  // Onnistumisviesti
        } else {
            model.addAttribute("error", "Ei riittävästi varastossa.");  // Virheviesti
        }

        model.addAttribute("product", product);  
        return "reservation";  
    }
}
*/
