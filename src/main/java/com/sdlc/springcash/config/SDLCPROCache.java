package com.sdlc.springcash.config;

import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SDLCPROCache implements Cache {
    private final String name;
    private final ConcurrentMap<Object,Object>storage;

    public SDLCPROCache(String name) {
        this.name = name;
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = this.storage.get(key);
        return value != null ? new SimpleValueWrapper(value):null;
    }

    @Override
    public <T> @Nullable T get(Object key, @Nullable Class<T> type) {
        return null;
    }

    @Override
    public <T> @Nullable T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        this.storage.put(key, value);

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
