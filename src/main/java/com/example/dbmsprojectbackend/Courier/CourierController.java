package com.example.dbmsprojectbackend.Courier;

import com.example.dbmsprojectbackend.Customer.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courier")
@CrossOrigin
public class CourierController {
	public final CourierService courierService;
	private final CourierRepository courierRepository;

	public CourierController(CourierService courierService, CourierRepository courierRepository) {
		this.courierService = courierService;
		this.courierRepository = courierRepository;
	}

	@GetMapping
	public List<Courier> getCouriers() {
		return courierService.getCourier();
	}

	@PostMapping
	public Long addNewCourier(@RequestBody Courier courier) {
		return courierService.addNewCourier(courier);
	}

	@DeleteMapping(path = "{courierId}")
	public Courier deleteCustomer(@PathVariable("courierId") Long courierId) {
		courierService.deleteCourier(courierId);
		Courier courier;
		return courier = courierRepository.findById(courierId).orElseThrow(() -> new IllegalStateException("A courier with that ID does not exist."));
	}

	@PutMapping(path = "{courierId}")
	public Courier updateCourier(
			@PathVariable("courierId") Long courierId,
			@RequestBody Courier courier) {
		courierService.updateCourier(courierId, courier.isApproved());
	return courier;
	}

}
