package com.assignment.pharmapartners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *
 * This an assignment from  PharmaPartners
 *
 * made by  @author Mignon Gakuba
 */
@SpringBootApplication
@EnableJpaRepositories
@Configuration
@EnableWebMvc
public class AssignmentPharmaPartnersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentPharmaPartnersApplication.class, args);
	}

}
