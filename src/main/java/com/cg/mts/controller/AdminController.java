package com.cg.mts.controller;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/add")
    public Admin add(@RequestBody Admin admin) {
        return adminService.insertAdmin(admin);
    }

    @PutMapping("/update/{id}")
    public Admin update(@PathVariable("id") int adminId, @RequestBody Admin newAdmin) {
        newAdmin.setAdminId(adminId);
        newAdmin = adminService.updateAdmin(newAdmin);
        return newAdmin;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int adminId) {
        Admin admin = adminService.deleteAdmin(adminId);
    }

    @GetMapping("/get/tripsBooking/{id}")
    public List<TripBooking> getTripBooking(@PathVariable("id") int id) {
        List<TripBooking> trp = adminService.getAllTrips(id);
        return trp;
    }

    @GetMapping("/get/getCabWise")
    public List<TripBooking> getCabWise(){
        List<TripBooking> trp = adminService.getTripsCabwise();
        return trp;
    }

    @GetMapping("/get/getTripsCustomerwise")
    public List<TripBooking> getCustomerWise() {
        List<TripBooking> trp = adminService.getTripsCustomerwise();
        return trp;
    }

    @GetMapping("/get/getTripsDatewise")
    public List<TripBooking> getTripsDatewise() {
        List<TripBooking> trp = adminService.getTripsDatewise();
        return trp;
    }

    @GetMapping("/get/getAllTripsForDays/{id}/{date1}/{date2}")
    public List<TripBooking> getAllTripsForDays(@PathVariable("id") int customerId, @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
        List<TripBooking> trp = adminService.getAllTripsForDays(customerId, fromDateTime, toDateTime);
        return trp;
    }
}

