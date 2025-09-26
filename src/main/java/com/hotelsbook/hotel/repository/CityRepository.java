package com.hotelsbook.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelsbook.hotel.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	
	public List<City> findAll();

}
