/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
