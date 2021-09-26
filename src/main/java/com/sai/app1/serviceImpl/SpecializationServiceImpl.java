package com.sai.app1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.app1.entity.Specialization;
import com.sai.app1.exception.SpecializationNotFoundException;
import com.sai.app1.repository.SpecializationRepository;
import com.sai.app1.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository repo;
	
	@Override
	public Long saveSpecialization(Specialization specialization) {
		
		return repo.save(specialization).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		
		return repo.findAll();
	}

	@Override
	public Specialization getOneSpecializationById(Long id) {
		
		return repo.findById(id).orElseThrow(
				()-> new SpecializationNotFoundException(id+ " Not Found")
				);
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);

	}
	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
		
	}
	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*
		 * Integer count=repo.getSpecCodeCount(specCode); boolean exist=count>0
		 * ?true:false; return exist;
		 */
		return repo.getSpecCodeCount(specCode)>0;
	}
	
	@Override
	public boolean isSpecNameExist(String specName) {
		return repo.getSpecNameCount(specName)>0;
	}
	
	
}
