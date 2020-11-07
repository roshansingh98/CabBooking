package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.cg.mts.exception.DriverNotFoundException;
import com.cg.mts.exception.InvalidDriverException;
import com.cg.mts.repository.IDriverRepository;
import com.cg.mts.util.PersistanceUtil;
import com.cg.mts.entities.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverService implements IDriverService {

	// private EntityManager entityManager;

	@Autowired
	private IDriverRepository driverRepository;

	@Override
	public Driver insertDriver(Driver driver) {
		driver = driverRepository.save(driver);
		return driver;
	}

	@Override
	public Driver updateDriver(Driver driver) {
		boolean checkIfExists = driverRepository.existsById(driver.getDriverId());
		if(!checkIfExists) {
			throw new InvalidDriverException("Driver with driver id " + driver.getDriverId() + " does not exists");
		}
		driver = driverRepository.save(driver);
		return driver;
	}

	@Override
	public Driver deleteDriver(int driverId) {
		Optional<Driver> driverOptional = driverRepository.findById(driverId);
		if(!driverOptional.isPresent()) {
			throw new DriverNotFoundException("Driver with driver id " + driverId + " does not exists");
		}
		Driver driver = driverOptional.get();
		driverRepository.delete(driver);
		return driver;
	}

	@Override
	public List<Driver> viewBestDrivers() {
		List<Driver> drivers = driverRepository.viewBestDrivers();
		if(drivers.size() == 0) {
			throw new DriverNotFoundException("No Driver with best rating at the moment");
		}
		return drivers;
	}

	@Override
	public Driver viewDriver(int driverId) {
		Optional<Driver> drivers = driverRepository.findById(driverId);
		if(!drivers.isPresent()) {
			throw new DriverNotFoundException("Driver with driver id " + driverId + " does not exists");
		}
		Driver driver = drivers.get();
		return driver;
	}

}
