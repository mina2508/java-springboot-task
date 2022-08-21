package com.example.demo.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.example.demo.model.Customer;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch,Object > {
	   @Override
	    public void initialize(PasswordMatch constraintAnnotation) {
	    }
	    @Override
	    public boolean isValid(Object obj, ConstraintValidatorContext context){
	    	Customer customer = (Customer) obj;
	        return customer.getPassword().equals(customer.getConfirmPassword());
	    }
}
