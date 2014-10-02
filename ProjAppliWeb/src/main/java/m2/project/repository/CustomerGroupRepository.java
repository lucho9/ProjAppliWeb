package m2.project.repository;

import org.springframework.data.repository.CrudRepository;

import m2.project.model.CustomerGroup;

public interface CustomerGroupRepository extends CrudRepository<CustomerGroup, Long> {

}
