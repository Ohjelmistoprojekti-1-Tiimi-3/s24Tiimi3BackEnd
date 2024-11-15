package s24.backend;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import s24.backend.domain.Manufacturer;
import s24.backend.domain.ManufacturerRepository;
import s24.backend.domain.Product;
import s24.backend.domain.ProductRepository;
import s24.backend.domain.Size;
import s24.backend.domain.SizeRepository;
import s24.backend.domain.Type;
import s24.backend.domain.TypeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productrepo;
    @Autowired
    ManufacturerRepository manufacturerrepo;
    @Autowired
    SizeRepository sizerepo;
    @Autowired
    TypeRepository typerepo;

    @Test
    public void addNewProductShouldGrowRepoSizeByOne() {
        Manufacturer testmanufacturer = new Manufacturer("TestManufacturer", "TestInfo");
        manufacturerrepo.save(testmanufacturer);
        Type testtype =  new Type("TestType");
        typerepo.save(testtype);
        Set<Type> testtypes = new HashSet<>();
        Size testsize = new Size("S");
        sizerepo.save(testsize);
        Set<Size> testsizes = new HashSet<>();
        Product testproduct = new Product("TestName", (float) 10.0, "Red", "TestInfo", testmanufacturer, testtypes, testsizes);

        long firstcount = productrepo.count();
        productrepo.save(testproduct);
        long secondcount = productrepo.count();

        assertEquals((firstcount+1), secondcount);
    }

    @Test
    public void deletingProductShouldReductRepoSizeByOne() {
        Manufacturer testmanufacturer = new Manufacturer("TestManufacturer", "TestInfo");
        manufacturerrepo.save(testmanufacturer);
        Type testtype =  new Type("TestType");
        typerepo.save(testtype);
        Set<Type> testtypes = new HashSet<>();
        Size testsize = new Size("S");
        sizerepo.save(testsize);
        Set<Size> testsizes = new HashSet<>();
        Product testproduct = new Product("TestName", (float) 10.0, "Red", "TestInfo", testmanufacturer, testtypes, testsizes);
        productrepo.save(testproduct);

        long firstcount = productrepo.count();
        productrepo.delete(testproduct);
        long secondcount = productrepo.count();

        assertEquals((firstcount-1), secondcount);
    }
}
