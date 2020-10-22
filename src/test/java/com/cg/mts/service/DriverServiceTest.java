package com.cg.mts.service;

import com.cg.mts.entities.Cab;
import com.cg.mts.entities.Driver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {

    Cab cab = new Cab("hatch", 15);
    Cab cab1 = new Cab("sedan", 20);
    Cab cab2 = new Cab("SUV", 25);
    Cab cab3 = new Cab("sedan", 25);
    Cab cab4 = new Cab("sedan", 25);

    @Test
    public void testForCrudOp() {

        Driver driver = new Driver("username", "password", "9600456781", "asd@fdhg", "XYZ", cab, 4.6f);
        Driver driver1 = new Driver("userShort", "password", "9600456781", "asd@fdhg", "XYZ", cab1, 4.0f);
        Driver driver2 = new Driver("usernam", "password", "9600456781", "asd@fdhg", "XYZ", cab2, 4.9f);

        IDriverService driverService = new DriverService();

        assertEquals(driver, driverService.insertDriver(driver));
        driverService.insertDriver(driver1);
        driverService.insertDriver(driver2);

        Driver driver3 = new Driver("updatedUser", "password", "9600456781", "asd@fdhg", "XYZ", cab, 4.6f);
        driver3.setDriverId(driver.getDriverId());
        assertEquals("updatedUser", driverService.updateDriver(driver3).getUsername());

        driverService.deleteDriver(driver1.getDriverId());
        //it checks for successful deletion by trying to delete the same element again which isnt there
        assertEquals(0, driverService.deleteDriver(driver1.getDriverId()).getDriverId());

    }

    @Test
    public void testForGetBestDrivers() {

        Driver driver = new Driver("username", "password", "9600456781", "asd@fdhg", "XYZ", cab, 4.6f);
        Driver driver1 = new Driver("userShort", "password", "9600456781", "asd@fdhg", "XYZ", cab1, 4.0f);
        Driver driver2 = new Driver("usernam", "password", "9600456781", "asd@fdhg", "XYZ", cab2, 4.9f);

        IDriverService driverService = new DriverService();

        driverService.insertDriver(driver);
        driverService.insertDriver(driver1);
        driverService.insertDriver(driver2);

        assertEquals(2, driverService.viewBestDrivers().size());
    }

    @Test
    public void testForViewDriver() {

        Driver driver = new Driver("username", "password", "9600456781", "asd@fdhg", "XYZ", cab, 4.6f);
        Driver driver1 = new Driver("userShort", "password", "9600456781", "asd@fdhg", "XYZ", cab1, 4.0f);
        Driver driver2 = new Driver("usernam", "password", "9600456781", "asd@fdhg", "XYZ", cab2, 4.9f);

        IDriverService driverService = new DriverService();

        driverService.insertDriver(driver);
        driverService.insertDriver(driver1);
        driverService.insertDriver(driver2);

        assertEquals("userShort", driverService.viewDriver(driver1.getDriverId()).getUsername());

    }

}