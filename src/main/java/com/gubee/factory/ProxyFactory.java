package com.gubee.factory;

import com.gubee.repository.MessageRepository;

public interface ProxyFactory {
    MessageRepository createRepository();
}
