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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			Student student = new Student("Wando", "Borges", "wando@gmail.com",
					Gender.MALE, new Address("Brasil", "São Paulo", "09400-400"),
					List.of("Anatomia", "Criação de Games", "VFX"), BigDecimal.TEN, LocalDateTime.now()
			);
			//mongoTemplateAndQuery(repository, mongoTemplate, student);

			repository.findStudentByEmail(student.getEmail()).ifPresentOrElse(s -> {
				System.out.println(s + " already exists");
			}, () -> {
				System.out.println("Inserting student " + student);
				repository.insert(student);
			});

		};
	}

	private void mongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(student.getEmail()));
		List<Student> students = mongoTemplate.find(query, Student.class);

		if(students.size() > 1) {
			throw new IllegalStateException("Found students with this email " + student.getEmail());
		}
		if(students.isEmpty()) {
			System.out.println("LOG: Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println("LOG: " + student  + " already exists");
		}
	}

}
