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

import java.util.Map;

import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.Script;
import org.everit.expression.CompiledExpression;

/**
 * {@link CompiledExpression} implementation that wraps of a Jexl based {@link Script}.
 */
public class JexlCompiledExpression implements CompiledExpression {

  private final Script jexlScript;

  public JexlCompiledExpression(final Script jexlScript) {
    this.jexlScript = jexlScript;
  }

  @Override
  public Object eval(final Map<String, Object> vars) {
    return jexlScript.execute(new MapContext(vars));
  }

}
