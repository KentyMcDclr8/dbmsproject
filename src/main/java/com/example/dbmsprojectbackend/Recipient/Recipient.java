package com.example.dbmsprojectbackend.Recipient;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.PaymentDetails.PaymentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Recipient")
@Table(name = "recipient")
public class Recipient {
	public static Long recipientId = 3000L;

	// properties
	@Id
	@Column(
			name = "recipient_id",
			nullable = false
	)
	private Long recipient_id;

	@ManyToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;


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



	// constructors
	public Recipient() {
	}

	public Recipient(Long recipient_id ,Customer customer, String name, String email, Long phone, String building_number, String street_number, String city, String province) {
		this.name = name;
		this.recipient_id = recipient_id;
		this.customer = customer;
		this.email = email;
		this.phone = phone;
		this.building_number = building_number;
		this.street_number = street_number;
		this.city = city;
		this.province = province;
	}

	// getters and setters
	public Long getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(Long recipient_id) {
		this.recipient_id = recipient_id;
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
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}