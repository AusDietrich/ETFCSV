package com.personal.etfComparator.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CSV {
	
	private String date;
	private String fund;
	private String company;
	private String ticker;
	private String cusip;
	private String shares;
	private String marketValue;
	private String weight;
}
