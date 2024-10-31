package s24.backend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import s24.backend.domain.Size;
import s24.backend.domain.SizeRepository;
import s24.backend.domain.Type;
import s24.backend.domain.TypeRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ManufacturerRepository mrepo, ProductRepository prepo, SizeRepository srepo,
			TypeRepository trepo) {
		return (args) -> {
			Manufacturer m1 = new Manufacturer("Valmistaja 1", "-");
			Manufacturer m2 = new Manufacturer("Valmistaja 2", "-");
			mrepo.save(m1);
			mrepo.save(m2);
			Size s1 = new Size("M");
			Size s2 = new Size("L");
			srepo.save(s1);
			srepo.save(s2);
			Type t1 = new Type("vaate");
			trepo.save(t1);

			Set<Size> p1Sizes = new HashSet<>();
			p1Sizes.add(s1);
			p1Sizes.add(s2);
			Set<Type> p1Types = new HashSet<>();
			p1Types.add(t1);
			Product p1 = new Product("Tuotteen nimi", (float) 10.0, "Punainen", "Kiva, hyvä ja halpa", m2, p1Types,
					p1Sizes);
			prepo.save(p1);
		};
	}

}
