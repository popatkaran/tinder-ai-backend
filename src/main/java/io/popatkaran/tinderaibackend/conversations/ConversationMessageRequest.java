package io.popatkaran.tinderaibackend.conversations;

public record ConversationMessageRequest(
        String profileId,
        String message
) {
}
