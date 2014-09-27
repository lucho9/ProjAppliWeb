package m2.project.repository;

import org.springframework.data.repository.CrudRepository;

import m2.project.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
