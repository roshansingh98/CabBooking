package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.Cab;
import com.cg.mts.exception.CabNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICabRepository extends JpaRepository<Cab, Integer> {
	// public Cab insertCab(Cab cab);
	// public Cab updateCab(Cab cab) throws CabNotFoundException;
	// public Cab deleteCab(Cab cab) throws CabNotFoundException;
	// public List<Cab> viewCabsOfType(String carType) throws CabNotFoundException;
	// public int countCabsOfType(String carType) throws CabNotFoundException;
	@Query(value = "select count(cab_id) from Cab where carType=:cabType")
	public int countCabsOfType(@Param("cabType") String carType);

	public List<Cab> findByCarType(String carType);
}