package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;

public class CustomCustomerDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepository repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=repo.findByUserName(username);
		if(customer==null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new CustomCustomerDetails(customer);
	}

}
