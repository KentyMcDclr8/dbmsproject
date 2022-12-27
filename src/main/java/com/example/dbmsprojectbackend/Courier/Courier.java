package com.example.dbmsprojectbackend.Courier;

import jakarta.persistence.*;

@Entity(name = "Courier")
@Table(name = "courier")
public class Courier {
	public static Long courierId = 6000L;

	@Id
	@Column(
			name = "id",
			updatable = false
	)
	private Long id;

	@Column(
			name = "password",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String password;

	@Column(
			name = "name",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String name;

	@Column(
			name = "email",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String email;
	@Column(
			name = "type",
			nullable = false,
			updatable = false,
			columnDefinition = "TEXT"
	)
	private String type;

	@Column(
			name = "phone",
			nullable = false
	)
	private Long phone;
	@Column(
			name = "salary",
			nullable = true
	)
	private int salary;
	@Column(
			name = "approved",
			nullable = false
	)
	private boolean approved;
	@Column(
			name = "available",
			nullable = false
	)
	private boolean available;
	@Column(
			name = "application_reason",
			nullable = false
	)
	private String application_reason;

	public Courier() {
	}

	public Courier(String password,int salary , String name, String email, Long phone, String application_reason, boolean approved, boolean available, String type) {
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.approved = approved;
		this.available = available;
		this.application_reason = application_reason;
		this.type = type;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// getter method for password
	public String getPassword() {
		return password;
	}

	// setter method for password
	public void setPassword(String password) {
		this.password = password;
	}

	// getter method for name
	public String getName() {
		return name;
	}

	// setter method for name
	public void setName(String name) {
		this.name = name;
	}

	// getter method for email
	public String getEmail() {
		return email;
	}

	// setter method for email
	public void setEmail(String email) {
		this.email = email;
	}

	// getter method for phone
	public Long getPhone() {
		return phone;
	}

	// setter method for phone
	public void setPhone(Long phone) {
		this.phone = phone;
	}

	// getter method for application_reason
	public String getApplicationReason() {
		return application_reason;
	}

	// setter method for application_reason
	public void setApplicationReason(String application_reason) {
		this.application_reason = application_reason;
	}

	// getter method for approved
	public boolean isApproved() {
		return approved;
	}

	// setter method for approved
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	// getter method for available
	public boolean isAvailable() {
		return available;
	}

	// setter method for available
	public void setAvailable(boolean available) {
		this.available = available;
	}

	// getter method for type
	public String getType() {
		return type;
	}

	// setter method for type
	public void setType(String type) {
		this.type = type;
	}
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}