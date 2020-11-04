package com.contacts.demo.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.contacts.demo.beans.ContactBean;

public interface ContactsDao extends JpaRepository<ContactBean, Long> {
	
	
//
//	List<ContactBean> findByName(String name);
//
//	List<ContactBean> findByEmail(String email);
//
//	List<ContactBean> findByPhoneNum(String phoneNum);
//
//	List<ContactBean> findByCompany(String company);
}






