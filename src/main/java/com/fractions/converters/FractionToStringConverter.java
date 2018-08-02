package com.fractions.converters;

import com.fractions.models.Fraction;
import org.springframework.core.convert.converter.Converter;

public class FractionToStringConverter implements Converter<Fraction, String> {
    private boolean printAsIs;

    public FractionToStringConverter(boolean printAsIs) {
        this.printAsIs = printAsIs;
    }

    @Override
    public String convert(Fraction source) {
        boolean isFraction = source.isFraction();
        int numerator = source.getNumerator();
        int denominator = source.getDenominator();
        int wholeNumber = source.getWholeNumber();

        if (!isFraction) {
            return String.format("%d", wholeNumber);
        }

        // if both numerator and the whole number are 0's, then this fraction has no value
        if (numerator == 0 && wholeNumber == 0) {
            return "0";
        }

        // for a fraction, if the denominator is a 1 and there is no whole number part, i.e., this is not a mixed fraction, then the numerator is the result
        if (denominator == 1 && wholeNumber == 0) {
            return String.format("%d", numerator);
        }

        if (wholeNumber > 0) {
            return String.format("%d_%d/%d", wholeNumber, numerator, denominator);
        } else if (!printAsIs) {
            if ((numerator > 0 && numerator > denominator) ||
                    numerator < 0 && ((numerator * -1) > denominator)) {
                // if we don't have to print this Fraction instance as-is, then convert the improper fraction to a mixed one
                // and this is an improper fraction since there is no whole number part and the numerator is greater than the denominator
                return printImproperFractionAsMixedFraction(numerator, denominator);
            }
        }

        return String.format("%d/%d", numerator, denominator);
    }

    private String printImproperFractionAsMixedFraction(int numerator, int denominator) {
        int divisor = (numerator / denominator);
        int remainder = (numerator % denominator);
        if (remainder == 0) {
            return String.format("%d", divisor);
        }
        // if the numberator is negative, in the mixed fraction form, the sign will be carried by the whole number part, so we have to convert the numerator to a positive int.
        int mixedFractionNumerator = (numerator > 0 ? remainder : (remainder * -1));
        return String.format("%d_%d/%d", divisor, mixedFractionNumerator, denominator);
    }
}
