package com.cg.mts.dao;

import com.cg.mts.entities.Driver;
import com.cg.mts.exception.DriverNotFoundException;
import com.cg.mts.repository.IDriverRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class DriverDao implements IDriverRepository {

    private EntityManager entityManager;

    public DriverDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Driver insertDriver(Driver driver) {
        entityManager.persist(driver);
        return driver;
    }

    @Override
    public Driver updateDriver(Driver driver) throws DriverNotFoundException {
        boolean status = checkIfExists(driver.getDriverId());
        if (!status) {
            throw new DriverNotFoundException("Driver Could Not be Found");
        }
        driver = entityManager.merge(driver);
        return driver;
    }

    private boolean checkIfExists(int driverId) {
        Driver driver = entityManager.find(Driver.class, driverId);
        boolean result = driver != null;
        return result;
    }

    @Override
    public Driver deleteDriver(int driverId) throws DriverNotFoundException {
        boolean status = checkIfExists(driverId);
        if (!status) {
            throw new DriverNotFoundException("Driver not found");
        }
        Driver driver = entityManager.find(Driver.class, driverId);
        entityManager.remove(driver);
        return driver;
    }

    @Override
    public Driver viewDriver(int driverId) throws DriverNotFoundException {
        boolean status = checkIfExists(driverId);
        if (!status) {
            throw new DriverNotFoundException("Driver Could Not Be Found");
        }
        Driver driver = entityManager.find(Driver.class, driverId);
        return driver;
    }

    @Override
    public List<Driver> viewBestDrivers() throws DriverNotFoundException {
        List<Driver> bestDrivers = entityManager.createQuery("Select a from Driver a where a.rating >=4.5f", Driver.class).getResultList();
        if (bestDrivers.size() == 0) {
            throw new DriverNotFoundException("No Drivers with best rating");
        }
        return bestDrivers;
    }

}
