# expression-jexl

[Jexl][1] based implementation of [expression-api][2]

## Usage

    JexlExpressionCompiler compiler = new JexlExpressionCompiler();
    Expression expression = compiler.compile("a + b", parserConfiguration);
    
    Map<String, Object> vars = new HashMap<>();
    vars.put("a", 1);
    vars.put("b", 2);
    
    System.out.println(expression.eval(vars));
    
## Notes

Jexl is used in strict mode.

[1]: https://commons.apache.org/proper/commons-jexl/
[2]: https://github.com/everit-org/expression-api
