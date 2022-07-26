package com.assignment.pharmapartners;

import com.assignment.pharmapartners.repository.CurrencyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * This an assignment from  PharmaPartners
 *
 * made by  @author Mignon Gakuba
 */
@SpringBootApplication
@Configuration
@EnableWebMvc
public class AssignmentPharmaPartnersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentPharmaPartnersApplication.class, args);
	}

}
