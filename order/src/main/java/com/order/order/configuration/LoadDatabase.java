package com.order.order.configuration;

import com.order.order.dao.OrderRepository;
import com.order.order.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OrderRepository repository) {

        return args -> {

            log.info("Preloading " + repository.save(new Order("Order1", "Tours")));
            log.info("Preloading " + repository.save(new Order("Order2", "Orléans")));
            log.info("Preloading " + repository.save(new Order("Order3", "Paris")));
            log.info("Preloading " + repository.save(new Order("Order4", "Chinon")));
            log.info("Preloading " + repository.save(new Order("Order5", "Bordeaux")));
        };
    }
}
