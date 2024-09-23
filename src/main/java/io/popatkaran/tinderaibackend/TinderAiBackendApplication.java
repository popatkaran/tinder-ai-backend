package io.popatkaran.tinderaibackend;

import io.popatkaran.tinderaibackend.conversations.ChatMessage;
import io.popatkaran.tinderaibackend.conversations.Conversation;
import io.popatkaran.tinderaibackend.conversations.ConversationsRepository;
import io.popatkaran.tinderaibackend.profiles.Gender;
import io.popatkaran.tinderaibackend.profiles.Profile;
import io.popatkaran.tinderaibackend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConversationsRepository conversationsRepository;

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

		profileRepository.save(profile);
		profileRepository.findAll().forEach(System.out::println);

		//insert first conversation
		System.out.println("Insert first profile");
		Conversation conversation = new Conversation(
				"1",
				profile.id(),
				List.of(
						new ChatMessage(
								"1",
								"Hello",
								profile.id(),
								"1",
								LocalDateTime.now()
						)
				)
		);

		conversationsRepository.save(conversation);
		conversationsRepository.findAll().forEach(System.out::println);
	}
}
