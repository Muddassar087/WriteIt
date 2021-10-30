package Net.writeit.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Net.writeit.main.controller")
@ComponentScan("Net.writeit.main.model")
@SpringBootApplication
public class WriteItApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriteItApplication.class, args);
	}

}
