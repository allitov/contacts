package org.allitov.beans;

import org.allitov.data.Contact;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PreDestroy;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ContactsManagerDefault implements ContactsManager {
    private final Set<Contact> contacts = new HashSet<>();

    @Value("${app.saving-file.path}")
    private String saveFilePath;

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

    @Override
    @PreDestroy
    public void serializeContacts() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFilePath))) {
            for (Contact contact : contacts) {
                String contactData = String.format("%s;%s;%s%n",
                        contact.getName(), contact.getPhoneNumber(), contact.getEmail());
                bufferedWriter.append(contactData);
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи данных в файл!");
        }
    }
}
