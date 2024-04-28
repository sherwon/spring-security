package com.sherwin.dev.springsecurity;

import com.sherwin.dev.springsecurity.entity.Role;
import com.sherwin.dev.springsecurity.entity.User;
import com.sherwin.dev.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

//CommandLineRunner if you want to add default user
@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(SpringSecurityApplication.class, args);

//		generate secrete k y
		SecureRandom random = new SecureRandom();
		byte[] keyBytes = new byte[32]; // 256 bits (32 bytes) is a common choice for HMAC-SHA256
		random.nextBytes(keyBytes);

		String secretKey = Base64.getEncoder().encodeToString(keyBytes);
		System.out.println("Generated SECRET_KEY : " + secretKey);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if (adminAccount == null) {
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
