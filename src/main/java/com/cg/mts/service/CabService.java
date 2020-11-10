package com.cg.mts.service;

import com.cg.mts.entities.Cab;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.InvalidCabException;
import com.cg.mts.repository.ICabRepository;
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
// this is using jpa repository
public class CabService implements ICabService {

	/*
	 * ICabRepository is a interface which extends JPA repository. We are trying to
	 * call the reference of it. Using @Autowired annotation , the object of the
	 * repository will be created during run time dynamicaly
	 */
	@Autowired
	private ICabRepository cabRepository;

	/*
	 * this method will call cabRepository and will add the cab object using save
	 * method of the JPA repository
	 */
	@Override
	public Cab insertCab(Cab cab) {
		cab = cabRepository.save(cab);
		return cab;
	}

	/*
	 * this method will call cabRepository and will update the cab object of the
	 * matching id. if however we don't have any object of matching id, we will
	 * throw a CabNotFound exception
	 */
	@Override
	public Cab updateCab(Cab cab) {
		boolean checkIfExists = cabRepository.existsById(cab.getCabId());
		if (!checkIfExists) {
			throw new InvalidCabException("Cab does not exists for id=" + cab.getCabId());
		}
		cab = cabRepository.save(cab);
		return cab;

	}

	/*
	 * this method will delete the cab object with matching cab object. if however
	 * we don't have any cab with cab id, we will throw nocabfound exception
	 */
	@Override
	public Cab deleteCab(Cab cab) {
		Optional<Cab> optional = cabRepository.findById(cab.getCabId());
		if (!optional.isPresent()) {
			throw new CabNotFoundException("Cab not found for id=" + cab.getCabId());
		}
		cabRepository.deleteById(cab.getCabId());

		return cab;
	}

	/*
	 * this method will return list of cabs object matching carType. if no matching
	 * cab is found, we will throw CabNotFound exception
	 */
	@Override
	public List<Cab> viewCabsOfType(String carType) {
		List<Cab> cabsOfCarType = cabRepository.findByCarType(carType);
		if (cabsOfCarType.size() == 0) {
			throw new CabNotFoundException("Cab not found for carType=" + carType);
		}
		return cabsOfCarType;
	}

	/* this method will return a count of cabs with matching cartype */
	@Override
	public int countCabsOfType(String carType) {
		int count = cabRepository.countCabsOfType(carType);
		return count;
	}
}
