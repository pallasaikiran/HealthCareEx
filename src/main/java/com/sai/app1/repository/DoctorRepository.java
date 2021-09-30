package com.sai.app1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sai.app1.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
