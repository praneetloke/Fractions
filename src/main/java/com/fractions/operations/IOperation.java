package com.fractions.operations;

import com.fractions.models.Fraction;

public interface IOperation {
    Fraction calculateResultForFractions(Fraction leftOperand, Fraction rightOperand);

    Fraction calculateResultForFractionAndWholeNumber(Fraction leftOperand, Fraction rightOperand);
}
