package m2.project.security;

import java.util.ArrayList;
import java.util.List;

import m2.project.model.Employee;
import m2.project.model.Role;
import m2.project.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

@Service("securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee loggedInEmployee = employeeService.findByLogin(login);
        if (loggedInEmployee == null) {
            throw new UsernameNotFoundException(login);
        }
        
        List<GrantedAuthority> authorities = getAuthorities(loggedInEmployee);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new UserAccount(
                loggedInEmployee.getLogin(),
                loggedInEmployee.getPassword(),
                loggedInEmployee.getEmail(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
    }
    
    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
        Employee loggedInEmployee = employeeService.findByLogin(getAttribute(token, "email"));
        if (loggedInEmployee == null) {
            throw new UsernameNotFoundException(token.getName());
        }
        
        List<GrantedAuthority> authorities = getAuthorities(loggedInEmployee);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        return new UserAccount(
            loggedInEmployee.getLogin(),
            loggedInEmployee.getPassword(),
            loggedInEmployee.getEmail(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            authorities);
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

    // [[email:[gomygamez@gmail.com]], [firstname:[robert]], [lastname:[martin]]]
    private String getAttribute(OpenIDAuthenticationToken token, String attr) {
        for (OpenIDAttribute attribute : token.getAttributes()) {
            if (attribute.getName().equals(attr)) {
                return attribute.getValues().get(0);
            }
        }
        return null;
    }
}