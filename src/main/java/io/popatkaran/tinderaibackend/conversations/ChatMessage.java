package io.popatkaran.tinderaibackend.conversations;

import java.time.LocalDateTime;

public record ChatMessage(
        String id,
        String conversationId,
        String authorId,
        String messageText,
        LocalDateTime messageTime
) {
}
