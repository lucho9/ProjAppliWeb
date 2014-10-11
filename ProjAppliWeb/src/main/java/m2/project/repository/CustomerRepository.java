package m2.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import m2.project.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Page<Customer> findAll(Pageable pageable);
}
