package com.cg.mts.controller;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Validated
/* This would signify that we are in admin controller right now */
@RequestMapping("/admin")
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
@RestController
public class AdminController {

    /*
     * IAdminService is a interface, whose reference we are trying to
     * call. @Autowired will dynamically create a object for it at run time,
     * avoiding tight coupling
     */
    @Autowired
    private IAdminService adminService;

    /*
     * This method will add a new admin object to the database, and return the added
     * object to the server
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Admin add(@RequestBody @NotNull(message = "") Admin admin) {
        return adminService.insertAdmin(admin);
    }


    /*
     * This method will update the admin object, to a new admin object and return
     * the updated object to the server
     */
    @PutMapping("/update/{id}")
    public Admin update(@PathVariable("id") int adminId, @RequestBody Admin newAdmin) {
        newAdmin.setAdminId(adminId);
        newAdmin = adminService.updateAdmin(newAdmin);
        return newAdmin;
    }


    /*
     * This method will delete the admin object from database whose admin id matches
     * with the one we have passed
     */
    @DeleteMapping("/delete/{id}")
    public void delete  (@PathVariable("id") @NotNull int adminId) {
        Admin admin = adminService.deleteAdmin(adminId);
    }


    /*
     * This method will return a list of tripbookings from the table where id
     * matches with the one we have passed
     */
    @GetMapping("/get/tripsBooking/{id}")
    public List<TripBooking> getTripBooking(@PathVariable("id") int id) {
        List<TripBooking> trp = adminService.getAllTrips(id);
        return trp;
    }

    /* This method will return a list of TripBooking based on each cabType */
    @GetMapping("/get/getCabWise")
    public List<TripBooking> getCabWise(){
        List<TripBooking> trp = adminService.getTripsCabwise();
        return trp;
    }

    /* This method will return a list of TripBookings group based on customers */
    @GetMapping("/get/getTripsCustomerwise")
    public List<TripBooking> getCustomerWise() {
        List<TripBooking> trp = adminService.getTripsCustomerwise();
        return trp;
    }

    /* This method will return a list of TripBookings based on Trip Dates */
    @GetMapping("/get/getTripsDatewise")
    public List<TripBooking> getTripsDatewise() {
        List<TripBooking> trp = adminService.getTripsDatewise();
        return trp;
    }


    /*
     * This method will return a list of TripBookings based on the customerId,
     * fromDate, and toDate
     */
    @GetMapping("/get/getAllTripsForDays/{id}/{date1}/{date2}")
    public List<TripBooking> getAllTripsForDays(@PathVariable("id") int customerId, @PathVariable("date1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDateTime, @PathVariable("date2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDateTime){
        List<TripBooking> trp = adminService.getAllTripsForDays(customerId, fromDateTime, toDateTime);
        return trp;
    }
}

