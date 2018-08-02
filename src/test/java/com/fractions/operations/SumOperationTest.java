package com.fractions.operations;

import com.fractions.models.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class SumOperationTest {
    private final IOperation sumOperation = new SumOperation();

    @Test
    public void testSumOfTwoFractions() {
        String expected = "2_2/12";
        Fraction result = sumOperation.calculateResultForFractions(Fraction.fromString("2/3"), Fraction.fromString("6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testSumOfWholeNumberAndAFraction() {
        String expected = "2_1/2";
        Fraction result = sumOperation.calculateResultForFractionAndWholeNumber(Fraction.fromString("2"), Fraction.fromString("1/2"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }
}
