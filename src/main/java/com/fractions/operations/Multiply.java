package com.fractions.operations;

import com.fractions.models.Fraction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Multiply")
public class Multiply extends AbstractBaseOperation {
    @Override
    public Fraction calculateResultForFractions(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }

        if (rightOperand.getWholeNumber() > 0) {
            rightOperand = rightOperand.asImproperFraction();
        }

        return getFraction((leftOperand.getNumerator() * rightOperand.getNumerator()), (leftOperand.getDenominator() * rightOperand.getDenominator()));
    }

    @Override
    public Fraction calculateResultForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (leftOperand.isFraction()) {
            return getFraction((leftOperand.getNumerator() * rightOperand.getWholeNumber()), leftOperand.getDenominator());
        } else {
            return getFraction((rightOperand.getNumerator() * leftOperand.getWholeNumber()), rightOperand.getDenominator());
        }
    }
}
