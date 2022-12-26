package com.example.dbmsprojectbackend.Courier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourierService {
	@PersistenceContext
	private EntityManager entityManager;
	private final CourierRepository courierRepository;

	@Autowired

	public CourierService(CourierRepository courierRepository) {
		this.courierRepository = courierRepository;
	}
	public List<Courier> getCourier() {
		return courierRepository.findAll();
	}

	@Transactional

	public Long addNewCourier(Courier courier) {
		Optional<Courier> courierOptionalId = courierRepository.findCourierById(courier.courierId);
		while(courierOptionalId.isPresent()){
			courier.courierId++;
			courierOptionalId = courierRepository.findCourierById(courier.courierId);
		}
		entityManager.createNativeQuery("INSERT INTO courier (id ,password, name, email, phone, application_reason, approved, available,  type, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
				.setParameter(1, courier.courierId)
				.setParameter(2, courier.getPassword())
				.setParameter(3, courier.getName())
				.setParameter(4, courier.getEmail())
				.setParameter(5, courier.getPhone())
				.setParameter(6,courier.getApplicationReason())
				.setParameter(7,false)
				.setParameter(8, false)
				.setParameter(9,"Courier")
				.setParameter(10, courier.getSalary())
				.executeUpdate();
		courier.courierId++;
		Long temp = courier.courierId - 1;
		return temp;
	}
	public void deleteCourier(Long courierId) {
		Optional<Courier> courierOptionalId = courierRepository.findCourierById(courierId);
		if (!courierOptionalId.isPresent()) {
			throw new IllegalStateException("A courier with that ID does not exist.");
		}
		courierRepository.deleteById(courierId);
	}

	//TODO make update courier complete
	@Transactional
	public void updateCourier(Long courierId,boolean approved){
		Courier courier = courierRepository.findById(courierId).orElseThrow(() -> new IllegalStateException("A customer with that ID does not exist."));
		if (approved) {
			courier.setApproved(approved);
		}
	}

}
