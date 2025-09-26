package com.hotelsbook.hotel.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public ResponseEntity<HotelError> exceptionHandler(HotelExcetion e){
		return new ResponseEntity<>(e.getError(), e.getStatusCode());
	}

}
