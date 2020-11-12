package com.contacts.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	private ContactsDao contactsDao;
	
	@Autowired
	private ContactService service;

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
			contactsDao.save(contact);
			return "redirect:/contacts";
		} else {
			model.addAttribute("message", EMPTYFIELD);
			return "/add";
			}
	}
	
	@RequestMapping(value="/contacts")
	public String showContacts(ModelMap model) {
		List<ContactBean> contacts = contactsDao.findByFav(false);
		List<ContactBean> starredContacts = contactsDao.findByFav(true);
		model.addAttribute("contacts", contacts);
		model.addAttribute("starred", starredContacts);
		return "contacts";
	}

	@RequestMapping(value = "/delete")
	public String deleteContact(ModelMap model,  @RequestParam("to_edit") Long contactId) {
		service.deleteContact(contactId);
		return "redirect:/contacts";
		
	}
	
	@GetMapping(value="/starred")
	public String changeStarring(ModelMap model, @RequestParam("to_edit") Long contactId ) {
		Optional<ContactBean> contact = contactsDao.findById(contactId);
		boolean current_status = contact.get().isFav();
		contact.get().setFav(current_status ? false : true);
		contactsDao.save(contact.get());
		return "redirect:/contacts";
	}	
	
	@GetMapping(value="/search_contacts")
	public String searchContacts(ModelMap model, @RequestParam("search") String search) {
		List<ContactBean> contacts = contactsDao.findByNameContaining(search);
		model.addAttribute("contacts", contacts);
		return "contacts";
	}

}

