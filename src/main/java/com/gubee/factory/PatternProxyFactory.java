package com.gubee.factory;

import com.gubee.repository.MessageRepository;
import com.gubee.proxy.ProxyPattern;

public class PatternProxyFactory<E extends MessageRepository> implements ProxyFactory {

    private final E repository;

    public PatternProxyFactory(E repository) {
        this.repository = repository;
    }

    @Override
    public MessageRepository createRepository() {
        return new ProxyPattern(repository);
    }

}
