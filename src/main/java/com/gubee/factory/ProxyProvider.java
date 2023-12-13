package com.gubee.factory;

import com.gubee.repository.StubMessageRepository;
import com.gubee.enums.ProxyType;

public class ProxyProvider {
    public static ProxyFactory getFactory(ProxyType type) {
        return switch (type) {
            case VIA_PATTERN_PROXY -> new PatternProxyFactory<>(new StubMessageRepository());
            case VIA_DYNAMIC_PROXY -> new DynamicProxyFactory<>(StubMessageRepository::new);
            default -> throw new IllegalArgumentException();
        };
    }
}
