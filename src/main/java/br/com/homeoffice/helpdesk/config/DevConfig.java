package br.com.homeoffice.helpdesk.config;

import br.com.homeoffice.helpdesk.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {
    @Autowired
    private DBService dbService;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private  String value;

    @Bean
    public boolean instantializeDb() {
        if(value.equals("create")) {
            this.dbService.instantializeDb();
        }
        return false;
    }
}
