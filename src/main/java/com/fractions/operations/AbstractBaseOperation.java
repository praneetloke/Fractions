package com.fractions.operations;

import com.fractions.models.Fraction;

public abstract class AbstractBaseOperation implements IOperation {
    /**
     * Returns a new fraction for the given numerator and denominator.
     * @param numerator
     * @param denominator
     * @return A fraction.
     */
    Fraction getFraction(int numerator, int denominator) {
        return Fraction.fromString(String.format("%d/%d", numerator, denominator));
    }

    /**
     * Converts a whole number operand to a fraction operand.
     * @param wholeNumber The whole number to convert to a fraction.
     * @param fractionOperand The fraction operand that has the common denominator used to convert the whole number to a fraction.
     * @return A fraction.
     */
    Fraction convertWholeNumberToFraction(int wholeNumber, Fraction fractionOperand) {
        int commonDenominator = fractionOperand.getDenominator();
        return getFraction((wholeNumber * commonDenominator), commonDenominator);
    }

    /**
     * Checks which operand is not a fraction, converts it, and then performs calculation on the homogeneous operands.
     * @param leftOperand
     * @param rightOperand
     * @return
     */
    @Override
    public Fraction calculateResultForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            return calculateResultForFractions(convertWholeNumberToFraction(rightOperand.getWholeNumber(), leftOperand), leftOperand);
        } else {
            return calculateResultForFractions(convertWholeNumberToFraction(leftOperand.getWholeNumber(), rightOperand), rightOperand);
        }
    }
}
