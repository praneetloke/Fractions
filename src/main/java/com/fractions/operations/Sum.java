package com.fractions.operations;

import com.fractions.models.Fraction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Sum")
public class Sum extends AbstractBaseOperation {
    @Override
    public Fraction calculateResultForFractions(Fraction leftOperand, Fraction rightOperand) {
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

    @Override
    public Fraction calculateResultForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            int commonDenominator = leftOperand.getDenominator();
            Fraction rightOperandAsFraction = getFraction((rightOperand.getWholeNumber() * commonDenominator), commonDenominator);
            return calculateResultForFractions(leftOperand, rightOperandAsFraction);
        } else {
            int commonDenominator = rightOperand.getDenominator();
            Fraction leftOperandAsFraction = getFraction((leftOperand.getWholeNumber() * commonDenominator), commonDenominator);
            return calculateResultForFractions(leftOperandAsFraction, rightOperand);
        }
    }
}
