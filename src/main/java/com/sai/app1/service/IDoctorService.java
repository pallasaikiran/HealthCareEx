package com.sai.app1.service;

import java.util.List;

import com.sai.app1.entity.Doctor;

public interface IDoctorService {

	public Long saveDoctor(Doctor doctor);
	
	public List<Doctor> getAllDoctors();
	
	public Doctor getOneDoctorById(Long id);
	
	public void removeDoctor(Long id);
	
	public void updateDoctor(Doctor doctor);
}
