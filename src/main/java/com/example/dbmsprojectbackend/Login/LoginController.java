package com.example.dbmsprojectbackend.Login;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {
	private final LoginService loginService;
@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	@PostMapping("customerLogin")
	public Customer customerLogin( @RequestParam(required = true) Long id,
	                       @RequestParam(required = true) String password){
	return loginService.customerLogin(id,password);
	}
	@PostMapping("employeeLogin")
	public Employee employeeLogin(@RequestParam(required = true) Long id,
	                      @RequestParam(required = true) String password){
		return loginService.employeeLogin(id,password);
	}
}