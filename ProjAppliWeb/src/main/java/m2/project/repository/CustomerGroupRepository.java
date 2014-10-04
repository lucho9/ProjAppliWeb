package m2.project.repository;

import m2.project.model.CustomerGroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long> {

}
