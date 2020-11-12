package com.contacts.demo.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contacts.demo.beans.ContactBean;

public interface ContactsDao extends JpaRepository<ContactBean, Long> {
	List<ContactBean> findByFav(boolean fav);
	List<ContactBean> findByNameContaining(String text);
}








