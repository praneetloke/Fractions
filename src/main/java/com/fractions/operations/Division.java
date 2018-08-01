package com.fractions.operations;

import com.fractions.models.Fraction;

public class Division extends AbstractBaseOperation {
    public static Fraction calculateDivisionForFractions(Fraction leftOperand, Fraction rightOperand) {
        Fraction result;

        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }

        if (rightOperand.getWholeNumber() > 0) {
            rightOperand = rightOperand.asImproperFraction();
        }

        return getFraction(leftOperand.getNumerator() * rightOperand.getDenominator(), leftOperand.getDenominator() * rightOperand.getNumerator());
    }

    public static Fraction calculateDivisionForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (!leftOperand.isFraction()) {
            return getFraction((leftOperand.getWholeNumber() * rightOperand.getDenominator()), rightOperand.getNumerator());
        }

        return getFraction((rightOperand.getWholeNumber() * leftOperand.getDenominator()), leftOperand.getNumerator());
    }
}
