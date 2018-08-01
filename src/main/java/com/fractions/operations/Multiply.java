package com.fractions.operations;

import com.fractions.models.Fraction;

public class Multiply extends AbstractBaseOperation {
    public static Fraction calculateProductForFractions(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }

        if (rightOperand.getWholeNumber() > 0) {
            rightOperand = rightOperand.asImproperFraction();
        }

        return getFraction((leftOperand.getNumerator() * rightOperand.getNumerator()), (leftOperand.getDenominator() * rightOperand.getDenominator()));
    }

    public static Fraction calculateProductForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            return getFraction((leftOperand.getNumerator() * rightOperand.getWholeNumber()), leftOperand.getDenominator());
        } else {
            return getFraction((rightOperand.getNumerator() * leftOperand.getWholeNumber()), rightOperand.getDenominator());
        }
    }
}
