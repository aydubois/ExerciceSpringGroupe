package org.abr.audreybr.configuration;

import org.abr.audreybr.dao.UserRepository;
import org.abr.audreybr.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {

            log.info("Preloading " + repository.save(new User("Ali")));
            log.info("Preloading " + repository.save(new User("Audrey B")));
            log.info("Preloading " + repository.save(new User("Audrey D")));
            log.info("Preloading " + repository.save(new User("Malek")));
            log.info("Preloading " + repository.save(new User("Océane")));
        };
    }
}
