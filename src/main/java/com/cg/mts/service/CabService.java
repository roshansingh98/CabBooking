package com.cg.mts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.InvalidCabException;
import com.cg.mts.repository.ICabRepository;
import com.cg.mts.util.PersistanceUtil;
import com.cg.mts.entities.Cab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
// this is using jpa repository
public class CabService implements ICabService {

	@Autowired
	private ICabRepository cabRepository;

	@Override
	public Cab insertCab(Cab cab) {
		cab = cabRepository.save(cab);
		return cab;
	}

	@Override
	public Cab updateCab(Cab cab) {
		boolean checkIfExists= cabRepository.existsById(cab.getCabId());
		if(!checkIfExists){
			throw new InvalidCabException("Cab does not exists for id="+cab.getCabId());
		}
		cab = cabRepository.save(cab);
		return cab;

	}

	@Override
	public Cab deleteCab(Cab cab) {
		Optional<Cab> optional=cabRepository.findById(cab.getCabId());
		if(!optional.isPresent()){
			throw new CabNotFoundException("Cab not found for id="+cab.getCabId());
		}
		cabRepository.deleteById(cab.getCabId());

		return cab;
	}

	@Override
	public List<Cab> viewCabsOfType(String carType) {
		List<Cab> cabsOfCarType = cabRepository.findByCarType(carType);
		if(cabsOfCarType.size()==0)
		{
			throw new CabNotFoundException("Cab not found for carType="+carType);
		}
		return cabsOfCarType;
	}

	@Override
	public int countCabsOfType(String carType) {
		int count = cabRepository.countCabsOfType(carType);
		return count;
	}

}
