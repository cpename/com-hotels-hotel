package com.hotelsbook.hotel.domain;

import java.util.Date;

import com.hotelsbook.hotel.DTOs.HotelSearchBycityDto;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.StoredProcedureParameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedStoredProcedureQuery(
	name = "HotelsByCity",
	procedureName = "GetAvailableHotelsByCity",
	parameters = {
		@StoredProcedureParameter( mode = ParameterMode.IN, name = "start_date",type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "end_date",type = Date.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "city_id",type = Integer.class)
	},
	resultSetMappings = "HotelByCityDtoMapping"	
)
@SqlResultSetMapping(
	name = "HotelByCityDtoMapping",
	classes = @ConstructorResult(
				targetClass = HotelSearchBycityDto.class,
				columns = {
		            @ColumnResult(name = "id", type = Long.class),
		            @ColumnResult(name = "name", type = String.class),
		            @ColumnResult(name = "price", type = Double.class),
		            @ColumnResult(name = "description", type = String.class),
		            @ColumnResult(name = "picture", type = String.class),
		            @ColumnResult(name = "street", type = String.class),
		            @ColumnResult(name = "number", type = Integer.class),
		            @ColumnResult(name = "city_name", type = String.class)
				}
			)	
)
public class HotelEntity {
	
	@Id	
	private Long hotelId;
	private String hotelName;
	private Double hotelPrice;
	private String hotelDescription;
	private String hotelPicture;
	private String hotelStreet;
	private Integer hotelAddresNumber;
	private String hotelCityName;
	

}
