package org.allitov.beans;

import org.allitov.data.Contact;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;

public class ContactsManagerInit implements ContactsManager {
    private final Set<Contact> contacts = new HashSet<>();

    @Value("${app.saving-file.path}")
    private String saveFilePath;

    @Value("${app.loading-file.path}")
    private String loadFilePath;

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

    @PostConstruct
    private void deserializeContacts() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(loadFilePath))) {
            while (bufferedReader.ready()) {
                String contactData = bufferedReader.readLine();
                String[] data = contactData.split(";");
                if (data.length != 3) {
                    continue;
                }
                Contact contact = Contact.builder()
                        .name(data[0])
                        .phoneNumber(data[1])
                        .email(data[2])
                        .build();
                contacts.add(contact);
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении файла!");
        }
    }
}
