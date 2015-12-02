package com.banadiga.acontrol.statistics.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

@Configuration
public class RedisConfig {

  @Value("${redis.password}")
  private String password;

  @Value("${redis.host}")
  private String redisHost;

  @Value("${redis.port}")
  private int redisPort;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

    jedisConnectionFactory.setHostName(redisHost);
    jedisConnectionFactory.setPort(redisPort);

    if (StringUtils.hasLength(password)) {
      jedisConnectionFactory.setPassword(password);
    }

    jedisConnectionFactory.setUsePool(true);
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Long> redisTemplate() {
    RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
