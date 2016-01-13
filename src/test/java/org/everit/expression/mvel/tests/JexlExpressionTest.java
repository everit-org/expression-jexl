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
/**
 * This file is part of Everit - Expression MVEL.
 *
 * Everit - Expression MVEL is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Expression MVEL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Expression MVEL.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.expression.mvel.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl3.JexlException;
import org.everit.expression.CompiledExpression;
import org.everit.expression.ParserConfiguration;
import org.everit.expression.jexl.JexlExpressionCompiler;
import org.junit.Assert;
import org.junit.Test;

public class JexlExpressionTest {

  @Test
  public void testExceptionDuringEvaluation() {
    JexlExpressionCompiler compiler = new JexlExpressionCompiler();
    ParserConfiguration parserContext = new ParserConfiguration(this.getClass().getClassLoader());

    parserContext.setStartColumn(11);
    parserContext.setStartRow(21);
    parserContext.setName("exceptionDuringEvaluationExpr");

    String testExpression = " \n   a.substring(-1)";
    CompiledExpression compiledExpression = compiler.compile(testExpression, parserContext);
    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("a", "test");
    try {
      compiledExpression.eval(vars);
      Assert.fail("Exception should have been thrown");
    } catch (JexlException e) {
      e.printStackTrace();
      // TODO
    }
  }

  @Test
  public void testExceptionOnCompilation() {
    JexlExpressionCompiler compiler = new JexlExpressionCompiler();
    ParserConfiguration parserContext = new ParserConfiguration(this.getClass().getClassLoader());

    parserContext.setStartColumn(11);
    parserContext.setStartRow(21);
    parserContext.setName("exceptionOnCompilationExpr");

    String testExpression = " \n  ...xx()";

    try {
      compiler.compile(testExpression, parserContext);
      Assert.fail("Exception should have been thrown");
    } catch (JexlException e) {
      e.printStackTrace();
      // TODO
    }

  }

  @Test
  public void testSimpleExpression() {
    JexlExpressionCompiler compiler = new JexlExpressionCompiler();

    String testExpression = "a + b";

    CompiledExpression compiledExpression = compiler.compile(testExpression,
        new ParserConfiguration(this.getClass().getClassLoader()));

    Map<String, Object> vars = new HashMap<String, Object>();
    vars.put("a", 1);
    vars.put("b", 2);
    Number eval = (Number) compiledExpression.eval(vars);
    Assert.assertEquals(3, eval.intValue());
  }
}
