package com.hotelsbook.hotel.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;

@Getter
public class HotelExcetion extends ResponseStatusException{
	
	private final transient HotelError error;

	public HotelExcetion(HttpStatusCode status, String reason) {
		super(status, reason);
		this.error = new HotelError(reason);
	}

}
