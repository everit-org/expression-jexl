package org.everit.expression.jexl.internal;

import org.apache.commons.jexl3.JexlArithmetic;

/**
 * Customized version of JexlArithmetic that uses sort-order keeping map.
 */
public final class CustomizedJexlArithmetic extends JexlArithmetic {
  public CustomizedJexlArithmetic(final boolean astrict) {
    super(astrict);
  }

  @Override
  public MapBuilder mapBuilder(final int size) {
    return new LinkedHashMapBuilder(size);
  }
}
