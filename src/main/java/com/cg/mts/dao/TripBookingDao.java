package com.cg.mts.dao;

import com.cg.mts.entities.TripBooking;
import com.cg.mts.repository.ITripBookingRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TripBookingDao implements ITripBookingRepository {

    private EntityManager entityManager;

    public TripBookingDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TripBooking insertTripBooking(TripBooking tripBooking) {
        entityManager.persist(tripBooking);
        return tripBooking;
    }

    @Override
    public TripBooking updateTripBooking(TripBooking tripBooking) {
        boolean success = checkExists(tripBooking.getTripBookingId());
        tripBooking = entityManager.merge(tripBooking);
        return tripBooking;
    }


    @Override
    public TripBooking deleteTripBooking(TripBooking tripBooking) {
        boolean success = checkExists(tripBooking.getTripBookingId());
        if (success) {
            entityManager.remove(tripBooking);
        }
        return tripBooking;
    }

    @Override
    public List<TripBooking> viewAllTripsCustomer(int customerId) {
        List<TripBooking> allTrips = entityManager.createNamedQuery("find tripbooking by customerId", TripBooking.class)
                .setParameter("customerId", customerId).getResultList();
        return allTrips;
    }

    @Override
    public TripBooking calculateBill(int customerId) {
        TripBooking tripBooking = entityManager.find(TripBooking.class, customerId);
        return tripBooking;
    }

    private boolean checkExists(Integer tripbookingid) {
        TripBooking tripBooking = entityManager.find(TripBooking.class, tripbookingid);
        boolean result = tripBooking != null;
        return result;
    }
}
