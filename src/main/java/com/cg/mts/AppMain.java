package com.cg.mts;

import com.cg.mts.entities.*;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.service.*;
import com.cg.mts.util.Util;
import com.sun.corba.se.spi.activation.ServerOperations;

import java.time.LocalDateTime;
import java.util.List;


public class AppMain {

    Cab cab = new Cab("Hatch", 15);
    Cab cab1 = new Cab("SUV", 15);
    Cab cab2 = new Cab("Sedan", 15);


    public static void main(String[] args) {

        AppMain appMain = new AppMain();
//        appMain.executeCustomerService();
//        appMain.executeCabServices();
//        appMain.executeDriverService();
        appMain.executeAdminServices();

        Util util = Util.getInstance();
        util.close();
    }

    private void executeDriverService() {

        /*Cab cab = new Cab("ABC", 1.0f);
        Driver driver = new Driver("cab", "cab1", "Cab2", "Cab3", "Cab4", cab, 4.5f);
        IDriverService driverService = new DriverService();
        driverService.insertDriver(driver);
        driverService.deleteDriver(6);
        Driver driver2 = new Driver("cab", "cab1", "Cab3", "Cab3", "Cab3", cab, 4.0f);
        driver2.setDriverId(driver.getDriverId());
        driverService.updateDriver(driver2);
        List<Driver> drivers = driverService.viewBestDrivers();
        for(Driver dd: drivers) {
            System.out.println(dd.getEmail());
        }
        drivers = driverService.viewBestDrivers();
        Driver ddd = driverService.viewDriver(driver2.getDriverId());
        System.out.print(ddd.getLicenseNo());*/

        Cab cab = new Cab("Hatch", 15);
        Cab cab1 = new Cab("SUV", 15);
        Cab cab2 = new Cab("Sedan", 15);

        Driver driver = new Driver("Uncle", "driver", "45845", "sfd", "fdsfs", cab, 4);
        Driver driver1 = new Driver("Uncle1", "driverUnc", "45845", "sfd", "fdsfs", cab1, 4);
        Driver driver2 = new Driver("Uncle2", "driver2", "45845", "sfd", "fdsfs", cab2, 4);

        IDriverService driverService = new DriverService();
        driverService.insertDriver(driver);
        driverService.insertDriver(driver1);
        driverService.insertDriver(driver2);

    }

    private void executeCabServices() {

        ICabService cabService = new CabService();
        Cab one = cabService.insertCab(cab);
        Cab two = cabService.insertCab(cab1);
        Cab three = cabService.insertCab(cab2);

    }

    private void executeAdminServices() {

        Admin admin = new Admin("Admin", "123", "12345", "abcde");
        Admin admin1 = new Admin("Admin1", "123", "12345", "abc");
        Admin admin2 = new Admin("Admin2", "123", "12345", "abc");

        //Use of insertion
        IAdminService adminService = new AdminService();
        adminService.insertAdmin(admin);
        adminService.insertAdmin(admin1);
        adminService.insertAdmin(admin2);

        //Use of updating
        Admin updateAdmin = new Admin("Updated admin", "1654", "4587", "khagdja");
        updateAdmin.setAdminId(admin.getAdminId());
        adminService.updateAdmin(updateAdmin);

        //Use of deletion
        adminService.deleteAdmin(admin1.getAdminId());

        //Use of getAllTrips
        Customer customer = new Customer("Cust1", "pass", "dsdcsd", "cscdsc");
        ICustomerService customerService = new CustomerService();
        customer = customerService.insertCustomer(customer);

        LocalDateTime localDateTime = LocalDateTime.now();
        Cab cab = new Cab("Hatch", 15);
        Driver driver = new Driver("Uncle", "driver", "45845", "sfd", "fdsfs", cab, 4);

        TripBooking tripBooking = new TripBooking(4, driver, "Mumbai", "Delhi", localDateTime, localDateTime.plusDays(3), true, 1200, 10000);
        TripBooking tripBooking2 = new TripBooking(4, driver, "Delhi", "Mumbai", localDateTime.minusDays(5), localDateTime.plusDays(10), true, 1200, 10000);
        ITripBookingService tripBookingService = new TripBookingService();
        tripBookingService.insertTripBooking(tripBooking);
        tripBookingService.insertTripBooking(tripBooking2);

        System.out.println(adminService.getAllTrips(customer.getCustomerId()));

        //Use of getAllTripsCabWise
        System.out.println(adminService.getTripsCabwise());

        //Use of getAllTripsDayWise
        System.out.println("id" + customer.getCustomerId());
        System.out.println("For days: " + adminService.getAllTripsForDays(4, localDateTime, localDateTime.plusDays(3)));
    }

    public void executeCustomerService() {

        Customer customer = new Customer("Customer", "123", "123456", "abc");
        Customer customer1 = new Customer("Customer1", "123", "123456", "abc");
        Customer customer2 = new Customer("Customer2", "123", "123456", "abc");


        ICustomerService customerService = new CustomerService();
        Customer c = customerService.insertCustomer(customer);
        Customer c1 = customerService.insertCustomer(customer1);
        Customer c2 = customerService.insertCustomer(customer2);


        System.out.println("New customer has" + c.getUsername());
        System.out.println("New customer with id 1 as: " + customerService.viewCustomer(1).getUsername());

//        List<Customer> list = customerService.viewCustomers();
//
//        for (Customer list1 : list)
//            System.out.println("The whole customer list is:" + list1.getUsername());
        //System.out.println("New customer deleted is " + customerService.deleteCustomer(c).getUsername());


    }
}
