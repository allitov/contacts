package org.allitov;

import org.allitov.beans.Application;
import org.allitov.beans.ContactsManager;
import org.allitov.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(Application.class).run();
        context.getBean(ContactsManager.class).serializeContacts();
    }
}
