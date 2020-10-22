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
        Driver currentDriver = entityManager.find(Driver.class, driver.getDriverId());
        if (currentDriver == null) {
            throw new DriverNotFoundException("Driver Could Not be Found");
        }
        driver = entityManager.merge(driver);
        return driver;
    }


    @Override
    public Driver deleteDriver(int driverId) throws DriverNotFoundException {
        Driver currentDriver = entityManager.find(Driver.class, driverId);
        if (currentDriver == null) {
            throw new DriverNotFoundException("Driver not found");
        }
        entityManager.remove(currentDriver);
        return currentDriver;
    }

    @Override
    public Driver viewDriver(int driverId) throws DriverNotFoundException {
        Driver currentDriver = entityManager.find(Driver.class, driverId);
        if (currentDriver == null) {
            throw new DriverNotFoundException("Driver Could Not Be Found");
        }
        return currentDriver;
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
