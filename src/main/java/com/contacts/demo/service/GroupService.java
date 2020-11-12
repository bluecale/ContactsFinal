package com.contacts.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contacts.demo.Dao.ContactGroupRelationDao;
import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.Dao.GroupsDao;
import com.contacts.demo.beans.ContactBean;
import com.contacts.demo.beans.ContactGroupRelationBean;
import com.contacts.demo.beans.GroupBean;

@Service
public class GroupService {

	@Autowired
	private ContactsDao contactsDao;
	
	@Autowired
	private GroupsDao groupsDao;
	
	@Autowired
	private ContactGroupRelationDao contactGroupRealationDao;

	public ArrayList<ContactBean> createContactsList(List<ContactGroupRelationBean> relations, GroupBean group) {
		ArrayList<ContactBean> contacts = new ArrayList<ContactBean>();
		for (ContactGroupRelationBean rel : relations) {
			if (rel.getGroupId().equals(String.valueOf(group.getId()))) {
				Optional<ContactBean> contact = contactsDao.findById(Long.valueOf(rel.getContactId()));
				if (contact.get() != null) {
					contacts.add(contact.get());
				}
			}
		}
		return contacts;
	}

	public HashMap<String, ArrayList<ContactBean>> createGroupsMap() {
		List<ContactGroupRelationBean> relations = contactGroupRealationDao.findAll();
		List<GroupBean> groupsbean = groupsDao.findAll();
		HashMap<String, ArrayList<ContactBean>> groups = new HashMap<String, ArrayList<ContactBean>>();
		for (GroupBean group : groupsbean) {
			groups.put(group.getName(), createContactsList(relations, group));
		}
		return groups;
	}

	public HashMap<String, ArrayList<ContactBean>> createGroupsMap(String search) {
		List<ContactGroupRelationBean> relations = contactGroupRealationDao.findAll();
		List<GroupBean> groupsbean = groupsDao.findByNameContaining(search);
		HashMap<String, ArrayList<ContactBean>> groups = new HashMap<String, ArrayList<ContactBean>>();
		for (GroupBean group : groupsbean) {
			groups.put(group.getName(), createContactsList(relations, group));
		}
		return groups;
	}
}