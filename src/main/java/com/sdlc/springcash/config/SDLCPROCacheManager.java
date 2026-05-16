package com.sdlc.springcash.config;

import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class SDLCPROCacheManager implements CacheManager {
    ConcurrentMap<String,Cache>concurrentMap = new ConcurrentHashMap<>();

    @Override
    public Cache getCache(String name) {
        return concurrentMap.computeIfAbsent(name, s -> new SDLCPROCache(s));

    }

    @Override
    public Collection<String> getCacheNames() {
        return concurrentMap.keySet();
    }
}
