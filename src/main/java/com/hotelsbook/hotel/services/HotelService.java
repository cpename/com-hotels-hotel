package com.hotelsbook.hotel.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotelsbook.hotel.DTOs.HotelAvailableDto;
import com.hotelsbook.hotel.DTOs.HotelSearchBycityDto;
import com.hotelsbook.hotel.DTOs.HotelServicesResponseDTO;
import com.hotelsbook.hotel.DTOs.ReviewsByHotelsDto;
import com.hotelsbook.hotel.DTOs.ServiceDto;
import com.hotelsbook.hotel.repository.HotelRepository;


@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private HotelServiceClient hotelServiceClient;
	
	@Autowired
	private HotelReviewClient hotelReviewClient;
	
	@Transactional
	public List<HotelAvailableDto> getAvailableHotelsWithServicesAndREviews( Date startDate, Date endDate, Integer cityId ){
		
		List<HotelSearchBycityDto> hotelSearchBycityDtos = hotelRepository.findAvailableHotelsByCity(startDate, endDate, cityId);
		
		if( hotelSearchBycityDtos.isEmpty() ) {
			List<HotelAvailableDto> hotelAvailables = new ArrayList<>();
			return new ArrayList<>(hotelAvailables);
		}else {
			List<Long> hotelIds =  hotelSearchBycityDtos.stream().map(HotelSearchBycityDto::getId).collect(Collectors.toList());
			
			List<HotelServicesResponseDTO> hotelServices =  hotelServiceClient.getHotelServices(hotelIds);
			
			List<ReviewsByHotelsDto> reviewsByHotel = hotelReviewClient.getHotelReviews(hotelIds);
			
//			combinar los resultados de reviews y servicios
			Map<Long, List<ServiceDto> > servicesByHotelId = new HashMap<>();
			hotelServices.stream().forEach( hotelServicesDto -> {
				servicesByHotelId.put(hotelServicesDto.getHotelDTO().getHotelId(), hotelServicesDto.getServicesDTO());
			});
			
			Map<Long, Double> reviewsByHotelId = new HashMap<>();
			reviewsByHotel.forEach(reviewsByHotelsDto -> {
				reviewsByHotelId.put(reviewsByHotelsDto.getHotelId(), reviewsByHotelsDto.getAverageCalification());
			});
			
//			Agrupar y mapear los resultados
			
			return hotelSearchBycityDtos.stream().map(hotelAvailable -> {
				HotelAvailableDto hotelAvailableDto = new HotelAvailableDto();
				hotelAvailableDto.setHotelSearchBycityDto(hotelAvailable);
				hotelAvailableDto.setServices(servicesByHotelId.getOrDefault(hotelAvailable.getId(), Collections.emptyList() ));
				hotelAvailableDto.setAverageCalification( reviewsByHotelId.getOrDefault(hotelAvailable.getId(), null));
				return hotelAvailableDto;
				
			}).collect(Collectors.toList());
			
			
		}
		
	}
	

}
