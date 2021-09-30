package com.sai.app1.serviceImpl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sai.app1.entity.Doctor;
import com.sai.app1.exception.DoctorNotFoundException;
import com.sai.app1.repository.DoctorRepository;
import com.sai.app1.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Long saveDoctor(Doctor doctor) {
		return repo.save(doctor).getId();
		
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		return repo.findAll();
	}

	@Override
	public Doctor getOneDoctorById(Long id) {
		
		return repo.findById(id).orElseThrow(()->new DoctorNotFoundException(id+"Not Exist"));
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctorById(id));

	}

	@Override
	public void updateDoctor(Doctor doctor) {
		if(repo.existsById(doctor.getId()))
			repo.save(doctor);
		else 
			throw new DoctorNotFoundException(doctor.getId()+"Not Exist");
	}

}
