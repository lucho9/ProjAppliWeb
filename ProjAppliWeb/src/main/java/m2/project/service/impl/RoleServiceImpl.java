package m2.project.service.impl;

import java.util.List;

import m2.project.model.Role;
import m2.project.repository.RoleRepository;
import m2.project.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findOne(long id) {
		return roleRepository.findOne(id);
	}

	public void save(Role role) {
		roleRepository.save(role);
	}

	public void delete(long id) {
		roleRepository.delete(id);
	}

	public void delete(Role role) {
		roleRepository.delete(role);
	}

	public List<Role> findAll() {
		return roleRepository.findAll(new Sort(new Order(Direction.ASC, "name")));
	}
}
