package com.wtiinfo;

import com.wtiinfo.model.Address;
import com.wtiinfo.model.Student;
import com.wtiinfo.model.enums.Gender;
import com.wtiinfo.repository.StudentRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			Student student = new Student("Wando", "Borges", "wando@gmail.com",
					Gender.MALE, new Address("Brasil", "São Paulo", "09400-400"),
					List.of("Anatomia", "Criação de Games", "VFX"), BigDecimal.TEN, LocalDateTime.now()
			);
			repository.insert(student);
		};
	}

}
