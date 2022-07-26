package com.assignment.pharmapartners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * This an assignment from  PharmaPartners
 *
 * made by  @author Mignon Gakuba
 */
@SpringBootApplication
@EnableJpaRepositories
@Configuration
public class AssignmentPharmaPartnersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentPharmaPartnersApplication.class, args);
	}

}
