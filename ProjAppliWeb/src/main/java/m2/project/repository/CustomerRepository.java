package m2.project.repository;

import m2.project.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Page<Customer> findAll(Pageable pageable);
	Customer findByLastName(String lastName);
	//Customer findByLastName(String name);
}
