package s24.backend.domain;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findByCustomer(Customer customer);
}
