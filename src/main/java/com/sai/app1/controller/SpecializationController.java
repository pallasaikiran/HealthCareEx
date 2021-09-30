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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sai.app1.entity.Specialization;
import com.sai.app1.exception.SpecializationNotFoundException;
import com.sai.app1.service.ISpecializationService;
import com.sai.app1.view.SpecializationExcelView;

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
		try {
			attributes.addAttribute("message","Record ("+id+") is removed");
			service.removeSpecialization(id);
		}catch(SpecializationNotFoundException e)
		{
			attributes.addAttribute("message",e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:all";
		
	}
	
	/**
	 * Fetech Data into Edit Page
	 * End user clicks on link,may enter ID manually
	 * If entered ID is Present in DB
	 * 	>Load row as object
	 * 	>Send data to edit page
	 *  >Fill in form 
	 * 
	 *  Else
	 *    >Redirect to all(Data page)
	 *    >	Show Error message(Not Found)
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,Model model,RedirectAttributes attributes
			)
	{
		String page=null;
		try {
			Specialization spec=service.getOneSpecializationById(id);
			model.addAttribute("specialization",spec);
			page="SpecializationEdit";
		}catch(SpecializationNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
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
	
	/**
	 * Export data excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel()
	{
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		//read data from DB
		List<Specialization> list=service.getAllSpecializations();
		//send to Excel Impl class
		m.addObject("list",list);
		return m;
		
	}
}