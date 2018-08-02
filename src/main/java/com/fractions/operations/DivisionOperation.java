package com.fractions.operations;

import com.fractions.models.Fraction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Division")
public class DivisionOperation extends AbstractBaseOperation {
    @Override
    public Fraction calculateResultForFractions(Fraction leftOperand, Fraction rightOperand) {
        Fraction result;

        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }

        if (rightOperand.getWholeNumber() > 0) {
            rightOperand = rightOperand.asImproperFraction();
        }

        return getFraction(leftOperand.getNumerator() * rightOperand.getDenominator(), leftOperand.getDenominator() * rightOperand.getNumerator());
    }

    @Override
    public Fraction calculateResultForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand) {
        if (!leftOperand.isFraction()) {
            if (rightOperand.getWholeNumber() > 0) {
                rightOperand = rightOperand.asImproperFraction();
            }
            return getFraction((leftOperand.getWholeNumber() * rightOperand.getDenominator()), rightOperand.getNumerator());
        }

        if (leftOperand.getWholeNumber() > 0) {
            leftOperand = leftOperand.asImproperFraction();
        }
        return getFraction((rightOperand.getWholeNumber() * leftOperand.getDenominator()), leftOperand.getNumerator());
    }
}
