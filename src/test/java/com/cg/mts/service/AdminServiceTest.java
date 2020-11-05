package com.cg.mts.service;

import com.cg.mts.entities.*;
import com.cg.mts.util.PersistanceUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    Admin admin = new Admin("firstAdmin", "123456", "9600456781", "abcde@gmail.com");
    Admin admin1 = new Admin("Admin1", "123456", "9600456782", "abc@gmail.com");
    Admin admin2 = new Admin("Admin2", "123456", "9600456783", "abc@gmail.com");

    IAdminService adminService = new AdminService();

    @Test
    public void testForCorrectInsertion() {
        //Admin admin = new Admin("firstAdmin", "123456", "9660456783", "abcde@gmail.com");
        assertEquals(admin, adminService.insertAdmin(admin));
        assertNotEquals(admin1, adminService.insertAdmin(admin));
    }

    @Test
    public void testForCorrectUpdating() {
        adminService.insertAdmin(admin);
        adminService.insertAdmin(admin1);
        adminService.insertAdmin(admin2);

        Admin updateAdmin = new Admin("Updated admin", "123456", "9600456781", "khagdja@gmail.com");
        updateAdmin.setAdminId(admin.getAdminId());
        //System.out.println("Updated here" + adminService.updateAdmin(updateAdmin).getAdminId());
        assertEquals(1, adminService.updateAdmin(updateAdmin).getAdminId());
    }

    @Test
    public void testForCorrectDeletion() {
        adminService.insertAdmin(admin);
        adminService.insertAdmin(admin1);
        adminService.insertAdmin(admin2);

        adminService.deleteAdmin(2);
        //it checks for successful deletion by trying to delete the same element again which isnt there
        assertEquals(0, adminService.deleteAdmin(2).getAdminId());
    }

    @Test
    public void testForGetAllTrips() {

        Customer customer = new Customer("Customer1", "password", "9600456781", "cscdsc@gmail.com");
        ICustomerService customerService = new CustomerService();
        customer = customerService.insertCustomer(customer);

        LocalDateTime localDateTime = LocalDateTime.now();
        Cab cab = new Cab("Hatch", 15);
        Driver driver = new Driver("UncleDriver", "driver", "9600456781", "sfd@gmail.com", "fdsfs", cab, 4);

        TripBooking tripBooking = new TripBooking(customer.getCustomerId(), driver, "Mumbai", "Delhi", localDateTime, localDateTime.plusDays(3), true, 1200, 10000);
        TripBooking tripBooking2 = new TripBooking(customer.getCustomerId(), driver, "Delhi", "Mumbai", localDateTime.minusDays(5), localDateTime.plusDays(10), true, 1200, 10000);
        ITripBookingService tripBookingService = new TripBookingService();
        tripBookingService.insertTripBooking(tripBooking);
        tripBookingService.insertTripBooking(tripBooking2);

        List<String> string = new ArrayList<>();

        for (TripBooking li : adminService.getAllTrips(customer.getCustomerId())) {
            string.add(li.getFromLocation());
        }

        assertArrayEquals(new String[]{"Mumbai", "Delhi"}, string.toArray());
        assertNotEquals(2, adminService.getAllTrips(customer.getCustomerId()).get(1).getCustomerId());
    }

    @Test
    public void testForGetAllTripsCabWise() {
        Customer customer = new Customer("Customer1", "password", "9600456781", "cscdsc@gmail.com");
        ICustomerService customerService = new CustomerService();
        customer = customerService.insertCustomer(customer);

        LocalDateTime localDateTime = LocalDateTime.now();
        Cab cab = new Cab("Hatch", 15);
        Driver driver = new Driver("UncleDriver", "driver", "9600456781", "sfd@gmail.com", "fdsfs", cab, 4);

        TripBooking tripBooking = new TripBooking(customer.getCustomerId(), driver, "Mumbai", "Delhi", localDateTime, localDateTime.plusDays(3), true, 1200, 10000);
        TripBooking tripBooking2 = new TripBooking(customer.getCustomerId(), driver, "Delhi", "Mumbai", localDateTime.minusDays(5), localDateTime.plusDays(10), true, 1200, 10000);
        ITripBookingService tripBookingService = new TripBookingService();
        tripBookingService.insertTripBooking(tripBooking);
        tripBookingService.insertTripBooking(tripBooking2);

        List<Integer> cabIdArray = new ArrayList<>();

        for (TripBooking li : adminService.getAllTrips(customer.getCustomerId())) {
            cabIdArray.add(li.getDriver().getCab().getCabId());
        }

        System.out.println(cabIdArray);
        assertArrayEquals(new Integer[]{4, 4}, cabIdArray.toArray());
    }

    @Test
    public void testForGetAllTripsForDays() {

        Customer customer = new Customer("Customer1", "password", "9600456781", "cscdsc@gmail.com");
        ICustomerService customerService = new CustomerService();
        customer = customerService.insertCustomer(customer);

        LocalDateTime localDateTime = LocalDateTime.now();
        Cab cab = new Cab("Hatch", 15);
        Driver driver = new Driver("UncleDriver", "driver", "9600456781", "sfd@gmail.com", "fdsfs", cab, 4);

        TripBooking tripBooking = new TripBooking(customer.getCustomerId(), driver, "Mumbai", "Delhi", localDateTime, localDateTime.plusDays(3), true, 1200, 10000);
        TripBooking tripBooking2 = new TripBooking(customer.getCustomerId(), driver, "Delhi", "Mumbai", localDateTime.minusDays(5), localDateTime.plusDays(10), true, 1200, 10000);
        ITripBookingService tripBookingService = new TripBookingService();
        tripBookingService.insertTripBooking(tripBooking);
        tripBookingService.insertTripBooking(tripBooking2);

//        System.out.println("For days: " + adminService.getAllTripsForDays(customer.getCustomerId(), localDateTime, localDateTime.plusDays(3)));
        assertEquals(1, adminService.getAllTripsForDays(customer.getCustomerId(), localDateTime, localDateTime.plusDays(3)).size());

    }

    @AfterAll
    static void closeUp() {
        PersistanceUtil persistanceUtil = PersistanceUtil.getInstance();
        persistanceUtil.close();
    }

}