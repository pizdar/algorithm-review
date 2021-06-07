package com.zxbayne.algorithm.review.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:zxbayne@outlook.com">zxbayne</a>
 * @date 2021/01/26
 * @since 1.0.0
 */
public class CalculatorTest {
    @Test
    public void testEvalInfixExpression() {
        int result1 = Calculator.testEvalInfixExpression("1+2+3+4+5");
        Assertions.assertEquals(15, result1);

        int result2 = Calculator.testEvalInfixExpression("1*2x3*4");
        Assertions.assertEquals(24, result2);

        int result3 = Calculator.testEvalInfixExpression("8X8/32+5*6");
        Assertions.assertEquals(32, result3);

        int result4 = Calculator.testEvalInfixExpression("11-3*2+2");
        Assertions.assertEquals(3, result4);
    }

    @Test
    public void testEvaluate() {
        int result1 = Calculator.evaluate("1+2*3+(4*5+6)*7");
        Assertions.assertEquals(189, result1);

        int result2 = Calculator.evaluate("11-3*2-3");
        Assertions.assertEquals(2, result2);

        int result3 = Calculator.evaluate("(3+4)*(5-2)");
        Assertions.assertEquals(21, result3);

        int result4 = Calculator.evaluate("((1+2)*(8-7))-(7-5)");
        Assertions.assertEquals(1, result4);

        int result5 = Calculator.evaluate("1+2+3+4+5+6+7");
        Assertions.assertEquals(28, result5);
    }

}
