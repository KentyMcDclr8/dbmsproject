package com.example.dbmsprojectbackend.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin
public class LoginController {
	private final LoginService loginService;
	private final Admin admin;

	@Autowired
	public LoginController(LoginService loginService, Admin admin) {
		this.loginService = loginService;
		this.admin = admin;
	}

	@PostMapping("login")
	public Object customerLogin(@RequestParam(required = true) Long id,
	                            @RequestParam(required = true) String password) {
		if (id == 353L && password.equals("A")) {
			return admin;
		}
		return loginService.customerLogin(id, password);
	}
}