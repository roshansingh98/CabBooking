package com.cg.mts.controller;

import com.cg.mts.entities.TripBooking;
import com.cg.mts.service.ITripBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tripbooking")
@RestController
public class TripBookingController {

    @Autowired
    private ITripBookingService tripBookingService;

    @PostMapping("/add")
    public TripBooking add(@RequestBody TripBooking tripBooking) {
        tripBooking = tripBookingService.insertTripBooking(tripBooking);
        return tripBooking;
    }

    @PutMapping("/update/{id}")
    public TripBooking update(@PathVariable("id") int tripBookingId, @RequestBody TripBooking tripBooking) {
        tripBooking.setTripBookingId(tripBookingId);
        tripBooking = tripBookingService.updateTripBooking(tripBooking);
        return tripBooking;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody TripBooking tripBooking) {
        tripBooking = tripBookingService.deleteTripBooking(tripBooking);
    }

    @GetMapping("/get/customerWiseTrips/{id}")
    public List<TripBooking> getTrips(@PathVariable("id") int customerId){
        List<TripBooking> trips = tripBookingService.viewAllTripsCustomer(customerId);
        return trips;
    }

    @GetMapping("/get/calculateBill/{id}")
    public Float calculateBill(@PathVariable("id") int customerId) {
        TripBooking tripBooking = tripBookingService.calculateBill(customerId);
        float bill = tripBooking.getBill();
        return bill;
    }
}
