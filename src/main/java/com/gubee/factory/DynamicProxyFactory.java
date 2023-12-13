package com.gubee.factory;

import com.gubee.proxy.VirtualProxyHandler;
import com.gubee.repository.MessageRepository;

import java.util.function.Supplier;

public class DynamicProxyFactory <E extends MessageRepository> implements ProxyFactory {

    private final Supplier<E> supplier;

    public DynamicProxyFactory(Supplier<E> repository) {
        supplier = repository;
    }

    @Override
    public MessageRepository createRepository() {
        return VirtualProxyHandler.virtualProxy(MessageRepository.class, supplier);
    }

}
