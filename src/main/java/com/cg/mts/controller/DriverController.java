package com.cg.mts.controller;

import com.cg.mts.entities.Driver;
import com.cg.mts.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/driver")
@RestController
public class DriverController {

    @Autowired
    private IDriverService driverService;

    @PostMapping("/add")
    public Driver add(@RequestBody Driver driver) {
        driver = driverService.insertDriver(driver);
        return driver;
    }

    @PutMapping("/update/{id}")
    public Driver update(@PathVariable("id") int driverId, @RequestBody Driver newDriver) {
        newDriver.setDriverId(driverId);
        newDriver = driverService.updateDriver(newDriver);
        return newDriver;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int driverId) {
        Driver driver = driverService.deleteDriver(driverId);
    }

    @GetMapping("/get/{id}")
    public Driver getDriver(@PathVariable("id") int driverId) {
        Driver driver = driverService.viewDriver(driverId);
        return driver;
    }

    @GetMapping("/get/viewBestDrivers")
    public List<Driver> viewBestDrivers(){
        List<Driver> bestDrivers = driverService.viewBestDrivers();
        return bestDrivers;
    }
}
