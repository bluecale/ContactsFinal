package com.contacts.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.beans.ContactBean;

import com.contacts.demo.service.ContactService;

@SessionAttributes(value = { "toEdit", "editModalOpen", "addModalOpen" })
@Controller
public class ContactsController {
	private final static String EMPTYFIELD = "YOU ARE NOT ALLOWED TO HAVE NO NAME/SURENAME";

	@Autowired
	private ContactsDao contactsDao;

	@Autowired
	private ContactService service;

	@RequestMapping(value = "/add")
	public String addForm(ModelMap model) { 
		model.addAttribute("addModalOpen", true);
		return "redirect:/contacts";
	}

	@PostMapping(value = "/add_new")
	public String addContact(ModelMap model, SessionStatus status, @RequestParam Map<String, String> allRequestParam) {
		ContactBean bean = new ContactBean();
		if (allRequestParam.get("save") != null) {
			bean.setName(allRequestParam.get("name"));
			bean.setSurname(allRequestParam.get("surname"));
			bean.setEmail(allRequestParam.get("email"));
			bean.setPhoneNum(allRequestParam.get("phone"));
			bean.setCompany(allRequestParam.get("company"));
			bean.setNotes(allRequestParam.get("notes"));
			contactsDao.save(bean);
		}
		status.setComplete();
		return "redirect:/contacts";
	}

	@RequestMapping(value = "/contacts")
	public String showContacts(ModelMap model) {
		ArrayList<ContactBean> contacts = contactsDao.findByFav(false);
		ArrayList<ContactBean> starredContacts = contactsDao.findByFav(true);
		contacts.addAll(starredContacts);
		//contacts = service.sortContactsByName(contacts);
		//starredContacts = service.sortContactsByName(starredContacts);
		model.addAttribute("contacts", contacts);
		model.addAttribute("starred", starredContacts);
		return "contacts";
	}

	@RequestMapping(value = "/delete")
	public String deleteContact(ModelMap model, @RequestParam("to_edit") Long contactId) {
		service.deleteContact(contactId);
		return "redirect:/contacts";

	}

	@GetMapping(value = "/starred")
	public String changeStarring(ModelMap model, @RequestParam("to_edit") Long contactId) {
		Optional<ContactBean> contact = contactsDao.findById(contactId);
		boolean current_status = contact.get().isFav();
		contact.get().setFav(current_status ? false : true);
		contactsDao.save(contact.get());
		return "redirect:/contacts";
	}

	@GetMapping(value = "/search_contacts")
	public String searchContacts(ModelMap model, @RequestParam("search") String search) {
		List<ContactBean> contacts = contactsDao.findByNameContaining(search);
		model.addAttribute("contacts", contacts);
		return "contacts";
	}

	@GetMapping(value = "/start_group")
	public String createGroup(ModelMap model, @RequestParam Map<String, String> allRequestParam) {
		System.out.println(allRequestParam);
		return "contacts";
	}

	@GetMapping(value = "/edit_contact")
	public String openEditModal(ModelMap model, @RequestParam("to_edit") Long id) {
		Optional<ContactBean> bean = contactsDao.findById(id);
		model.addAttribute("toEdit", bean.get());
		model.addAttribute("editModalOpen", true);
		return "redirect:/contacts";
	}

	@GetMapping(value = "/apply_changes")
	public String saveChanges(ModelMap model, SessionStatus status, @RequestParam Map<String, String> allRequestParam) {
		ContactBean bean = (ContactBean) model.getAttribute("toEdit");
		if (allRequestParam.get("save") != null) {
			bean.setName(allRequestParam.get("name"));
			bean.setSurname(allRequestParam.get("surname"));
			bean.setEmail(allRequestParam.get("email"));
			bean.setPhoneNum(allRequestParam.get("phone"));
			bean.setCompany(allRequestParam.get("company"));
			bean.setNotes(allRequestParam.get("notes"));
			contactsDao.save(bean);
		}
		status.setComplete();
		return "redirect:/contacts";
	}
}
