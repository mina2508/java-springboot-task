package com.example.demo.model.validators;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ TYPE })
@Retention(RUNTIME)
public @interface PasswordMatch {
	  String message() default "Passwords don't match";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};


}
