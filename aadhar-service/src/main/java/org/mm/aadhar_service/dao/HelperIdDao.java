package org.mm.aadhar_service.dao;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HelperIdDao 
{
	private Long userId;
	
	private Long aadharId;
	
	private Long panId;
	
}
