package com.yourcompany.addressbook.logic;

import com.yourcompany.addressbook.exception.ResourceNotFoundException;
import com.yourcompany.addressbook.domain.model.Contact;
import com.yourcompany.addressbook.domain.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactLogic {
    private final ContactRepository contactRepository;

    public ContactLogic(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // existing methods

    public Contact updateContact(Long id, Contact updatedContact) {
        return contactRepository.findById(id)
            .map(contact -> {
                contact.setName(updatedContact.getName());
                contact.setEmail(updatedContact.getEmail());
                contact.setDateOfBirth(updatedContact.getDateOfBirth());
                contact.setJobTitle(updatedContact.getJobTitle());
                return contactRepository.save(contact);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact not found with id " + id);
        }
        contactRepository.deleteById(id);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id " + id));
    }
}