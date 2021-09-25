package com.sai.app1.service;

import java.util.List;

import com.sai.app1.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization specialization);
	
	public List<Specialization> getAllSpecializations();
	
	public Specialization getOneSpecializationById(Long id);
	
	public void removeSpecialization(Long id);
}
