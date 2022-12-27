package com.example.dbmsprojectbackend.Package;

import com.example.dbmsprojectbackend.Customer.Customer;
import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.PaymentDetails.PaymentDetails;
import com.example.dbmsprojectbackend.Recipient.Recipient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PackageService {

    @PersistenceContext
    private EntityManager entityManager;
    private final PackageRepository packageRepository;

    @Autowired
    public PackageService(PackageRepository packageRepository) { this.packageRepository = packageRepository; }

    public List<Package> getPackages() {
        return packageRepository.findAll();
    }

    public List<Package> getPackageBySenderId(Long senderId) {
        return packageRepository.findPackageBySenderId(senderId);
    }

    public List<Package> getActivePackages(Long senderId) {
        return entityManager.createNativeQuery("SELECT * FROM package p WHERE p.id = ? AND p.status <> ? and p.status <> ?")
                .setParameter(1, senderId)
                .setParameter(2, "delivered")
                .setParameter(3, "cancelled")
                .getResultList();
    }

    public List<Package> getInactivePackages(Long senderId) {
        return entityManager.createNativeQuery("SELECT * FROM package p WHERE p.id = ? AND p.status = ? and p.status = ?")
                .setParameter(1, senderId)
                .setParameter(2, "delivered")
                .setParameter(3, "cancelled")
                .getResultList();
    }

    @Transactional
    public void addNewPackage(Package pack, Long customer) {
//        Optional<Package> packageOptional = packageRepository.findPackageById(pack.getId());
//        if (packageOptional.isPresent()) {
//            throw new IllegalStateException("A package with that ID already exists.");
//        }
        Optional<Package> recipientOptionalId = packageRepository.findPackageById(pack.packageId);
        while(recipientOptionalId.isPresent()){
            pack.packageId++;
            recipientOptionalId = packageRepository.findPackageById(pack.packageId);
        }
        entityManager.createNativeQuery("INSERT INTO package (id, volume, weight, type, delivery_status, sent_by) VALUES (?, ?, ?, ?, ?, ?)")
                .setParameter(1, pack.packageId)
                .setParameter(2, pack.getVolume())
                .setParameter(3, pack.getWeight())
                .setParameter(4, pack.getType())
                .setParameter(5, "To Be Assigned")
                .setParameter(6, customer)
                .executeUpdate();
        pack.packageId++;
    }

    public int getPricePackage(Long packageId) {
        Optional<Package> packageOptional = packageRepository.findPackageById(packageId);
        if (!packageOptional.isPresent()) {
            throw new IllegalStateException("A package with that ID does not exist.");
        }
        Package pack = packageOptional.get();
        pack.setPrice();
        return pack.getPrice();
    }

    @Transactional
    public void deletePackage(Long packageId) {
        Optional<Package> packageOptional = packageRepository.findPackageById(packageId);
        if (!packageOptional.isPresent()) {
            throw new IllegalStateException("A package with that ID does not exist.");
        }
        packageRepository.deleteById(packageId);
    }

    @Transactional
    public void setPackagePrice(Long packageId) {
        Optional<Package> packageOptional = packageRepository.findPackageById(packageId);
        if (!packageOptional.isPresent()) {
            throw new IllegalStateException("A package with that ID does not exist.");
        }
        Package pack = packageOptional.get();
        pack.setPrice();
    }

    @Transactional
    public void updatePackage(Long packageId, Double volume, Double weight, String type) {
        Optional<Package> packageOptional = packageRepository.findPackageById(packageId);
        if (!packageOptional.isPresent()) {
            throw new IllegalStateException("A package with that ID does not exist.");
        }
        Package pack = packageOptional.get();
        if (volume != null && volume > 0 && !Objects.equals(volume.doubleValue(), pack.getVolume())) {
            pack.setVolume(volume.doubleValue());
        }
        if (weight != null && weight > 0 && !Objects.equals(weight.doubleValue(), pack.getWeight())) {
            pack.setVolume(weight.doubleValue());
        }
        pack.setPrice();
        if (type != null && type.length() != 0 && !Objects.equals(type, pack.getType())) {
            pack.setType(type);
        }
    }
}
