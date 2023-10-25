package org.allitov.beans;

import org.allitov.data.Contact;

import java.util.List;

public interface ContactsManager {
    List<Contact> getAllContacts();
    boolean deleteContactByEmail(String email);
    boolean saveContact(Contact contact);
}
