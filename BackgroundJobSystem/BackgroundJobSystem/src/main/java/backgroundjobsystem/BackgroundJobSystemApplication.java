package backgroundjobsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BackgroundJobSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackgroundJobSystemApplication.class, args);
	}
}
