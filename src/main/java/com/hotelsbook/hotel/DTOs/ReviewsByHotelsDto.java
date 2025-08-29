package com.hotelsbook.hotel.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsByHotelsDto {
	
	private Long hotelId;
	private String hotelName;
	private Double averageCalification;
	

}
