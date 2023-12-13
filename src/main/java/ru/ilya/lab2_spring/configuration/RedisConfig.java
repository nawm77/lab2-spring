package ru.ilya.lab2_spring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.ilya.lab2_spring.dto.BrandDTO;
import ru.ilya.lab2_spring.dto.ModelDTO;
import ru.ilya.lab2_spring.model.viewModel.BrandModelViewModel;

import java.time.Duration;

import static ru.ilya.lab2_spring.service.util.Constants.*;

@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration cacheConfig = cacheConfiguration().disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration(REDIS_BRANDS_CACHE_NAME, cacheConfiguration(BrandDTO.class, 15))
                .withCacheConfiguration(REDIS_BRANDS_AND_MODELS_CACHE_NAME, cacheConfiguration(BrandModelViewModel.class, 15))
                .withCacheConfiguration(REDIS_MODELS_CACHE_NAME, cacheConfiguration(ModelDTO.class, 15))
                .build();
    }

    private <T> RedisCacheConfiguration cacheConfiguration(Class<T> clazz, int duration) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new Jackson2JsonRedisSerializer<>(clazz)))
                .entryTtl(Duration.ofMinutes(duration));
    }


//    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
//        return RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(duration)
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .entryTtl(Duration.ofMinutes(10));
    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
//
//        return new LettuceConnectionFactory(configuration);
//    }

//    @Bean
//    public RedisCacheManager cacheManager() {
//        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();
//        var jacksonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper());
//        var valueSerializer = RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer);
//        return RedisCacheManager.builder(redisConnectionFactory())
//                .cacheDefaults(cacheConfig)
//                .withCacheConfiguration("brands", myDefaultCacheConfig(Duration.ofMinutes(10)).serializeValuesWith(valueSerializer))
//                .build();
//    }
//
//
//
//    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
//        return RedisCacheConfiguration
//                .defaultCacheConfig()
//                .entryTtl(duration)
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }
//@Bean
//public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
//    var jacksonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper());
//
//    var valueSerializer = RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer);
//    return (builder) -> builder
//            .withCacheConfiguration("brands",
//                    RedisCacheConfiguration.defaultCacheConfig()
//                            .serializeValuesWith(valueSerializer)
//                            .entryTtl(Duration.ofMinutes(20)))
//            .withCacheConfiguration("offers",
//                    RedisCacheConfiguration.defaultCacheConfig()
//                            .serializeValuesWith(valueSerializer)
//                            .entryTtl(Duration.ofMinutes(20)))
//            .withCacheConfiguration("users",
//                    RedisCacheConfiguration.defaultCacheConfig()
//                            .serializeValuesWith(valueSerializer)
//                            .entryTtl(Duration.ofMinutes(20)))
//            .withCacheConfiguration("models",
//                    RedisCacheConfiguration.defaultCacheConfig()
//                            .serializeValuesWith(valueSerializer)
//                            .entryTtl(Duration.ofMinutes(20)));
//}

//    @Bean
//    public ObjectMapper objectMapper() {
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
////        return JsonMapper.builder()
////                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
////                .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
////                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
////                .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
////                .addModule(javaTimeModule)
////                .findAndAddModules()
////                .build();
//        return new ObjectMapper()
//                .registerModule(javaTimeModule)
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
//                .findAndRegisterModules();
//    }
}
