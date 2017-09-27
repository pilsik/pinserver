package by.sivko.pinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinServerApplication.class, args);
	}
}
