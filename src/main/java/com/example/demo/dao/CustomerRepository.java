package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
	@Query("select c from Customer c where c.userName=?1")
Customer findByUserName(String userName) ;

}
