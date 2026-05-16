package com.sdlc.springcash.config;

import org.jspecify.annotations.Nullable;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;

@Configuration
public class SDLCProCashConfig {
    @Bean
    public CacheManager SDLCPROCacheManager()
    {
      return   new SDLCPROCacheManager();
    }


}
