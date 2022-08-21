package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.model.Customer;

@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository repo;
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
@GetMapping("/register")
public String showRegistrationForm(Model model) {
    model.addAttribute("customer", new Customer());
    return "signup";
}
@PostMapping("/register")
public String processRegister(@Valid Customer customer, BindingResult bindingResult, Model model) {
	if (checkErrors(bindingResult, customer.getUserName())) 
		  return "signup";
  customer.enctyptPassword();
     repo.save(customer);
    return "register_success";
}

@GetMapping("/loggedin_success")
public String showLoggedInSuccess() {
    return "loggedin_success";
}


public boolean checkErrors(BindingResult bindingResult,String userName) {
	if (bindingResult.hasErrors()) 
			return  true;
	if(repo.findByUserName(userName)!=null) {
	   	bindingResult.addError(new ObjectError("UserNameAlreadyExist", "UserName "+userName +" Already Exist"));
	   	return true;
	}
	return false;
}


}
