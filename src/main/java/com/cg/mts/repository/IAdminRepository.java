package com.cg.mts.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier
public interface IAdminRepository extends JpaRepository<Admin , Integer> {

    @Query(value ="select trb from TripBooking trb where trb.customerId=:customerId" )
    public List<TripBooking> getAllTrips(@Param("customerId")int customerId) throws CustomerNotFoundException;

    @Query(value = "SELECT tr FROM TripBooking tr where tr.driver.driverId in(select dr.driverId from Driver dr where dr.cab.cabId in (select c.cabId from Cab c group by c.cabId))")
    public List<TripBooking> getTripsCabwise()
            throws CabNotFoundException;

    @Query(value = "SELECT tr from TripBooking tr where tr.customerId in(select trb.customerId from TripBooking trb where trb.tripBookingId = tr.tripBookingId group by trb.customerId)")
    public List<TripBooking> getTripsCustomerwise();

    @Query(value = "select tr from TripBooking tr where tr.fromDateTime in(select trb.fromDateTime from TripBooking trb group by trb.fromDateTime)")
    public List<TripBooking> getTripsDatewise();

    @Query(value = "select e from TripBooking e where e.customerId = :customerId and e.fromDateTime = :fromdatetime and e.toDateTime = :todatetime")
    public List<TripBooking> getAllTripsForDays(@Param("customerId") int customerId, @Param("fromdatetime") LocalDateTime fromDate,@Param("todatetime") LocalDateTime toDate) throws
            CustomerNotFoundException;

}