package m2.project.service.impl;

import java.util.List;

import m2.project.model.Customer;
import m2.project.model.CustomerGroup;
import m2.project.repository.CustomerGroupRepository;
import m2.project.service.CustomerGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerGroupServiceImpl implements CustomerGroupService {

	@Autowired
	CustomerGroupRepository customerGroupRepository;
	
	public CustomerGroup findOne(long id) {
		return customerGroupRepository.findOne(id);
	}
	
	public List<CustomerGroup> findAll() {
		return customerGroupRepository.findAll(new Sort(new Order(Direction.DESC, "discount")));
	}
	
	public CustomerGroup save(CustomerGroup customerGroup) {
		return customerGroupRepository.save(customerGroup);
	}

	public void delete(long id) {
		customerGroupRepository.delete(id);
	}
	
	public void delete(CustomerGroup customerGroup) {
		customerGroupRepository.delete(customerGroup);
	}
	
	public void deleteAll() {
		customerGroupRepository.deleteAll();
	}
	
	public String getGroupInfos(long id) {
		CustomerGroup g = findOne(id);
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"title\":\"" + g.getName() + "\"");
		sb.append(", \"discount\":\"" + g.getDiscount() + "\"");
		sb.append(", \"customers\":\"");
		for (Customer c : g.getCustomers()) {
			sb.append(c.getLastName()).append(" ").append(c.getFirstName()).append("<br />");
		}
		sb.append("\"");
		sb.append("}");
		return sb.toString();
	}
	
}
