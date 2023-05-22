package com.yourcompany.addressbook.service.rest;

import com.yourcompany.addressbook.domain.model.Contact;
import com.yourcompany.addressbook.logic.ContactLogic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactService {
    private final ContactLogic contactLogic;

    public ContactService(ContactLogic contactLogic) {
        this.contactLogic = contactLogic;
    }

    // existing endpoints

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Contact contact = contactLogic.updateContact(id, updatedContact);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactLogic.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactLogic.getContactById(id);
        return ResponseEntity.ok(contact);
    }
}