package org.mm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class AdminDao
{
	private Long id;
	
	private String name;
	
	private String adminImage;
	
	private String adminNo;
}
