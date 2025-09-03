package com.hotelsbook.hotel.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hotelsbook.hotel.DTOs.HotelServicesResponseDTO;
import com.hotelsbook.hotel.services.IServices.IHotelServiceClient;

@Service
public class HotelServiceClient implements IHotelServiceClient {
	
	private final RestTemplate restTemplate;
	
	@Value("${microservice.services.url}")
	private String servicesUrl;
	
	@Autowired
	public HotelServiceClient( RestTemplateBuilder templateBuilder ) {
		this.restTemplate = templateBuilder.build();
	}
	

	@Override
	public List<HotelServicesResponseDTO> getHotelServices(List<Long> hotelIds) {
		
		String hotelIdsParam = hotelIds.stream().map(String::valueOf).collect(Collectors.joining(","));
		String url = servicesUrl + "/" + hotelIdsParam;
		
		ResponseEntity<List<HotelServicesResponseDTO>> response = restTemplate.exchange(
				url,
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<HotelServicesResponseDTO>>(){}
		);
				
		
		return response.getBody();
	}

}
