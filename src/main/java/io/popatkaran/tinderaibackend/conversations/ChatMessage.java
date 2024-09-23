package io.popatkaran.tinderaibackend.conversations;

import java.time.LocalDateTime;

public record ChatMessage(
        String id,
        String messageText,
        String authorId,
        String conversationId,
        LocalDateTime messageTime
) {
}
