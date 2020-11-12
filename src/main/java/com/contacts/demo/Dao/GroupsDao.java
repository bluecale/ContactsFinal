package com.contacts.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacts.demo.beans.GroupBean;

public interface GroupsDao extends JpaRepository<GroupBean, Long>{
	List<GroupBean> findByNameContaining(String name);

}


