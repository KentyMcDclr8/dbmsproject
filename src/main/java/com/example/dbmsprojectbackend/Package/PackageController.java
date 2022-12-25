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

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping
    public List<Package> getPackages() {
        return packageService.getPackages();
    }

    @PostMapping(path = "{customerId}")
    public void addNewPackage(@RequestBody Package pack, @PathVariable Long customerId) { packageService.addNewPackage(pack, customerId); }

    @DeleteMapping(path = "{packageId}")
    public void deletePackage(@PathVariable("packageId") Long packageId) {
        packageService.deletePackage(packageId);
    }

    @PutMapping(path = "{packageId}")
    public void updateEmployee(
            @PathVariable("packageId") Long packageId,
            @RequestParam(required = false) Double volume,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) String type) {
        packageService.updatePackage(packageId, volume, weight, type);
    }

    @PutMapping(path = "price/{packageId}")
    public void setPackagePrice(
            @PathVariable("packageId") Long packageId) {
        packageService.setPackagePrice(packageId);
    }
}
