package com.contacts.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.contacts.demo.beans.ContactGroupRelationBean;

public interface ContactGroupRelationDao extends JpaRepository<ContactGroupRelationBean, Long>{
	List<ContactGroupRelationBean> findByContactId(String id);
	List<ContactGroupRelationBean> findByGroupId(String id);
}
