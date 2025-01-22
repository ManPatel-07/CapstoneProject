package org.mm.aadhar_service.service;

import java.util.List;

import org.mm.aadhar_service.entity.AadharEntity;
import org.mm.aadhar_service.repository.AadharRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AadharService 
{

	private final AadharRepository aadharRepository;
	
	public AadharEntity createNewAadhar(AadharEntity aadharEntity)
	{
		return aadharRepository.save(aadharEntity);
	}
	
	public List<AadharEntity> allAadharsDetails()
	{
		return aadharRepository.findAll();
	}
	
}
