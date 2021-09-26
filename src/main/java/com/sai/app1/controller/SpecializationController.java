package com.sai.app1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sai.app1.entity.Specialization;
import com.sai.app1.service.ISpecializationService;

@Controller

@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService service;
	
	
	/**
	 * 1.Show Register Page
	 */
	@GetMapping("/register")
	public String displayRegister()
	{
		return "SpecializationRegister";
		
	}
	
	/**
	 * On Submit Form save data
	 */
	@PostMapping("/save") 
	public String saveForm(@ModelAttribute Specialization specialization,Model model) {
		
		Long id=service.saveSpecialization(specialization);
		String message="Record ("+id+") is created";
		model.addAttribute("message",message);
		return "SpecializationRegister";
	}
	
	
	/**
	 * Display All Specializations
	 */
	@GetMapping("/all")
	public String viewAll(Model model,
			@RequestParam(value="message",required=false) String message	
			){
		List<Specialization> list=service.getAllSpecializations();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "SpecializationData";
	}
	
	/**
	 * Delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			)
	{
		attributes.addAttribute("message","Record ("+id+") is removed");
		service.removeSpecialization(id);
		return "redirect:all";
		
	}
	
	/**
	 * Fetech Data into Edit Page
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,Model model
			)
	{
		Specialization spec=service.getOneSpecializationById(id);
		model.addAttribute("specialization",spec);
		return "SpecializationEdit";
	}
	
	/**
	 * Update Form data and redirect to all
	 */
	@PostMapping("/update")
	public String updateData(
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes
			)
	{
		service.updateSpecialization(specialization);
		attributes.addAttribute("message","Record ("+specialization.getId()+") is updated");
		return "redirect:all";
	}
	
	/**
	 * Read specCode and check with Service
	 * Return message back to UI
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(
			@RequestParam String code
			)
	{
		String message="";
		
		if(service.isSpecCodeExist(code))
		{
			message=code+",Already exist";
		}
		return message;
		
	}
	
	/**
	 * Read Name and check with Service
	 * Return message back to UI
	 */
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(
				@RequestParam String name
			)
	{
		String message="";
		if(service.isSpecNameExist(name))
		{
			message=name+",Already exist";
		}
		return message;
	}
}