package com.cg.mts.dao;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AdminDao implements IAdminRepository {

    @PersistenceContext
    private EntityManager entityManager;

//    public AdminDao(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public Admin insertAdmin(Admin admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminNotFoundException {

        Admin currentAdmin = entityManager.find(Admin.class, admin.getAdminId());

        if (currentAdmin == null) {
            throw new AdminNotFoundException("Cant update! Admin not found");
        }

        admin = entityManager.merge(admin);

        return admin;
    }

    @Override
    public Admin deleteAdmin(int adminId) throws AdminNotFoundException {

        Admin admin = entityManager.find(Admin.class, adminId);

        if (admin == null) {
            throw new AdminNotFoundException("Cant update! Admin with the respective Admin Id not found");
        }

        entityManager.remove(admin);
        return admin;
    }

    @Override
    public List<TripBooking> getAllTrips(int customerId) throws CustomerNotFoundException {

        List<TripBooking> trips = entityManager.createNamedQuery("find tripbooking by customerId", TripBooking.class)
                .setParameter("customerId", customerId).getResultList();

        if (trips.size() == 0) {
            throw new CustomerNotFoundException("No trip data is found!");
        }

        return trips;
    }

    @Override
    public List<TripBooking> getTripsCabwise() throws CabNotFoundException {

        List<TripBooking> cabWiseTrip = entityManager.createQuery(
                "SELECT tr FROM TripBooking tr where tr.driver.driverId in(select dr.driverId from Driver dr where dr.cab.cabId in (select c.cabId from Cab c group by c.cabId))",
                TripBooking.class).getResultList();

        if (cabWiseTrip.size() == 0) {
            throw new CabNotFoundException("No cabs found");
        }

        return cabWiseTrip;
    }

    @Override
    public List<TripBooking> getTripsCustomerwise() {

        List<TripBooking> trips = entityManager
                .createQuery("SELECT tr from TripBooking tr group by tr.customerId", TripBooking.class).getResultList();

        return trips;
    }

    @Override
    public List<TripBooking> getTripsDatewise() {
        List<TripBooking> tripDateWise = entityManager
                .createQuery("select tr from TripBooking tr group by tr.fromDateTime", TripBooking.class).getResultList();
        return tripDateWise;
    }

    @Override
    public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws CustomerNotFoundException {

        //List<Customer> list = entityManager.createQuery("Select a from tripbooking where customerId")

        if (entityManager.find(Customer.class, customerId) == null) {
            throw new CustomerNotFoundException("Cant find the customer with the given ID");
        }

        List<TripBooking> allTripForDays = entityManager
                .createNamedQuery("find tripbooking by multiple", TripBooking.class)
                .setParameter("customerId", customerId).setParameter("fromdatetime", fromDate)
                .setParameter("todatetime", toDate).getResultList();
        return allTripForDays;
    }


    /*
    Commented as need can be sufficed in the respective function itself
    private boolean checkExists(int adminId) {
        Admin admin = entityManager.find(Admin.class, adminId);
        boolean result = admin != null;
        return result;
    }*/
}
