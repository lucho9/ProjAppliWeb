package m2.project.service;

import java.util.List;

import m2.project.model.Role;

public interface RoleService {
	public Role findOne(long id);
	public void save(Role role);
	public void delete(long id);
	public void delete(Role role);
	public void deleteAll();
	public List<Role> findAll();
}
