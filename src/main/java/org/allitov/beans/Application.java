package org.allitov.beans;

import lombok.RequiredArgsConstructor;
import org.allitov.data.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class Application {
    private final ContactsManager contactsManager;
    private Scanner scanner;

    public void run() {
        scanner = new Scanner(System.in);
        boolean isRunning = true;

        do {
            System.out.println("Меню");
            System.out.println("1 - Вывести все контакты");
            System.out.println("2 - Добавить контакт");
            System.out.println("3 - Удалить контакт по email");
            System.out.println("0 - Выйти из программы");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> printContacts();
                case "2" -> addContact();
                case "3" -> deleteContact();
                case "0" -> isRunning = false;
                default -> System.out.println("Ошибка ввода");
            }
        } while (isRunning);
        scanner.close();
    }

    private void printContacts() {
        List<Contact> contacts = contactsManager.getAllContacts();
        System.out.printf("Контакты (всего: %d)%n", contacts.size());
        contacts.forEach(System.out::println);
        System.out.println();
    }

    private void addContact() {
        System.out.println("Введите данные контакта в формате Ф. И. О.; номер телефона; адрес электронной почты");
        String input = scanner.nextLine();
        String[] contactData = input.split("; ");
        if (contactData.length != 3) {
            System.out.println("Неверный формат ввода");
            return;
        }
        Contact contact = Contact.builder()
                .name(contactData[0])
                .phoneNumber(contactData[1])
                .email(contactData[2])
                .build();
        boolean result = contactsManager.saveContact(contact);
        System.out.println(result ? "Контакт сохранен" : "Такой контакт уже есть");
        System.out.println();
    }

    private void deleteContact() {
        System.out.println("Введите email контакта, которого хотите удалить:");
        String email = scanner.nextLine();
        boolean result = contactsManager.deleteContactByEmail(email);
        System.out.println(result ? "Контакт удален" : "Такого контакта нет");
        System.out.println();
    }
}
