package com.fractions.operations;

import com.fractions.models.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class DifferenceOperationTest {
    private final IOperation differenOperation = new DifferenceOperation();

    @Test
    public void testDifferenceOfTwoFractions() {
        String expected = "-10/12";
        Fraction result = differenOperation.calculateResultForFractions(Fraction.fromString("2/3"), Fraction.fromString("6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testDifferenceOfAFractionAndAWholeNumber() {
        String expected = "-2/4";
        Fraction result = differenOperation.calculateResultForFractionAndWholeNumber(Fraction.fromString("-2"), Fraction.fromString("-6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testDifferenceOfAMixedFractionAndAWholeNumber() {
        String expected = "-4_2/4";
        Fraction result = differenOperation.calculateResultForFractionAndWholeNumber(Fraction.fromString("-2"), Fraction.fromString("1_6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }
}
