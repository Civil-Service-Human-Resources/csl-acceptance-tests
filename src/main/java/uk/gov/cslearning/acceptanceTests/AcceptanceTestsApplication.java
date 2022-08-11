package uk.gov.cslearning.acceptanceTests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootApplication
@SpringBootTest
public class AcceptanceTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcceptanceTestsApplication.class, args);
	}

}
