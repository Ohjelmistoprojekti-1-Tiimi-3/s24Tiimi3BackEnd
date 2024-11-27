package s24.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s24.backend.domain.AppUser;
import s24.backend.domain.AppUserRepository;
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
			TypeRepository trepo, CustomerRepository crepo, AppUserRepository arepo) {
		return (args) -> {
			Manufacturer m1 = new Manufacturer("Rukka", "erittäin korkea suojaavuus");
			Manufacturer m2 = new Manufacturer("Basic", "laadukkaat tuotteet järkevään hintaan");
			Manufacturer m3 = new Manufacturer("Feel Active", "käyttöä säässä kuin säässä");
			Manufacturer m4 = new Manufacturer("Hurtta", "korkealaatuisia koirantarvikkeita");
			mrepo.save(m1);
			mrepo.save(m2);
			mrepo.save(m3);
			mrepo.save(m4);
			Size s1 = new Size("S");
			Size s2 = new Size("M");
			Size s3 = new Size("L");
			srepo.save(s1);
			srepo.save(s2);
			srepo.save(s3);
		
			Type t1 = new Type("Vaate");
			Type t2 = new Type("Lelu");
			Type t3 = new Type("Ruoka");
			trepo.save(t1);
			trepo.save(t2);
			trepo.save(t3);

			Product p1 = new Product("Sadetakki", (float) 10.0, "Punainen", "Kiva, hyvä ja halpa", m3, t1,
					s2,4);
			Product p2 = new Product("Kissan ruoka", (float) 29.99, null, "namnam", m4, t3,
			null, 3);
			Product p3 = new Product("Koiran purulelu", (float) 15, "sininen", "kestävä", m2, t2,
					null, 2);
			prepo.save(p1);
			prepo.save(p2);
			prepo.save(p3);

			Customer c1 = new Customer("Maija", "Mehiläinen", "maijameh@gmail.com");
			Customer c2 = new Customer("Jorma", "Jormanen", "jormajor@gmail.com");
			crepo.save(c1);
			crepo.save(c2);

			AppUser admin = new AppUser("admin", "$2a$10$z9TZiBsJv7/1tk5V7TRcVOwJlsNhzgV3DKi4ArViniEPOmaLZ2kbC", "ROLE_ADMIN");
			arepo.save(admin);
		};
	}

}
