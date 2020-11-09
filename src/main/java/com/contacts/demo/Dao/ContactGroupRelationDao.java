package com.contacts.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contacts.demo.beans.ContactGroupRelationBean;

public interface ContactGroupRelationDao extends JpaRepository<ContactGroupRelationBean, Long>{
	
	List<ContactGroupRelationBean> findByGroupId(String groupId);
	List<ContactGroupRelationBean> findByContactId(String contactId);
}
