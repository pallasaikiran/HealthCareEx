package com.sai.app1.service;

import java.util.List;
import java.util.Map;

import com.sai.app1.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization specialization);
	
	public List<Specialization> getAllSpecializations();
	
	public Specialization getOneSpecializationById(Long id);
	
	public void removeSpecialization(Long id);
	
	public void updateSpecialization(Specialization spec);
	
	public boolean isSpecCodeExist(String specCode);
	
	public boolean isSpecNameExist(String specName);
	
	public boolean isSpecCodeExistForEdit(String specCode,Long id);
	
	Map<Long,String> getSpecIdAndName();
}
