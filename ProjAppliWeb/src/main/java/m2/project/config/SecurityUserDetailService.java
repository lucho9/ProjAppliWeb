package m2.project.config;

import java.util.ArrayList;
import java.util.List;

import m2.project.model.Employee;
import m2.project.model.Role;
import m2.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Employee loggedInEmployee = employeeService.findEmployeeByLogin(login);

            List<GrantedAuthority> authorities = getAuthorities(loggedInEmployee);
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new User(
                    loggedInEmployee.getLogin(),
                    loggedInEmployee.getPassword().toLowerCase(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    authorities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<GrantedAuthority> getAuthorities(Employee employee) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //for (Role role : employee.getRoles()) {
        //    for (Permission permission : role.getPermissions()) {
        //        authorities.add(new SimpleGrantedAuthority(permission.getName()));
        //    }
        //}
        Role role = employee.getRole();
        
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

}