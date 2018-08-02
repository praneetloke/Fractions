package com.fractions.operations;

import com.fractions.models.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class DivisionOperationTest {
    private final IOperation divisionOperation = new DivisionOperation();

    @Test
    public void testDivisionBetweenTwoFractions() {
        String expected = "8/18";
        Fraction result = divisionOperation.calculateResultForFractions(Fraction.fromString("2/3"), Fraction.fromString("6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testDivisionBetweenWholeNumberAndFraction() {
        String expected = "2";
        Fraction result = divisionOperation.calculateResultForFractionAndWholeNumber(Fraction.fromString("3"), Fraction.fromString("6/4"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void testDivisionBetweenTwoMixedFractions() {
        String expected = "25/123";
        Fraction result = divisionOperation.calculateResultForFractions(Fraction.fromString("1_2/3"), Fraction.fromString("7_6/5"));
        String actual = result.printForResult();
        Assert.assertTrue(actual.equals(expected));
    }
}
