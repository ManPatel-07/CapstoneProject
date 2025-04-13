package org.mm.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aadhar")
public class AadharEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String aadharName;
	
	private String aadharNo;
	
	private String aadharImage;
	
	private Long profileId;
	
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dob;

	public AadharEntity(String aadharName, String aadharNo, String aadharImage) {
		super();
		this.aadharName = aadharName;
		this.aadharNo = aadharNo;
		this.aadharImage = aadharImage;
	}
	
}
