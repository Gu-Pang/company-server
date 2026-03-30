package org.gupang.company_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"org.gupang.common"
})
public class CompanyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServerApplication.class, args);
	}

}
