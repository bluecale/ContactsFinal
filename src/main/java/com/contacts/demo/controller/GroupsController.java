package com.contacts.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
import com.contacts.demo.service.GroupService;

@Controller
@SessionAttributes(value = { "groupModal", "groupList" })
public class GroupsController {

	private final static String EMPTYFIELD = "YOU ARE NOT ALLOWED TO HAVE NO GROUP NAME";

	@Autowired
	ContactsDao contactsDao;

	@Autowired
	GroupsDao groupsDao;

	@Autowired
	ContactGroupRelationDao contactGroupRealationDao;

	@Autowired
	GroupService service;

	@PostMapping(value = "/new_group")
	public String startGroup(ModelMap model, @RequestParam Map<String, String> allRequestParam) {
		model.addAttribute("groupModal", true);
		model.addAttribute("groupList", allRequestParam);
		return "redirect:/contacts";
	}

	@SuppressWarnings({ "unchecked" })
	@PostMapping(value = "/create_group")
	public String createGroup(SessionStatus status, ModelMap model, @RequestParam Map<String, String> allRequestParam, @RequestParam("groupName") String name) {
		System.out.println(model.getAttribute("groupList"));
		if (allRequestParam.get("save") != null) {
			GroupBean group = new GroupBean();
			group.setName(name);
			if (service.isValid(group)) {
				groupsDao.save(group);
				String groupId = String.valueOf(group.getId());
				Set<String> contactsToAdd = ((Map<String, String>) model.getAttribute("groupList")).keySet();
				for (String id : contactsToAdd) {
					System.out.println(id);
					ContactGroupRelationBean contactRelation = new ContactGroupRelationBean();
					contactRelation.setContactId(id);
					contactRelation.setGroupId(groupId);
					contactGroupRealationDao.save(contactRelation);
				}
			} else {
				return "redirect:/contacts";
			}
		}
		status.setComplete();
		return "redirect:/contacts";

	}

//
//	@SuppressWarnings("unchecked")
//	@PostMapping("/create_group")
//	public String createGroup(ModelMap model, SessionStatus status, @RequestParam("groupName") String groupName) {
//		GroupBean group = new GroupBean();
//		group.setName(groupName);
//		groupsDao.save(group);
//		System.out.println(group);
//		List<ContactBean> contactsToAdd = (List<ContactBean>) model.getAttribute("added");
//
//		for (ContactBean bean : contactsToAdd) {
//			ContactGroupRelationBean contactRelation = new ContactGroupRelationBean();
//			contactRelation.setContactId(String.valueOf(bean.getId()));
//			contactRelation.setGroupId(String.valueOf(group.getId()));
//			System.out.println(contactRelation);
//			contactGroupRealationDao.save(contactRelation);
//		}
//		status.setComplete();
//		return "addGroup";
//	}
//	@RequestMapping(value = "/new_group")
//	public String newGroup(ModelMap model) {
//		List<ContactBean> toAdd = contactsDao.findAll();
//		model.addAttribute("toAdd", toAdd);
//		model.addAttribute("added", new ArrayList<ContactBean>());
//		model.addAttribute("group", new GroupBean());
//		return "addGroup";
//
//	}
//
//	@SuppressWarnings("unchecked")
//	@GetMapping("/add_contact")
//	public String addContactToGroup(ModelMap model, @RequestParam("id") String id) {
//		Optional<ContactBean> contact = contactsDao.findById((Long.valueOf(id).longValue()));
//		List<ContactBean> added = (List<ContactBean>) model.getAttribute("added");
//		List<ContactBean> toAdd = (List<ContactBean>) model.getAttribute("toAdd");
//		added.add(contact.get());
//		toAdd.remove(contact.get());
//		model.addAttribute("added", added);
//		model.addAttribute("toAdd", toAdd);
//		return "addGroup";
//
//	}
//
//	@SuppressWarnings("unchecked")
//	@GetMapping("/remove_contact")
//	public String removeContactFromGroup(ModelMap model, @RequestParam("id") String id) {
//		Optional<ContactBean> contact = contactsDao.findById((Long.valueOf(id).longValue()));
//		List<ContactBean> added = (List<ContactBean>) model.getAttribute("added");
//		List<ContactBean> toAdd = (List<ContactBean>) model.getAttribute("toAdd");
//		added.remove(contact.get());
//		toAdd.add(contact.get());
//		model.addAttribute("added", added);
//		model.addAttribute("toAdd", toAdd);
//		return "addGroup";
//	}

	@RequestMapping("/groups_list")
	public String listGroups(ModelMap model) {
		HashMap<String, ArrayList<ContactBean>> groups = service.createGroupsMap();
		model.addAttribute("groups", groups);
		return "groupsList";
	}

	@GetMapping("/search_groups")
	public String searchGroups(ModelMap model, @RequestParam("search") String groupSearch) {
		HashMap<String, ArrayList<ContactBean>> groups = service.createGroupsMap(groupSearch);
		model.addAttribute("groups", groups);
		return "groupsList";

	}
}
