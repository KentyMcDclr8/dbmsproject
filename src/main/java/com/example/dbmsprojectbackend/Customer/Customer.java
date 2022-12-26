package com.example.dbmsprojectbackend.Customer;

import com.example.dbmsprojectbackend.PaymentDetails.PaymentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Customer")
@Table(
		name = "customer",
		uniqueConstraints = {
				@UniqueConstraint(name = "customer_email_unique", columnNames = "email"),
				@UniqueConstraint(name = "customer_phone_unique", columnNames = "phone")
		}
)
public class Customer {
	public static Long customerId = 2000L;

	// properties
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
			name = "building_number",
			nullable = false,
			columnDefinition = "TEXT"

	)
	private String building_number;

	@Column(
			name = "street_number",
			nullable = false,
			columnDefinition = "TEXT"

	)
	private String street_number;

	@Column(
			name = "city",
			columnDefinition = "TEXT",
			nullable = false

	)
	private String city;

	@Column(
			name = "province",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String province;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<PaymentDetails> paymentDetailsList = new ArrayList<PaymentDetails>();


	// constructors
	public Customer() {
	}

	public Customer(String password, String name, String email, Long phone, String building_number, String street_number, String city, String province, String type) {
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.building_number = building_number;
		this.street_number = street_number;
		this.city = city;
		this.province = province;
		this.type = type;
	}

	// getters and setters
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBuildingNumber() {
		return building_number;
	}

	public void setBuildingNumber(String building_number) {
		this.building_number = building_number;
	}

	public String getStreetNumber() {
		return street_number;
	}

	public void setStreetNumber(String street_number) {
		this.street_number = street_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PaymentDetails> getPaymentDetailsList() {
		return paymentDetailsList;
	}

	public void addPaymentDetails(PaymentDetails paymentDetails) {
		if (!paymentDetailsList.contains(paymentDetails)) {
			paymentDetailsList.add(paymentDetails);
		}
	}
}