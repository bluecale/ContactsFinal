package com.contacts.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacts.demo.beans.ContactGroupRelationBean;

public interface ContactGroupRelationDao extends JpaRepository<ContactGroupRelationBean, Long>{

}
