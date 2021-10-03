package com.sai.app1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sai.app1.entity.Doctor;
import com.sai.app1.exception.DoctorNotFoundException;
import com.sai.app1.service.IDoctorService;
import com.sai.app1.service.ISpecializationService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specializationService;
	
	//1.show register page
	
	@GetMapping("/register")
	public String showReg(
			@RequestParam(value="message",required=false)String message,
			Model model
			)
	{
		model.addAttribute("message",message);
		createDynamicUi(model);
		return "DoctorRegister";
	}
	
	//2.save on submit
	@PostMapping("/save")
	public String save(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			)
	{
		Long id=service.saveDoctor(doctor);
		attributes.addAttribute("message","Doctor ("+id+") is created");
		return "redirect:register";
	}
	//3.display data
	@GetMapping("/all")
	public String display(
			@RequestParam(value="message",required=false)String message, 
			Model model)
	{
		List<Doctor> list=service.getAllDoctors();
		model.addAttribute("message",message);
		model.addAttribute("list",list);
		return "DoctorData";
	}
	
	//4.delete by id
	
	@GetMapping("/delete")
	public String delete(
			@RequestParam("id")Long id,
			RedirectAttributes attributes
			)
	{
		String message=null;
		try {
			service.getOneDoctorById(id);
			message="Doctor Removed";
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			message=e.getMessage();
		}
		attributes.addAttribute("message",message);
		return "redirect:all";
		
	}
	
	//5.show edit
	
	@GetMapping("/edit")
	public String edit(
			@RequestParam("id")Long id,
			Model model,
			RedirectAttributes attributes
			)
	{
		String page=null;
		try {
			Doctor doctor=service.getOneDoctorById(id);
			createDynamicUi(model);
			model.addAttribute("doctor",doctor);
			page="DoctorEdit";
			
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		
		return page;	
	}
	
	//6.update doctor
	
	@PostMapping("/update")
	public String doUpdate(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			)
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message",doctor.getId()+", updated");
		return "redirect:all";
	}
	
	//Dynamic UI Specializations
	private void createDynamicUi(Model model)
	{
		model.addAttribute("specializations",specializationService.getSpecIdAndName());
		
	}
	
}
