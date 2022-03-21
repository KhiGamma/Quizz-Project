package fr.insa.user;

import fr.insa.user.models.User;
import fr.insa.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class UserApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
