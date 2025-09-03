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

import com.hotelsbook.hotel.DTOs.ReviewsByHotelsDto;

@Service
public class HotelReviewClient {
	
	private final RestTemplate restTemplate;
	
	@Value("${microservice.reviews.url}")
	private String reviewsUrl;
	
	@Autowired
	public HotelReviewClient( RestTemplateBuilder templateBuilder ) {
		this.restTemplate = templateBuilder.build();
		
	}
	
	public List<ReviewsByHotelsDto> getHotelReviews(List<Long> hotelIds){
		String hotelIdsParam = hotelIds.stream().map(String::valueOf).collect(Collectors.joining(","));
		String url = reviewsUrl + "/" + hotelIdsParam;
		
		ResponseEntity<List<ReviewsByHotelsDto>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET,
				null, 
				new ParameterizedTypeReference<List<ReviewsByHotelsDto>>() {
		});
		
		return response.getBody();
	}

}
