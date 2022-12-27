package com.example.dbmsprojectbackend.Recipient;

import com.example.dbmsprojectbackend.Customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class RecipientService {
	@PersistenceContext
	private EntityManager entityManager;
	private final RecipientRepository recipientRepository;

	@Autowired

	public RecipientService(RecipientRepository recipientRepository) {
		this.recipientRepository = recipientRepository;
	}

	public List<Recipient> getRecipient() {
		return recipientRepository.findAll();
	}

	public List<Recipient> getRecipientByFilter(Long customerId, Filters filters) {
		String name;
		String email;
		if(filters.getName() == null){
			name = "";
		}
		else {
			name = filters.getName();
		}
		if(filters.getEmail() == null){
			email = "";
		}
		else {
			email = filters.getEmail();
		}
		Query sql =  entityManager.createNativeQuery("SELECT r.recipient_id FROM recipient r WHERE r.name like \'%"+name+"%\' "+ "and r.email like \'%" + email+"%\'");
		List<Long> r = sql.getResultList();
		List<Recipient> c = new ArrayList<>();
		for (Long a:r){
			c.add(recipientRepository.findById(a).orElseThrow(() -> new IllegalStateException("A complaint with that ID does not exist.")));

		}
		return c;





		//return recipientRepository.findAll();
	}

	@Transactional

	public void addNewRecipient(Recipient recipient, Customer customer) {
		Optional<Recipient> recipientOptionalId = recipientRepository.findRecipientById(recipient.recipientId);
		while(recipientOptionalId.isPresent()){
			recipient.recipientId++;
			recipientOptionalId = recipientRepository.findRecipientById(recipient.recipientId);
		}
		entityManager.createNativeQuery("INSERT INTO recipient (recipient_id, customer_id, name, email, phone, building_number,  street_number,  city,  province) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")
				.setParameter(1, recipient.recipientId)
				.setParameter(2, customer.getId())
				.setParameter(3, recipient.getName())
				.setParameter(4, recipient.getEmail())
				.setParameter(5, recipient.getPhone())
				.setParameter(6, recipient.getBuildingNumber())
				.setParameter(7, recipient.getStreetNumber())
				.setParameter(8, recipient.getCity())
				.setParameter(9, recipient.getProvince())
				.executeUpdate();
		recipient.recipientId++;
	}

	public void deleteRecipient(Long recipientId) {
		Optional<Recipient> customerOptional = recipientRepository.findRecipientById(recipientId);
		if (!customerOptional.isPresent()) {
			throw new IllegalStateException("A recipient with that ID does not exist.");
		}
		recipientRepository.deleteById(recipientId);
	}

	@Transactional
	public void updateRecipient(Long recipientId,  String name, String email, Long phone, String building_number, String street_number, String city, String province) {
		Recipient recipient = recipientRepository.findById(recipientId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));
		if (name != null && name.length() != 0 && !Objects.equals(name, recipient.getName())) {
			recipient.setName(name);
		}
		if (email != null && email.length() != 0 && !Objects.equals(email, recipient.getEmail())) {
			Optional<Recipient> recipioentOptionalEmail = recipientRepository.findRecipientByEmail(email);
			if (recipioentOptionalEmail.isPresent()) {
				throw new IllegalStateException("A recipient with that email address already exists");
			}
			recipient.setEmail(email);
		}
		if (phone != null && phone != 0 && !Objects.equals(phone, recipient.getPhone())) {
			Optional<Recipient> recipientOptionalPhone = recipientRepository.findRecipientByPhone(phone);
			if (recipientOptionalPhone.isPresent()) {
				throw new IllegalStateException("A customer with that phone number already exists");
			}
			recipient.setPhone(phone);
		}
		if (building_number != null && !Objects.equals(building_number, recipient.getBuildingNumber())) {
			recipient.setBuildingNumber(building_number);
		}
		if (street_number != null && !Objects.equals(street_number, recipient.getStreetNumber())) {
			recipient.setStreetNumber(street_number);
		}
		if (city != null && city.length() != 0 && !Objects.equals(city, recipient.getCity())) {
			recipient.setCity(city);
		}
		if (province != null && province.length() != 0 && !Objects.equals(province, recipient.getProvince())) {
			recipient.setProvince(province);
		}
	}
}
