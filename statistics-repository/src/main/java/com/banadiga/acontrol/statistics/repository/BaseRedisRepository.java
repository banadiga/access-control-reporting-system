package com.banadiga.acontrol.statistics.repository;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public abstract class BaseRedisRepository<K, V> implements Repository<K, V> {

  private RedisTemplate<K, V> redisTemplate;

  public BaseRedisRepository(RedisTemplate<K, V> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public Optional<V> get(K key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key));
  }

  @Override
  public void set(K key, V value) {
    redisTemplate.opsForValue().set(key, value);
  }

  @Override
  public Collection<K> keys(byte[] pattern) {
    Set<K> result = new HashSet<K>();

    Iterator<byte[]> it = redisTemplate.getConnectionFactory()
        .getConnection()
        .keys(pattern)
        .iterator();

    while (it.hasNext()) {
      byte[] data = it.next();
      result.add((K) (new String(data, 0, data.length))); // FIXME
    }

    return result;
  }

  @Override
  public void delete(K key) {
    redisTemplate.delete(key);
  }

  @Override
  public void deleteAll() {
    Collection<K> keys = keys("*".getBytes()); // FIXME
    keys.stream().forEach(k -> delete(k));
  }
}
