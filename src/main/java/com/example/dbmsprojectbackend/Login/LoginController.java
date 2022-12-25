package com.example.dbmsprojectbackend.Login;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class LoginController {
	private final LoginService loginService;
@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	@PostMapping("login")
	public Object customerLogin( @RequestParam(required = true) Long id,
	                       @RequestParam(required = true) String password){
	return loginService.customerLogin(id,password);
	}
}