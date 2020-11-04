package com.contacts.demo.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Entity
// TODO: @Slf4j
public class ContactBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String surname;
	private String email;
	private String phoneNum;
	private String company;
	private String picPath;
	private String notes;

	private boolean fav;
	
	

}