package com.contacts.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.contacts.demo.Dao.ContactGroupRelationDao;
import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.Dao.GroupsDao;
import com.contacts.demo.beans.ContactBean;
import com.contacts.demo.beans.ContactGroupRelationBean;
import com.contacts.demo.beans.GroupBean;

@Controller
@SessionAttributes(value = {"toAdd", "added"})
public class GroupsController {
	


	@Autowired
	ContactsDao contactsDao;

	@Autowired
	GroupsDao groupsDao;

	@Autowired
	ContactGroupRelationDao contactGroupRealationDao;

	
	@RequestMapping(value = "/new_group")
	public String newGroup(ModelMap model) {
		List<ContactBean> toAdd = contactsDao.findAll();
		model.addAttribute("toAdd", toAdd);
		model.addAttribute("added",  new ArrayList<ContactBean>());
		model.addAttribute("group", new GroupBean());
		return "addGroup";

	}
	@SuppressWarnings("unchecked")
	@GetMapping("/add_contact")
	public String addContactToGroup(ModelMap model, @RequestParam("id") String id) {
		Optional<ContactBean> contact = contactsDao.findById((Long.valueOf(id).longValue()));
		List<ContactBean> added = (List<ContactBean>) model.getAttribute("added");
		List<ContactBean> toAdd = (List<ContactBean>) model.getAttribute("toAdd");
		added.add(contact.get());
		toAdd.remove(contact.get());
		model.addAttribute("added",  added);
		model.addAttribute("toAdd",  toAdd);
		return "addGroup";
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/remove_contact")
	public String removeContactFromGroup(ModelMap model, @RequestParam("id") String id) {
		Optional<ContactBean> contact = contactsDao.findById((Long.valueOf(id).longValue()));
		List<ContactBean> added = (List<ContactBean>) model.getAttribute("added");
		List<ContactBean> toAdd = (List<ContactBean>) model.getAttribute("toAdd");
		added.remove(contact.get());
		toAdd.add(contact.get());
		model.addAttribute("added",  added);
		model.addAttribute("toAdd",  toAdd);
		return "addGroup";	
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/create_group")
	public String createGroup(ModelMap model, SessionStatus status, @RequestParam("groupName") String groupName) {
		GroupBean group = new GroupBean();
		group.setName(groupName);
		groupsDao.save(group);
		System.out.println(group);
		List<ContactBean> contactsToAdd = (List<ContactBean>) model.getAttribute("added");
		for (ContactBean bean: contactsToAdd) {
			ContactGroupRelationBean contactRelation = new ContactGroupRelationBean();
			contactRelation.setContactId(String.valueOf(bean.getId()));
			contactRelation.setGroupId(String.valueOf(group.getId()));
			System.out.println(contactRelation);
			contactGroupRealationDao.save(contactRelation);
		}
		
		status.setComplete();
		
		return "addGroup";
	}
	
	@RequestMapping("/groups_list")
	public String listGroups(ModelMap model) {
		List<ContactGroupRelationBean> relations = contactGroupRealationDao.findAll();
		List<GroupBean> groups = groupsDao.findAll();
		model.addAttribute("groups", groups);
		model.addAttribute("relations", relations);
		
		return "groupsList"; 
	}
}


