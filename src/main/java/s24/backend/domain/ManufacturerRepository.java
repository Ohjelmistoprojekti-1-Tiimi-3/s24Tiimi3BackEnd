package s24.backend.domain;

import org.springframework.data.repository.CrudRepository;


public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

    Manufacturer findByManufacturername(String manufacturername);

}
