package com.contacts.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.contacts.demo.Dao.ContactGroupRelationDao;
import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.Dao.GroupsDao;
import com.contacts.demo.beans.ContactBean;

@Service
public class ScheduleReport {
	
	@Autowired
	private ContactsDao contactsDao;

	@Autowired
	private GroupsDao groupsDao;
	
//	@Scheduled(fixedRate= 10000)
//	public void report() {
//		int contactNumber = contactsDao.findAll().size();
//		int groupNumber = groupsDao.findAll().size();	
//		System.out.println(String.format("Contacts %d -- Groups:%d", contactNumber, groupNumber));;
//	}

}
