package org.mm.entity;

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
@Table(name = "education")
public class EducationEntity 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String collegeName;
	
	private String degree;
	
	private String course;
	
	private String gpa;
	
	private String collegeStartYear;
	
	private String collegeEndYear;
	
	private String schoolName;
	
	private String program;
	
	private String percentage;
	
	private String schoolStart;
	
	private String schoolEnd;
	
	private Long profileId;
}
