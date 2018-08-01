package com.fractions.operations;

import com.fractions.models.Fraction;

public class Sum extends AbstractBaseOperation {
    public static Fraction calculateSumForFractions(Fraction leftOperand, Fraction rightOperand) {
        Fraction result;

        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }

        if (rightOperand.getWholeNumber() > 0) {
            rightOperand = rightOperand.asImproperFraction();
        }

        if (leftOperand.getDenominator() != rightOperand.getDenominator()) {
            int commonDenominator = leftOperand.getDenominator() * rightOperand.getDenominator();
            // multiply the denominator of the right fraction to the left fraction's numerator
            int newLeftFractionNumerator = leftOperand.getNumerator() * rightOperand.getDenominator();
            // multiply the denominator of the left fraction to the right fraction's numerator
            int newRightFractionNumerator = rightOperand.getNumerator() * leftOperand.getDenominator();

            result = getFraction((newLeftFractionNumerator + newRightFractionNumerator), commonDenominator);
        } else {
            result = getFraction((leftOperand.getNumerator() + rightOperand.getNumerator()), leftOperand.getDenominator());
        }

        return result;
    }

    public static Fraction calculateSumForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            int commonDenominator = leftOperand.getDenominator();
            Fraction rightOperandAsFraction = getFraction((rightOperand.getWholeNumber() * commonDenominator), commonDenominator);
            return calculateSumForFractions(leftOperand, rightOperandAsFraction);
        } else {
            int commonDenominator = rightOperand.getDenominator();
            Fraction leftOperandAsFraction = getFraction((leftOperand.getWholeNumber() * commonDenominator), commonDenominator);
            return calculateSumForFractions(leftOperandAsFraction, rightOperand);
        }
    }
}
