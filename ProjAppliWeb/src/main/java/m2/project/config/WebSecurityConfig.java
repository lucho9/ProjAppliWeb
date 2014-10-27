package m2.project.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetailService;
    @Autowired private DataSource dataSource;
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }
    
    @Bean
    public UserDetailsService userDetailService() {
        return new SecurityUserDetailService();
    }
    
	@Bean
    public RememberMeServices rememberMeServices() {
        // Key must be equal to rememberMe().key() 
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("your_key", userDetailService);
        rememberMeServices.setCookieName("rememberme_cookie");
        rememberMeServices.setParameter("rememberme");
        rememberMeServices.setTokenValiditySeconds(10); // 1month
        return rememberMeServices;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/errors/**", "/templates/fragments/**", "/theme/**", "/js/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth
          .jdbcAuthentication()
              .dataSource(dataSource)
              .usersByUsernameQuery(getUserQuery())
              .authoritiesByUsernameQuery(getAuthoritiesQuery())
              .passwordEncoder(passwordEncoder);
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests().antMatchers("/", "/home").permitAll();
		http.authorizeRequests().antMatchers("/customer/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/product/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/employee/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/caisse/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		
		http.formLogin()
			.loginPage("/login")
			//.defaultSuccessUrl("/success-login", true)
            //.failureUrl("/error-login")
            .loginProcessingUrl("/process-login")
			.usernameParameter("login").passwordParameter("pwd").permitAll(); // redéfinition des input names de login.html
			//and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
		
		// avec l'instruction logoutUrl, la requête est en post -> logoutRequestMatcher fait du get
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
		
		// marche pas
		http.rememberMe()
        	.key("your_key")
        	.rememberMeServices(rememberMeServices());
		
		//http.authorizeRequests().antMatchers("/**").permitAll();
		//http.csrf().disable();
	}

    private String getUserQuery() {
        return "SELECT login AS username, password AS password, CAST(1 AS BIT) AS enabled "
                + "FROM employee "
                + "WHERE login = ?";
    }

    private String getAuthoritiesQuery() {
        return "SELECT DISTINCT employee.login AS username, role.name AS authority "
                + "FROM employee "
                + "INNER JOIN role ON employee.role_id = role.id "
                + "WHERE employee.login = ?";
    }
}
