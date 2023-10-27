package org.allitov.config;

import org.allitov.beans.ContactsManager;
import org.allitov.beans.ContactsManagerDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultAppConfig {

    @Bean
    public ContactsManager contactsManager() {
        return new ContactsManagerDefault();
    }
}
