package org.mm.dto;
import java.util.Set;

import org.mm.entity.enums.Role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class ProfileDao 
{
	private Long id;
	
	private String name;
	
	private String profileImage;
	
	private String email;
	
	private String password;
	
	private Set<Role> roles;
	
	private String contactNo;
	
	private String address;
	
	private String postalCode;
	
	private String bio;
}
