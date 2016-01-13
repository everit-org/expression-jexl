package org.everit.expression.jexl.internal;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlArithmetic.MapBuilder;

/**
 * {@link MapBuilder} implementation that creates a {@link LinkedHashMap} instead of a simple hash
 * map to keep the order of keys in case of inline map literals.
 */
public class LinkedHashMapBuilder implements MapBuilder {
  /** The map being created. */
  protected final Map<Object, Object> map;

  /**
   * Creates a new builder.
   *
   * @param size
   *          the expected map size
   */
  public LinkedHashMapBuilder(final int size) {
    map = new LinkedHashMap<Object, Object>(size);
  }

  @Override
  public Object create() {
    return map;
  }

  @Override
  public void put(final Object key, final Object value) {
    map.put(key, value);
  }
}
