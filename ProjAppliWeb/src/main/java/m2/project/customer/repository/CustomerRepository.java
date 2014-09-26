package m2.project.customer.repository;

import org.springframework.data.repository.CrudRepository;

import m2.project.customer.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
