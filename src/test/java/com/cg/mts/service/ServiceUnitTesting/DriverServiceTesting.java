package com.cg.mts.service.ServiceUnitTesting;

import com.cg.mts.entities.Driver;
import com.cg.mts.repository.IDriverRepository;
import com.cg.mts.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTesting {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private IDriverRepository driverRepository;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testInsertDriver() {
        Driver driver = new Driver("ABC", "abc", "Acd", "acd", "abc",  null, 4.5f);
        Mockito.when(driverRepository.save(driver)).thenReturn(driver);
        assertEquals(driverService.insertDriver(driver), driver);
    }

    @Test
    void testDeleteDriver() {
        Driver driver = new Driver("ABC", "abc", "Acd", "acd", "abc",  null, 4.5f);
        Mockito.when(driverRepository.findById(driver.getDriverId())).thenReturn(Optional.of(driver));
        assertEquals(driverService.deleteDriver(driver.getDriverId()), driver);
    }

    @Test
    void testBestDriver() {
        Driver driver = new Driver("ABC", "abc", "Acd", "acd", "abc",  null, 4.5f);
        List<Driver> drivers = new ArrayList<Driver>();
        drivers.add(driver);
        Mockito.when(driverRepository.viewBestDrivers()).thenReturn(drivers);
        assertEquals(driverService.viewBestDrivers(), drivers);
    }
}
