package com.example.dbmsprojectbackend.Login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity(name = "Admin")
@Component
@Table(name = "admin")
public class Admin {
	// properties
	@Id
	@Column(
			name = "id",
			nullable = false
	)
	private Long id = 353L;
	@Column(
			name = "password",
			nullable = false
	)
	private String password = "A";
	@Column(
			name = "type",
			nullable = false,
			updatable = false,
			columnDefinition = "TEXT"
	)
	private String type = "Admin";

	public Admin() {
	}

	public Admin(String password, String type) {
		this.password = password;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}