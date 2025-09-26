package com.hotelsbook.hotel.services.IServices;

import java.util.Date;
import java.util.List;

import com.hotelsbook.hotel.DTOs.HotelAvailableDto;
import com.hotelsbook.hotel.DTOs.HotelCityDto;

public interface IHotelService {

	public List<HotelAvailableDto> getAvailableHotelsWithServicesAndREviews( Date startDate, Date endDate, Integer cityId );
	
	public List<HotelCityDto> getHotelCities();
}
