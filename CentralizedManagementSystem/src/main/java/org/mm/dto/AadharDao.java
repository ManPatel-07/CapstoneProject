package org.mm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class AadharDao 
{
	private Long id;
	
	private String aadharName;
	
	private String aadharNo;
	
	private String aadharImage;
	
	private Long profileId;

}
