package com.gubee.repository;

import com.gubee.annotation.Transactional;
import java.util.UUID;

public interface MessageRepository {
    @Transactional
    void saveMessage(UUID id, String message) throws NoSuchMethodException;
}
