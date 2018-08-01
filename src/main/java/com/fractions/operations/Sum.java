package com.fractions.operations;

import com.fractions.models.Fraction;

public class Sum {
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

            result = Fraction.fromString(String.format("%d/%d", (newLeftFractionNumerator + newRightFractionNumerator), commonDenominator));
        } else {
            result = Fraction.fromString(String.format("%d/%d", (leftOperand.getNumerator() + rightOperand.getNumerator()), leftOperand.getDenominator()));
        }

        return result;
    }

    public static Fraction calculateSumForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            int commonDenominator = leftOperand.getDenominator();
            Fraction rightOperandAsFraction = Fraction.fromString(String.format("%d/%d", (rightOperand.getWholeNumber() * commonDenominator), commonDenominator));
            return calculateSumForFractions(leftOperand, rightOperandAsFraction);
        } else {
            int commonDenominator = rightOperand.getDenominator();
            Fraction leftOperandAsFraction = Fraction.fromString(String.format("%d/%d", (leftOperand.getWholeNumber() * commonDenominator), commonDenominator));
            return calculateSumForFractions(leftOperandAsFraction, rightOperand);
        }
    }
}
