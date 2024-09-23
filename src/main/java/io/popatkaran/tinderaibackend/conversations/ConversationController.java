package io.popatkaran.tinderaibackend.conversations;

import io.popatkaran.tinderaibackend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class ConversationController {

    private final ConversationsRepository conversationsRepository;
    private final ProfileRepository profileRepository;

    public ConversationController(
            ConversationsRepository conversationsRepository,
            ProfileRepository   profileRepository
    ) {
        this.conversationsRepository = conversationsRepository;
        this.profileRepository = profileRepository;
    }


    @PostMapping("/conversations")
    public Conversation createConversation(@RequestBody ConversationRequest request) {
        profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );

        conversationsRepository.save(conversation);

        return conversation;
    }

}
