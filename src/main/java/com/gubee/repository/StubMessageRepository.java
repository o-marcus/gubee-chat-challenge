package com.gubee.repository;

import com.gubee.annotation.Transactional;

import java.util.*;

public class StubMessageRepository implements MessageRepository {

    Map<UUID, Deque<String>> messages = new HashMap<>();

    @Transactional
    @Override
    public void saveMessage(UUID id, String message) {
        if (id == null || message.isBlank()) throw new NullPointerException();
        if (messages.containsKey(id)) messages.get(id).push(message);
        else  {
            Deque<String> deque = new ArrayDeque<>();
            deque.push(message);
            messages.put(id, deque);
        }
    }
}
