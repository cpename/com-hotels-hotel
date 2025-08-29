package com.hotelsbook.hotel.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelSearchBycityDto {
	
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String picture;
	private String street;
	private Integer addresNumber;
	private String cityName;
}
