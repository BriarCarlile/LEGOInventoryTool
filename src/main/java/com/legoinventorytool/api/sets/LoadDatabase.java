package com.legoinventorytool.api.sets;

import com.legoinventorytool.api.LegoInventoryToolApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Slf4j
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(LEGOSetRepository legoSetRepository) {
        return args -> {
            log.info("Loading LegoInventoryToolApplication");
            log.info("Preloading " + legoSetRepository.save(new LEGOSet(673419389457L, LocalDate.now())));
        };
    }
}
