package com.fractions.operations;

import com.fractions.models.Fraction;

public abstract class AbstractBaseOperation implements IOperation {
    protected static Fraction getFraction(int numerator, int denominator) {
        return Fraction.fromString(String.format("%d/%d", numerator, denominator));
    }
}
