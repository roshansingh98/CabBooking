package com.cg.mts.controller;

import com.cg.mts.entities.TripBooking;
import com.cg.mts.service.ITripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* This would signify we are in tripbooking controller */
@RequestMapping("/tripbooking")
@RestController
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
public class TripBookingController {

    /*
     * ITripBookingService is a interface, whose reference we are trying to
     * call. @Autowired will dynamically create a object for it at run time,
     * avoiding tight coupling
     */
    @Autowired
    private ITripBookingService tripBookingService;

    /*
     * This method will add a tripbooking object to the driver table. and return the
     * driver object added back to the server
     */
    @PostMapping("/add")
    public TripBooking add(@RequestBody TripBooking tripBooking) {
        tripBooking = tripBookingService.insertTripBooking(tripBooking);
        return tripBooking;
    }

    /*
     * This method will update a tripbooking object whose id matches with the one we
     * are passing. Once it updates, it will return the updated object back to the
     * server
     */
    @PutMapping("/update/{id}")
    public TripBooking update(@PathVariable("id") int tripBookingId, @RequestBody TripBooking tripBooking) {
        tripBooking.setTripBookingId(tripBookingId);
        tripBooking = tripBookingService.updateTripBooking(tripBooking);
        return tripBooking;
    }

    /*
     * This method will delete a tripbooking object from the database table, which
     * we have passed in the method
     */
    @DeleteMapping("/delete")
    public void delete(@RequestBody TripBooking tripBooking) {
        tripBooking = tripBookingService.deleteTripBooking(tripBooking);
    }

    /* This will return a list of Tripbookings based on customer id */
    @GetMapping("/get/customerWiseTrips/{id}")
    public List<TripBooking> getTrips(@PathVariable("id") int customerId) {
        List<TripBooking> trips = tripBookingService.viewAllTripsCustomer(customerId);
        return trips;
    }

    /*
     * This will return the bill of the trip booking of the customer whose customer
     * id we will pass in the url
     */
    @GetMapping("/get/calculateBill/{id}")
    public Float calculateBill(@PathVariable("id") int customerId) {
        TripBooking tripBooking = tripBookingService.calculateBill(customerId);
        float bill = tripBooking.getBill();
        return bill;
    }
}
