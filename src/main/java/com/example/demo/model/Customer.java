package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.validators.PasswordMatch;

@PasswordMatch
@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long customerId;
	@Column(nullable = false,unique = true,length = 20)
    @NotBlank(message = "UserName is mandatory")
	@Size(min=2, max=30)
private String userName;
	@Column(nullable = false,length = 20)
    @NotBlank(message = "First Name is mandatory")
	@Size(min=2, max=30)
	@Pattern(regexp = "^[a-zA-z]{2,30}$",message = "Not Valid Name")
private String firstName;
	@Column(nullable = false,length = 20)
    @NotBlank(message = "Last Name is mandatory")
	@Size(min=2, max=30)
	@Pattern(regexp = "^[a-zA-z]{2,30}$",message = "Not Valid Name")
private String lastName;
	@Column(nullable = false,length = 100)
    @NotBlank(message = "Email is mandatory")
	@Email(message = "this is not valid Email")
private String email;
	@Column(nullable = false,length = 20)
	@NotBlank(message = "Phone is mandatory")
	@Pattern(regexp = "^[01]{1}[0-9]{10}$",message = "Not Valid Mobile Number")
private String phone;
	@Column(nullable = false,length = 64)
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_-])(?=\\S+$).{8,}$",message = "Password is at least 8 Chars contains at Least one of each Upper case and small case letter and a special char and a number")
private String password;
	@Transient
	@NotBlank(message = "Confirm Password is mandatory")
private String confirmPassword;

public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public void enctyptPassword() {
	
	BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	String encryptedPassword=bCryptPasswordEncoder.encode(this.getPassword());
	this.setPassword(encryptedPassword);
	this.setConfirmPassword(encryptedPassword);
}
}
