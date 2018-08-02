package com.fractions.operations;

import com.fractions.models.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class MultiplyOperationTest {
    private final IOperation multiplyOperation = new MultiplyOperation();

    @Test
    public void testMultiplyOperationForTwoFractions() {
        String expected = "12/12";
        Fraction result = multiplyOperation.calculateResultForFractions(Fraction.fromString("2/3"), Fraction.fromString("6/4"));
        String actual = result.printForResult();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyOperationForWholeNumberAndAFraction() {
        String expected = "69_3/4";
        Fraction result = multiplyOperation.calculateResultForFractionAndWholeNumber(Fraction.fromString("9"), Fraction.fromString("6_7/4"));
        String actual = result.printForResult();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyOperationForTwoMixedFractions() {
        String expected = "12_11/12";
        Fraction result = multiplyOperation.calculateResultForFractions(Fraction.fromString("1_2/3"), Fraction.fromString("6_7/4"));
        String actual = result.printForResult();
        Assert.assertEquals(expected, actual);
    }
}
