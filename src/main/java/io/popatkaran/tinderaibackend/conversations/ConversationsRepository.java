package io.popatkaran.tinderaibackend.conversations;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationsRepository extends MongoRepository<Conversation, String> {
}
