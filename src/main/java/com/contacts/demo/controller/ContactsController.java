package com.contacts.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.beans.ContactBean;
import com.contacts.demo.service.ContactService;

@Controller
public class ContactsController {
	private final static String EMPTYFIELD =  "YOU ARE NOT ALLOWED TO HAVE NO NAME/SURENAME";

	@Autowired
	private ContactsDao dao;
	
	private ContactService contactsService = new ContactService();
	
	
	@RequestMapping(value = "/add")
	public String addForm(ModelMap model) { // HttpSession session
		ContactBean contact = new ContactBean();
		model.addAttribute("contact", contact);
		return "add";
	}

	
	
	@PostMapping(value = "/add")
	public String addContact(ModelMap model, @ModelAttribute("contact") ContactBean contact) { // HttpSession session
		System.out.println(contact.toString());
		if (ContactService.isValid(contact)) {
			dao.save(contact);
			return "redirect:/contacts";
		} else {
			model.addAttribute("message", EMPTYFIELD);
			return "/add";
			}
	}
	
	@RequestMapping(value="/contacts")
	public String showContacts(ModelMap model) {
		List<ContactBean> contacts = dao.findAll();
		model.addAttribute("contacts", contacts);
		return "contacts";
	}
	
	@RequestMapping(value = "/delete")
	public String deleteContact(ModelMap model,  @RequestParam("to_edit") Long contactId) {
		contactsService.deleteContact(contactId);
		return "redirect:/contacts";
		
	}
	

	
}

