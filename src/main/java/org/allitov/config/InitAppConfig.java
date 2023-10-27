package org.allitov.config;

import org.allitov.beans.ContactsManager;
import org.allitov.beans.ContactsManagerInit;
import org.springframework.context.annotation.*;

@Configuration
@Profile("init")
public class InitAppConfig {

    @Bean
    public ContactsManager contactsManager() {
        return new ContactsManagerInit();
    }
}
