package com.contacts.demo.service;

import com.contacts.demo.beans.ContactBean;

public class ContactService {

	public static boolean isValid(ContactBean bean) {
		if (bean.getName().isEmpty()) {
			return false;
		}
		return true;
	}
}
