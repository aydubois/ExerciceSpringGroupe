package com.alik.alik.config;

import com.alik.alik.dao.CustomerRepository;
import com.alik.alik.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBProvider {
    private static final Logger log = LoggerFactory.getLogger(DBProvider.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {

        return args -> {

            log.info("Preloading " + repository.save(new Customer("Ali", "37000", "Tours")));
            log.info("Preloading " + repository.save(new Customer("Audrey Brouard", "37000", "Tours")));
            log.info("Preloading " + repository.save(new Customer("Audrey Dubois", "37000", "Tours")));
            log.info("Preloading " + repository.save(new Customer("Malek Aubert", "37000", "Tours")));
            log.info("Preloading " + repository.save(new Customer("Océan", "37000", "Tours")));
        };
    }
}
