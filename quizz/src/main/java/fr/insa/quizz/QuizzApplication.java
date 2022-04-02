package fr.insa.quizz;

import fr.insa.quizz.repositories.QuizzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories
public class QuizzApplication {

	@Autowired
	QuizzRepository quizzRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizzApplication.class, args);
	}

}
