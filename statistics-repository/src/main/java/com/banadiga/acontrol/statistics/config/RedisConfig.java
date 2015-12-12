package com.banadiga.acontrol.statistics.config;

import com.banadiga.acontrol.statistics.module.Report;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

@Configuration
public class RedisConfig {

  @Value("${redis.host:localhost}")
  private String redisHost;

  @Value("${redis.port:6379}")
  private int redisPort;

  @Value("${redis.password:}")
  private String password;

  @Value("${redis.usePool:true}")
  private boolean usePool;

  @Value("${redis.maxPool:100}")
  private int maxPool;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

    jedisConnectionFactory.setHostName(redisHost);
    jedisConnectionFactory.setPort(redisPort);

    if (StringUtils.hasLength(password)) {
      jedisConnectionFactory.setPassword(password);
    }

    jedisConnectionFactory.setUsePool(usePool);
    jedisConnectionFactory.getPoolConfig().setMaxTotal(maxPool);
    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Long> redisStatisticsTemplate(JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public RedisTemplate<String, Report> redisReportTemplate(JedisConnectionFactory jedisConnectionFactory) {
    RedisTemplate<String, Report> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
