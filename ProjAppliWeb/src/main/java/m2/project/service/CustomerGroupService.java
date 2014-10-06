package m2.project.service;

import java.util.List;

import m2.project.model.CustomerGroup;

public interface CustomerGroupService {
	public CustomerGroup findOne(long id);
	public List<CustomerGroup> findAll();	
	public void save(CustomerGroup customerGroup);
	public void delete(long id);
	public void delete(CustomerGroup customerGroup);
}
