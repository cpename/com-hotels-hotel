package com.hotelsbook.hotel.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelsbook.hotel.DTOs.HotelAvailableDto;
import com.hotelsbook.hotel.DTOs.HotelCityDto;
import com.hotelsbook.hotel.exceptions.ErrorResponse;
import com.hotelsbook.hotel.services.HotelService;


import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200/" )
@RestController
@RequestMapping("/api/hotels")
@Slf4j
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/available")
	public ResponseEntity<?> getAvailableHotelsWithServices(
		@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date startDate,
		@RequestParam("endDate") @DateTimeFormat(iso = ISO.DATE) Date endDate,
		@RequestParam("cityId") Integer cityId
	){
		
		try {
			log.info("Metodo getAvailableHotelsWithServices");
			
			List<HotelAvailableDto> hotels =  hotelService.getAvailableHotelsWithServicesAndREviews(startDate, endDate, cityId);
			
			if( hotels.isEmpty() ) {
				return new ResponseEntity<>(new ErrorResponse(404, "No se encontraron registros"), HttpStatus.NOT_FOUND);
			}
			
			return ResponseEntity.ok(hotels);
			
		} catch (Exception e) {
			log.error("error en getAvailableHotelsWithServices");
			e.printStackTrace();
			ErrorResponse error = new ErrorResponse(500, "Error interno del servidor");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
		
	}
	
	@GetMapping("/cities")
	public ResponseEntity<List<HotelCityDto>> getHotelCities(){
		List<HotelCityDto> cities =  hotelService.getHotelCities();
		
		return  ResponseEntity.ok(cities);
	}

}
