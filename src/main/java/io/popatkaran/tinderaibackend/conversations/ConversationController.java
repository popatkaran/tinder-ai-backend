package io.popatkaran.tinderaibackend.conversations;

import io.popatkaran.tinderaibackend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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


    /**
     * TODO: add request validator
     */
    @PostMapping("/conversations")
    public Conversation createConversation(@RequestBody ConversationRequest request) {
        profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find profile with Id" + request.profileId()
                ));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                request.profileId(),
                new ArrayList<>()
        );

        conversationsRepository.save(conversation);

        return conversation;
    }

    /**
     * TODO: add request validator
     */
    @PutMapping("/conversations/{id}")
    public Conversation addMessageToConversation(
            @PathVariable String id,
            @RequestBody ConversationMessageRequest conversationMessageRequest
    ) {
        Conversation conversation = conversationsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find conversation."
                ));

        profileRepository.findById(conversationMessageRequest.profileId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Unable to find profile with Id" + conversationMessageRequest.profileId()
                ));

        // TODO: validate if authorId is authorised to add message to the conversation

        conversation.messages().add(
                new ChatMessage(
                    UUID.randomUUID().toString(),
                    id,
                    conversationMessageRequest.profileId(),
                    conversationMessageRequest.message(),
                    LocalDateTime.now()
                )
        );

        conversationsRepository.save(conversation);

        return conversation;
    }
}
