package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)//because by default it uses inMemory database
@Rollback(false)
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateCustomer() {
		Customer customer=new Customer();
		customer.setFirstName("Mina");
		customer.setLastName("Kameel");
		customer.setUserName("mkameel");
		customer.setPhone("01228017124");
		customer.setEmail("mina@gmail.com");
		customer.setPassword("123456789");
		customer.enctyptPassword();
		Customer savedCustomer= repo.save(customer);
		Customer existedCustomer=entityManager.find(Customer.class, savedCustomer.getCustomerId());
		assertThat(existedCustomer.getEmail()).isEqualTo(customer.getEmail());
	}
	@Test
	public void testFindUserByUserName() {
		String userName="mike";
		Customer customer=repo.findByUserName(userName);
		assertThat(customer).isNull();
		
	}

}
