package m2.project.config;

import m2.project.security.MyAccessDeniedHandler;
import m2.project.security.SecurityUserDetailsService;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private SecurityUserDetailsService userDetailsService;
    @Autowired private DataSource dataSource;
	
    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }
    
    /*
	@Bean
    public RememberMeServices rememberMeServices() {
        // Key must be equal to rememberMe().key() 
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("your_key", userDetailsService);
        rememberMeServices.setCookieName("rememberme_cookie");
        rememberMeServices.setParameter("rememberme");
        rememberMeServices.setTokenValiditySeconds(10); // 1month
        return rememberMeServices;
    }
     */
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/errors/**", "/fragments/**", "/theme/**", "/js/**", "/ThemeTemplate/**", "/index**", "/callconfig/**", "/config/**");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
	
		//http.authorizeRequests().antMatchers("/**").permitAll();
		//http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/customer/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/customer/delete/**").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/customer/grouppopover/**").access("hasRole('ROLE_ADMIN')");

		http.authorizeRequests().antMatchers("/product/**").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/category/**").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/employee/**").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/caisse**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/facture/display").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/facture/delete").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/factures/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/facture/validationCommande").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/delete").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/deletePanier").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/custCaisse").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/cbCaisse").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/stats/**").access("hasRole('ROLE_ADMIN')");
		
		http.formLogin()
			.loginPage("/login")
            .loginProcessingUrl("/process-login")
			.usernameParameter("login").passwordParameter("pwd") // redéfinition des input names de login.html
			.permitAll();
		
		// avec l'instruction logoutUrl, la requête est en post -> logoutRequestMatcher fait du get
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
		
		http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler("/errors/403"));
		//http.exceptionHandling().accessDeniedPage("");
		
		// marche pas
		//http.rememberMe()
        //	.key("your_key")
        //	.rememberMeServices(rememberMeServices());
		
		http.openidLogin()
	        .loginPage("/login")
	        .authenticationUserDetailsService(userDetailsService)
	        .attributeExchange("https://www.google.com/.*")
	            .attribute("email")
	                .type("http://axschema.org/contact/email")
	                .required(true)
	                .and()
	            .attribute("firstname")
	                .type("http://axschema.org/namePerson/first")
	                .required(true)
	                .and()
	            .attribute("lastname")
	                .type("http://axschema.org/namePerson/last")
	                .required(true)
	                .and()
	            .and()
	        .attributeExchange(".*yahoo.com.*")
	            .attribute("email")
	                .type("http://axschema.org/contact/email")
	                .required(true)
	                .and()
	            .attribute("fullname")
	                .type("http://axschema.org/namePerson")
	                .required(true)
	                .and()
	            .and()
	        .attributeExchange(".*myopenid.com.*")
	            .attribute("email")
	                .type("http://schema.openid.net/contact/email")
	                .required(true)
	                .and()
	            .attribute("fullname")
	                .type("http://schema.openid.net/namePerson")
	                .required(true);
	}
}
