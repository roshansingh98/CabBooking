package com.cg.mts.service;

import com.cg.mts.entities.Driver;
import com.cg.mts.exception.DriverNotFoundException;
import com.cg.mts.exception.InvalidDriverException;
import com.cg.mts.repository.IDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/* This annotation marks the class as a service class */
@Service
/*
 * @Transactional marks the transaction that we have to do in the methods while
 * updating the data
 */
@Transactional
public class DriverService implements IDriverService {

	/*
	 * IDriverRepository is a interface which extends JPA repository. We are trying
	 * to call the reference of it. Using @Autowired annotation , the object of the
	 * repository will be created during run time dynamicaly
	 */
	@Autowired
	private IDriverRepository driverRepository;

	/* this method would add the driver object in the database using save method */
	@Override
	public Driver insertDriver(Driver driver) {
		driver = driverRepository.save(driver);
		return driver;
	}

	/*
	 * this method would update the driver object in the database with matching
	 * driver id. if however there is no matching id, it will throw driver not found
	 * exception
	 */
	@Override
	public Driver updateDriver(Driver driver) {
		boolean checkIfExists = driverRepository.existsById(driver.getDriverId());
		if (!checkIfExists) {
			throw new InvalidDriverException("Driver with driver id " + driver.getDriverId() + " does not exists");
		}
		driver = driverRepository.save(driver);
		return driver;
	}

	/*
	 * this method would delete the driver object from the database with matching
	 * driver id. if however there is no matching id, it will throw driver not found
	 * exception
	 */
	@Override
	public Driver deleteDriver(int driverId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		if (!driverOptional.isPresent()) {
			throw new DriverNotFoundException("Driver with driver id " + driverId + " does not exists");
		}
		Driver driver = driverOptional.get();
		driverRepository.delete(driver);
		return driver;
	}

	/*
	 * this method would retrieve the list of drivers from database with rating 4.5
	 * or more. if however there are no driver with good rating, it will return
	 * driver not found
	 */
	@Override
	public List<Driver> viewBestDrivers() {
		List<Driver> drivers = driverRepository.viewBestDrivers();
		if (drivers.size() == 0) {
			throw new DriverNotFoundException("No Driver with best rating at the moment");
		}
		return drivers;
	}

	/*
	 * this method would retrieve the driver object with driver id matching. if
	 * however there is no driver object with matching id, it will return a
	 * exception of driver not found
	 */
	@Override
	public Driver viewDriver(int driverId) {
		Optional<Driver> drivers = driverRepository.findById(driverId);
		if (!drivers.isPresent()) {
			throw new DriverNotFoundException("Driver with driver id " + driverId + " does not exists");
		}
		Driver driver = drivers.get();
		return driver;
	}

}
