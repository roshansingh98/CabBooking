package com.cg.mts.dao;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.IAdminRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class AdminDao implements IAdminRepository {

    EntityManager entityManager;

    public AdminDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminNotFoundException {

        Boolean success = checkExists(admin.getMobileNumber());

        if (!success) {
            throw new AdminNotFoundException("Cant update! Admin with the respective mobile number not found");
        }

        admin = entityManager.merge(admin);

        return admin;
    }

    @Override
    public Admin deleteAdmin(int adminId) throws AdminNotFoundException {

        Admin admin = entityManager.find(Admin.class, adminId);

        if (admin == null) {
            throw new AdminNotFoundException("Cant update! Admin with the respective mobile number not found");
        }

        entityManager.remove(admin);
        return admin;
    }

    @Override
    public List<TripBooking> getAllTrips(int customerId) throws CustomerNotFoundException {

        List<TripBooking> li = entityManager.createQuery("SELECT * FROM tripbooking tr WHERE tr.customerId = 'customerID'", TripBooking.class).setParameter("customerID", customerId).getResultList();

        if (li == null) {
            throw new CustomerNotFoundException("No customer data is found!!");
        }

        return null;
    }

    @Override
    public List<TripBooking> getTripsCabwise() throws CabNotFoundException {

        List<TripBooking> li = entityManager.createQuery("SELECT * FROM tripbooking tr WHERE driver_driverid in (select driverid from driver where cab_cabid in (select cabid from cab group by cabid order by cabid))", TripBooking.class).getResultList();

        if (li == null) {
            throw new CabNotFoundException("No cabs found");
        }

        return li;
    }

    @Override
    public List<TripBooking> getTripsCustomerwise() {

        List<TripBooking> li = entityManager.createQuery("SELECT * from tripbooking tr where customerid in (select customerid from customer group by customerid order by customerid)", TripBooking.class).getResultList();

        return li;
    }

    @Override
    public List<TripBooking> getTripsDatewise() {
        List<TripBooking> li = entityManager.createQuery("select tr from tripbooking tr group by fromdatetime", TripBooking.class).getResultList();
        return li;
    }

    @Override
    public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws CustomerNotFoundException {

        Customer customer = entityManager.find(Customer.class, customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Cant find the customer with the given ID");
        }

        List<TripBooking> li = entityManager.createQuery("Select tr from tripbooking tr where customerId = 'customerID' and fromdatetime = 'fromdatetime' and todatetime = 'todatetime'", TripBooking.class).getResultList();

        return li;
    }


    private boolean checkExists(String mobileNumber) {
        Customer customer = entityManager.find(Customer.class, mobileNumber);
        boolean result = customer != null;
        return result;
    }
}
