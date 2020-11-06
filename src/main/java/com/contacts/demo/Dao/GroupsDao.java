package com.contacts.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contacts.demo.beans.GroupBean;

public interface GroupsDao extends JpaRepository<GroupBean, Long>{

}


