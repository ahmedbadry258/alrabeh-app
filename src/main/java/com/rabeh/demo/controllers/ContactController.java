package com.rabeh.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rabeh.demo.model.Contact;
import com.rabeh.demo.services.*;



@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@GetMapping({"/","/home"})
	public String getHomePage(Model model) {
		System.out.println("Home Page");
		model.addAttribute("contact", new Contact());
	//	loadDate();
		return "index";
	}
	
	@PostMapping("/send")
	public String sendMessage(@ModelAttribute Contact contact, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors())  {
	    	System.out.println("Error"+bindingResult.toString());
	    }
		System.out.println(contact.toString());
		contactService.save(contact);
		return "redirect:/home";
	}
	
	
	@GetMapping("/contacts")
	public String getContactPage(Model model) {
	
		return listByPage(model, 1);
	}
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model,@PathVariable ("pageNumber") int currentPage ) {
		org.springframework.data.domain.Page<Contact> page=contactService.findAll(currentPage);
	
		 long totalItems=page.getTotalElements();
		 int totalPages=page.getTotalPages();
		 
		List<Contact> contacts=page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("contacts", contacts);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		return "contacts";
	}
	
public void loadDate() {
	Contact contact= new Contact("ahmed14", "ahmed@gmail.com", "message", false);
	Contact contact2= new Contact("ahmed25", "ahmed2@gmail.com", "message", false);
	Contact contact3= new Contact("ahmed35", "ahmed3@gmail.com", "message", false);
	Contact contact4= new Contact("ahmed45", "ahmed4@gmail.com", "message", false);
	Contact contact5= new Contact("ahmed55", "ahmed5@gmail.com", "message", false);
	Contact contact6= new Contact("ahmed66", "ahmed6@gmail.com", "message", false);
	Contact contact7= new Contact("ahmed75", "ahmed7@gmail.com", "message", false);
	Contact contact8= new Contact("ahmed81", "ahmed8@gmail.com", "message", false);
	Contact contact9= new Contact("ahmed93", "ahmed9@gmail.com", "message", false);
	Contact contact10= new Contact("ahmed19", "ahmed10@gmail.com", "message", false);
	Contact contact11= new Contact("ahmed17", "ahmed11@gmail.com", "message", false);
	Contact contact12= new Contact("ahmed23", "ahmed12@gmail.com", "message", false);
	Contact contact13= new Contact("ahmed13", "ahmed13@gmail.com", "message", false);
	
	contactService.save(contact);
	contactService.save(contact2);
	contactService.save(contact3);
	contactService.save(contact4);
	contactService.save(contact5);
	contactService.save(contact6);
	contactService.save(contact7);
	contactService.save(contact8);
	contactService.save(contact9);
	contactService.save(contact10);
	contactService.save(contact11);
	contactService.save(contact12);
	contactService.save(contact13);
}
	
	@GetMapping("/contacts/{id}")
	public String getContactPage(@PathVariable Long id,Model model) {
		Contact contact=contactService.findContact(id);
		System.out.println("Contacts Page");
		model.addAttribute("contact", contact);
		return "msg";
	}
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
}
