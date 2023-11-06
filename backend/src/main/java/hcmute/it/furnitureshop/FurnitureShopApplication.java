package hcmute.it.furnitureshop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebSocketMessageBroker
public class FurnitureShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureShopApplication.class, args);
	}

}
