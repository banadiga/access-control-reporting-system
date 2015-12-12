package com.banadiga.acontrol.statistics.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<K, V> {

  Optional<V> get(K keys);

  void set(K key, V value);

  Collection<K> keys(byte[] pattern);

  void delete(K key);

  void deleteAll();
}
