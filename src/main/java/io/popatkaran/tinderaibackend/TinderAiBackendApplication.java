package io.popatkaran.tinderaibackend;

import io.popatkaran.tinderaibackend.profiles.Gender;
import io.popatkaran.tinderaibackend.profiles.Profile;
import io.popatkaran.tinderaibackend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	public static void main(String[] args) {
		SpringApplication.run(TinderAiBackendApplication.class, args);
	}

	public void run(String... args) {
		//start application
		System.out.println("Starting TINDER AI");

		//insert first profile
		System.out.println("Insert first profile");
		Profile profile = new Profile(
				"1",
				"Karan",
				"Popat",
				32,
				"Indian",
				Gender.MALE,
				"Software Programmer",
				"karan.jpg",
				"INTP"
		);

		//save profile using repository
		profileRepository.save(profile);

		//find all profiles using repository
		profileRepository.findAll().forEach(System.out::println);
	}

}
