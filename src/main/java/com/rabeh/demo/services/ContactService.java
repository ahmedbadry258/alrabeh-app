package com.rabeh.demo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rabeh.demo.model.Contact;
import com.rabeh.demo.repositories.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	public Contact save(Contact contact) {
		contact.setCreateDate(new Date());
		contact.setReadFlag(false);
		return contactRepository.save(contact);
	}

	public Page<Contact> findAll(int pageNumber){
		Pageable pageable= PageRequest.of(pageNumber-1, 10);
		return contactRepository.findAll(pageable);
	}

	public Contact findContact(Long id) {
		Contact con=contactRepository.findById(id).get();
		con.setReadFlag(true);
		return	contactRepository.save(con);
	
		
	}
	public void saveList(List<Contact> contacts) {
		contactRepository.saveAll(contacts);
	}
	
}
