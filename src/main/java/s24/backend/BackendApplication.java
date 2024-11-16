package s24.backend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.backend.domain.Customer;
import s24.backend.domain.CustomerRepository;
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
			TypeRepository trepo, CustomerRepository crepo) {
		return (args) -> {
			Manufacturer m1 = new Manufacturer("Rukka", "erittäin korkea suojaavuus");
			Manufacturer m2 = new Manufacturer("Basic", "laadukkaat tuotteet järkevään hintaan");
			Manufacturer m3 = new Manufacturer("Feel Active", "käyttöä säässä kuin säässä");
			Manufacturer m4 = new Manufacturer("Hurtta", "korkealaatuisia koirantarvikkeita");
			mrepo.save(m1);
			mrepo.save(m2);
			mrepo.save(m3);
			mrepo.save(m4);
			Size s1 = new Size("XS");
			Size s2 = new Size("S");
			Size s3 = new Size("M");
			Size s4 = new Size("L");
			Size s5 = new Size("XL");
			srepo.save(s1);
			srepo.save(s2);
			srepo.save(s3);
			srepo.save(s4);
			srepo.save(s5);
			Type t1 = new Type("Vaate");
			Type t2 = new Type("Lelu");
			Type t3 = new Type("Ruoka");
			trepo.save(t1);
			trepo.save(t2);
			trepo.save(t3);

			Set<Size> p1Sizes = new HashSet<>();
			p1Sizes.add(s2);
			p1Sizes.add(s3);
			Set<Type> p1Types = new HashSet<>();
			p1Types.add(t1);
			Set<Type> p2Types = new HashSet<>();
			p2Types.add(t3);
			Set<Type> p3Types = new HashSet<>();
			p3Types.add(t2);
			Product p1 = new Product("Sadetakki", (float) 10.0, "Punainen", "Kiva, hyvä ja halpa", m3, p1Types,
					p1Sizes);
			Product p2 = new Product("Kissan ruoka", (float) 29.99, null, "namnam", m4, p2Types,
			null);
			Product p3 = new Product("Koiran purulelu", (float) 15, "sininen", "kestävä", m2, p3Types,
					null);
			prepo.save(p1);
			prepo.save(p2);
			prepo.save(p3);

			Customer c1 = new Customer("Maija", "Mehiläinen", "maijameh@gmail.com");
			Customer c2 = new Customer("Jorma", "Jormanen", "jormajor@gmail.com");
			crepo.save(c1);
			crepo.save(c2);

		};
	}

}
