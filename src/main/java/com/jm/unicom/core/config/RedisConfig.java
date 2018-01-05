package com.jm.unicom.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * RedisConfig
 *
 * @author Eric
 * @date 2017/12/22
 */
@Slf4j
@Configuration
@EnableCaching//启用缓存
public class RedisConfig extends CachingConfigurerSupport {

    @Configuration
    static class LocalConfiguration {
        //从application.properties中获得以下参数
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.port}")
        private Integer port;
        @Value("${spring.redis.password}")
        private String password;
        @Value("${spring.redis.database}")
        private Integer database;
        @Value("${spring.redis.timeout}")
        private Integer timeout;

        /**
         * 缓存管理器.
         *
         * @param redisTemplate
         * @return
         */
        @Bean
        public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
            return new RedisCacheManager(redisTemplate);
        }

        @Bean
        public RedisTemplate<Serializable, Serializable> redisTemplate(
                JedisConnectionFactory redisConnectionFactory) {
            log.info("------------------------------------------");
            log.info("-----------------加载 redis---------------");
            log.info("------------------------------------------");
            RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<Serializable, Serializable>();
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            return redisTemplate;
        }

        @Bean
        public JedisConnectionFactory redisConnectionFactory() {
            JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
            redisConnectionFactory.setDatabase(database);
            redisConnectionFactory.setTimeout(timeout);
            redisConnectionFactory.setHostName(host);
            redisConnectionFactory.setPort(port);
            redisConnectionFactory.setPassword(password);

            return redisConnectionFactory;
        }
    }

    /**
     * 自定义key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key
     */
    @Override
    public KeyGenerator keyGenerator() {
        log.info("RedisCacheConfig.keyGenerator()");
        return (o, method, objects) -> {

            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            log.info("keyGenerator=" + sb.toString());
            return sb.toString();
        };
    }
}
