package com.contacts.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.contacts.demo.Dao.ContactGroupRelationDao;
import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.Dao.GroupsDao;
import com.contacts.demo.beans.ContactBean;
import com.contacts.demo.beans.ContactGroupRelationBean;

@Service
public class ContactService {
	
	@Autowired 
	private ContactsDao contactsDao;
	
	@Autowired 
	private ContactGroupRelationDao contactGroupRelationDao;
	
	@Autowired 
	private GroupsDao groupsDao;

	public static boolean isValid(ContactBean bean) {
		if (StringUtils.isEmpty(bean.getName())) {
			return false;
		}
		return true;
	}
	
	public void deleteContact(Long id) {
		String idToString = String.valueOf(id);
		Set<String> affectedGroups = new HashSet<String>();
		List<ContactGroupRelationBean> realtionsToDelete = contactGroupRelationDao.findByContactId(idToString);
		for (ContactGroupRelationBean bean: realtionsToDelete) {
			affectedGroups.add(bean.getGroupId());
			contactGroupRelationDao.delete(bean);
		}
		for (String groupId: affectedGroups) {
			List<ContactGroupRelationBean> contactGroupRealtions = contactGroupRelationDao.findByGroupId(groupId);
			if (contactGroupRealtions.isEmpty()) {
				groupsDao.deleteById(Long.valueOf(groupId));
			}
		}
		contactsDao.deleteById(id);
		
	}
}




