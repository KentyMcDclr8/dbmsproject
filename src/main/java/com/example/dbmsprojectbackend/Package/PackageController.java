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

    @PostMapping(path = "/price")
    public int getPackagePrice(@RequestBody PriceCalcHelper pch) {

        return (int) ((pch.getWeight()/2) + pch.getVolume());
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
            @RequestBody Package pack) {
        packageService.updatePackage(packageId, pack.getVolume(), pack.getWeight(), pack.getType(), pack.getDeliveryStatus());
        return pack;
    }

    @PutMapping(path = "price/{packageId}")
    public Package setPackagePrice(
            @PathVariable("packageId") Long packageId) {
        packageService.setPackagePrice(packageId);
        Package pack;
        return  pack = packageRepository.findPackageById(packageId).orElseThrow(() -> new IllegalStateException("A package with that ID does not exist."));

    }

    @GetMapping(path = "filter/{packageId}")
    public List<Package> getPackageFiltered(
            @PathVariable("packageId") Long packageId,
            @RequestParam int weight1,
            @RequestParam int weight2
            ) {

        return packageService.getPackageFilter(packageId, weight1, weight2);
    }
}
