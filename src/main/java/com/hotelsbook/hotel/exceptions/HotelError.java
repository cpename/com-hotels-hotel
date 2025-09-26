package com.hotelsbook.hotel.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelError {
	
	private String errorMessage;
	private String errorCode;
	
	
	
	public HotelError(String errorMessage) {		
		this.errorMessage = errorMessage;
	}



	public HotelError(String errorMessage, String errorCode) {		
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	

}
