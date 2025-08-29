package com.hotelsbook.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelsbook.hotel.DTOs.HotelSearchBycityDto;
import com.hotelsbook.hotel.domain.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
	
	@Procedure(name = "HotelsByCity")
	public List<HotelSearchBycityDto> findAvailableHotelsByCity(
			@Param("start_date") Date startDate ,
			@Param("end_date") Date endDate, 
			@Param("city_id") Integer cityId 
	);

}
