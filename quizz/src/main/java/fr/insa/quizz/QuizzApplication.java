package fr.insa.quizz;

import fr.insa.quizz.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class QuizzApplication {

	@Autowired
	QuizzRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizzApplication.class, args);
	}

}
