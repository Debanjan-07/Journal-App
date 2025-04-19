package com.example.journal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement            // for transaction management
public class JournalApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalApplication.class, args);
	}


//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){        // this method help to management transaction
//		return new JpaTransactionManager(emf);
//	}
}
