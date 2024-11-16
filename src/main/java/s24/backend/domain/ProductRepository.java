package s24.backend.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
    List<Product> findByproductname(String productname);
  
    List<Product> findByType_typenameContainingIgnoreCase(String typename);

}
