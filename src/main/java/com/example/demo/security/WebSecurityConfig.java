package com.example.demo.security;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
	
	@Autowired
	private DataSource dataSource;

	 @Bean
	 public UserDetailsService userDetailsService() {
		 
		return new CustomCustomerDetailsService ();
	 }
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		 authProvider.setUserDetailsService(userDetailsService());
		 authProvider.setPasswordEncoder(passwordEncoder());
		 return authProvider;
	 }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.addFilterAfter(new LoginPageFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests().antMatchers("/loggedin_success").authenticated()
			.anyRequest().permitAll()
			.and().formLogin()
			.usernameParameter("userName")
			.defaultSuccessUrl("/loggedin_success")
			.permitAll()
			.and().logout().logoutSuccessUrl("/").permitAll();
	        return http.build();
	    }	
	    @Bean
	 public InMemoryUserDetailsManager configureAuthentication() {
	    	UserDetails user = User.withDefaultPasswordEncoder()
	                .username("userName")
	                .password("password")
	                .roles("USER")
	                .build();
	            return new InMemoryUserDetailsManager(user);
	    }
}
