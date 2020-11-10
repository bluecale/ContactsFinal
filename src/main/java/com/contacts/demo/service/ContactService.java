package com.contacts.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.contacts.demo.Dao.ContactGroupRelationDao;
import com.contacts.demo.Dao.ContactsDao;
import com.contacts.demo.Dao.GroupsDao;
import com.contacts.demo.beans.ContactBean;
import com.contacts.demo.beans.ContactGroupRelationBean;

@Configurable
@Service
public class ContactService {
	
	@Autowired
	ContactsDao contactsDao;

	@Autowired
	ContactGroupRelationDao contactGroupRelationDao;

	@Autowired
	GroupsDao groupsDao;

	public static boolean isValid(ContactBean bean) {
		if (StringUtils.isEmpty(bean.getName())) {
			return false;
		}
		return true;
	}

	public void deleteContact(Long id) {
		String idToString = String.valueOf(id);
		System.out.println(contactsDao.findById((long) 1));
		Set<String> affectedGroups = new HashSet<String>();
		List<ContactGroupRelationBean> realtionsToDelete = contactGroupRelationDao.findByContactId(idToString);
		System.out.println(realtionsToDelete);
		for (ContactGroupRelationBean bean : realtionsToDelete) {
			affectedGroups.add(bean.getGroupId());
			contactGroupRelationDao.delete(bean);
		}
		for (String groupId : affectedGroups) {
			List<ContactGroupRelationBean> contactGroupRealtions = contactGroupRelationDao.findByGroupId(groupId);
			if (contactGroupRealtions.isEmpty()) {
				groupsDao.deleteById(Long.valueOf(groupId));
			}
		}
		contactsDao.deleteById(id);
	}
}
