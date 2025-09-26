package com.hotelsbook.hotel.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City {
	
	@Id
	private Long id;
	
	private String name;
	
	private String country;

}
