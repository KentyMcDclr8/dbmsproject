package com.example.dbmsprojectbackend.Package;

import com.example.dbmsprojectbackend.Employee.Employee;
import com.example.dbmsprojectbackend.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("package")
@CrossOrigin
public class PackageController {

    private final PackageService packageService;
    private final PackageRepository packageRepository;

    @Autowired
    public PackageController(PackageService packageService,
                             PackageRepository packageRepository) {
        this.packageService = packageService;
        this.packageRepository = packageRepository;
    }

    @GetMapping
    public List<Package> getPackages() {
        return packageService.getPackages();
    }

    @GetMapping(path = "{senderId}")
    public List<Package> getPackagesBySenderId(@PathVariable("senderId") Long senderId) {
        return packageService.getPackageBySenderId(senderId);
    }

    @GetMapping(path = "{senderId}/active")
    public List<Package> getActivePackages(@PathVariable("senderId") Long senderId) {
        return packageService.getActivePackages(senderId);
    }

    @GetMapping(path = "{senderId}/inactive")
    public List<Package> getInactivePackages(@PathVariable("senderId") Long senderId) {
        return packageService.getInactivePackages(senderId);
    }

    @GetMapping(path = "/price")
    public int getPackagePrice(@RequestBody Package pack) {
        pack.setPrice();
        return pack.getPrice();
    }

    @PostMapping(path = "{customerId}")
    public Package addNewPackage(@RequestBody Package pack, @PathVariable Long customerId) { packageService.addNewPackage(pack, customerId);
    return pack;
    }

    @DeleteMapping(path = "{packageId}")
    public Package deletePackage(@PathVariable("packageId") Long packageId) {
        packageService.deletePackage(packageId);
        Package pack;
        return  pack = packageRepository.findPackageById(packageId).orElseThrow(() -> new IllegalStateException("A package with that ID does not exist."));

    }

    @PutMapping(path = "{packageId}")
    public Package updateEmployee(
            @PathVariable("packageId") Long packageId,
            @RequestParam(required = false) Double volume,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) String type) {
        packageService.updatePackage(packageId, volume, weight, type);
        Package pack;
        return  pack = packageRepository.findPackageById(packageId).orElseThrow(() -> new IllegalStateException("A package with that ID does not exist."));

    }

    @PutMapping(path = "price/{packageId}")
    public Package setPackagePrice(
            @PathVariable("packageId") Long packageId) {
        packageService.setPackagePrice(packageId);
        Package pack;
        return  pack = packageRepository.findPackageById(packageId).orElseThrow(() -> new IllegalStateException("A package with that ID does not exist."));

    }
}
