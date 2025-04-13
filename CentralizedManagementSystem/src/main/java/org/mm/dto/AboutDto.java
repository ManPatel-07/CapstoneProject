package org.mm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class AboutDto 
{
	private String email;
	
	private String name;
	
	private String profileImage;
	
	private String contactNo;
	
	private String address;
	
	private String postalCode;
	
	private String bio;
}
