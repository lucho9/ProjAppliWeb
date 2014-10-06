package m2.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http.authorizeRequests().antMatchers("/", "/home", "/customer**", "/product**", "/employee**", "/resources**")
				.permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").usernameParameter("login")
				.passwordParameter("pwd").permitAll() // redéfinition des input names de login.html
				.and().logout().logoutUrl("/bye").logoutSuccessUrl("/") // redéfinition pas de logout
				.permitAll();
		 */
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.csrf().disable();
	}

	@Configuration
	protected static class AuthenticationConfiguration extends
			GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("user").password("password")
					.roles("USER").and().withUser("toto").password("toto")
					.roles("ADMIN");
		}

	}
	
	/*
	@Autowired
	UserRepository userRepository;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/").permitAll();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new PlaintextPasswordEncoder());
	}
	@Bean
	protected UserDetailsService userDetailsService() {
		return new SpringOverflowUserDetailsService(this.userRepository);
	}
	 */

}
