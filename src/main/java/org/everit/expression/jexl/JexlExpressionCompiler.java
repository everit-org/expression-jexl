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
package org.everit.expression.jexl;

import java.util.Set;

import org.apache.commons.jexl2.DebugInfo;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlInfo;
import org.apache.commons.jexl2.Script;
import org.everit.expression.CompiledExpression;
import org.everit.expression.ExpressionCompiler;
import org.everit.expression.ParserConfiguration;
import org.everit.expression.jexl.internal.JexlCompiledExpression;

/**
 * Jexl based implementation of {@link ExpressionCompiler}.
 */
public class JexlExpressionCompiler implements ExpressionCompiler {

  @Override
  public CompiledExpression compile(final char[] document, final int expressionStart,
      final int expressionLength,
      final ParserConfiguration parserConfiguration) {
    return compile(String.valueOf(document, expressionStart, expressionLength),
        parserConfiguration);
  }

  @Override
  public CompiledExpression compile(final String expression,
      final ParserConfiguration parserConfiguration) {

    if (parserConfiguration == null) {
      throw new IllegalArgumentException("Parser configuration must be defined");
    }

    JexlInfo jexlInfo = new DebugInfo(parserConfiguration.getName(),
        parserConfiguration.getStartRow(), parserConfiguration.getStartColumn());
    String[] parameterNames = null;
    if (parserConfiguration.getVariableTypes() != null) {
      Set<String> parameterSet = parserConfiguration.getVariableTypes().keySet();
      parameterNames = parameterSet.toArray(new String[parameterSet.size()]);
    }

    JexlEngine jexlEngine = new JexlEngine();
    jexlEngine.setSilent(false);
    jexlEngine.setLenient(false);
    jexlEngine.setClassLoader(parserConfiguration.getClassLoader());

    Script script = jexlEngine.createScript(expression, jexlInfo, parameterNames);
    return new JexlCompiledExpression(script);
  }
}
