package com.hotelsbook.hotel.services.IServices;

import java.util.List;
import com.hotelsbook.hotel.DTOs.HotelServicesResponseDTO;

public interface IHotelServiceClient {
	
	public List<HotelServicesResponseDTO> getHotelServices(List<Long> hotelIds);

}
