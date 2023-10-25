package org.allitov.beans;

import org.allitov.data.Contact;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ContactsManagerImpl implements ContactsManager {
    private final Set<Contact> contacts = new HashSet<>();

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public boolean deleteContactByEmail(String email) {
        boolean isDeleted = false;
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getEmail().equals(email)) {
                iterator.remove();
                isDeleted = true;
            }
        }

        return isDeleted;
    }

    @Override
    public boolean saveContact(Contact contact) {
        return contacts.add(contact);
    }
}
