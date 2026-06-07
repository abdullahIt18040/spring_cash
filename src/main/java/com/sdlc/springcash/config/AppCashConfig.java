package com.sdlc.springcash.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Configuration
public class AppCashConfig {
    @Primary
    @Bean("caffeineCacheManager")
    public CaffeineCacheManager caffeineCacheManager()
    {
        var caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(60)));
        return caffeineCacheManager;

    }
    @Bean( "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper)
    {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30));
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .withCacheConfiguration("product",RedisCacheConfiguration
                        .defaultCacheConfig()
                        .entryTtl(Duration.ofSeconds(50))
                        )

                .build();
    }

    @Bean("sdlcProCacheResolver")
    public CacheResolver sdlcproCacheResolver(CaffeineCacheManager caffeineCacheManager,
                                              RedisCacheManager redisCacheManager)
    {
        return new CacheResolver() {
            @Override
            public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {

              Integer pid =(Integer) context.getArgs()[0];
                return isHotproduct(pid)?buildCache(caffeineCacheManager,context.getOperation().getCacheNames())
                        :buildCache(redisCacheManager,context.getOperation().getCacheNames()) ;
            }
        };
    }
    private Collection<Cache> buildCache(CacheManager cacheManager, Set<String> cacheNames){


      List<Cache>caches = new ArrayList<>(cacheNames.size());
      for(var name :cacheNames){
          caches.add(cacheManager.getCache(name));
      }
      return caches;

    }


    private boolean isHotproduct(Integer id)
    {
        return id%2==0;
    }
}
