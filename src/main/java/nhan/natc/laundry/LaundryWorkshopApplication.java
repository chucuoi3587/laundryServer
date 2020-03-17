package nhan.natc.laundry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LaundryWorkshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaundryWorkshopApplication.class, args);
	}

}
