package com.banadiga.acontrol.statistics.repository;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public abstract class BaseRedisRepositoryIntegrationTest<K, V> {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  protected abstract K getKey();
  protected abstract byte[] getKeyPattern();

  protected abstract V getValue();

  protected abstract Repository<K, V> getRepository();

  @Test
  public void getSet() {
    getRepository().set(getKey(), getValue());
    Optional<V> actual = getRepository().get(getKey());

    assertThat(actual, notNullValue());
    assertThat(actual.isPresent(), is(true));
    assertThat(actual.get(), equalTo(getValue()));
  }

  @Test
  public void delete() {
    getRepository().set(getKey(), getValue());

    getRepository().delete(getKey());

    Optional<V> actual = getRepository().get(getKey());

    assertThat(actual, notNullValue());
    assertThat(actual.isPresent(), is(false));
  }

  @Test
  public void keys() {
    getRepository().set(getKey(), getValue());

    Collection<K> actual = getRepository().keys(getKeyPattern());

    assertThat(actual, notNullValue());
    assertThat(actual, hasItem(getKey()));
  }

  @Test
  public void deleteAll() {
    getRepository().set(getKey(), getValue());

    getRepository().deleteAll();

    Collection<K> actual = getRepository().keys(getKeyPattern());

    assertThat(actual, notNullValue());
    assertThat(actual.size(), is(0));
  }

  @After
  public void afterClass() {
    getRepository().deleteAll();
  }
}
