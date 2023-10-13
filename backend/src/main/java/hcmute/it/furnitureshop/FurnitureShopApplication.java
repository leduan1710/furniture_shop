package hcmute.it.furnitureshop;

import hcmute.it.furnitureshop.Entity.Notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FurnitureShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureShopApplication.class, args);
	}

}
