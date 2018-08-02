package com.fractions.models;

import com.fractions.converters.FractionToStringConverter;
import org.springframework.lang.NonNull;

public class Fraction {
    private int numerator;
    private int denominator;
    private int wholeNumber;
    private boolean isFraction;

    private Fraction(int numerator, int denominator, int wholeNumber, boolean isFraction) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.wholeNumber = wholeNumber;
        this.isFraction = isFraction;
    }

    public static Fraction fromString(String input) {
        int numerator = 0, denominator = 0, wholeNumber = 0;
        boolean isFraction = false;

        // TODO maybe move these validations to a validator?
        if (input.contains("/")) {
            isFraction = true;
            if (!input.contains("_")) {
                String[] parts = input.split("/");
                if (parts[1].equals("0")) {
                    throw new IllegalArgumentException("Denominator of a fraction must not be 0.");
                }
                numerator = Integer.valueOf(parts[0]);
                denominator = Integer.valueOf(parts[1]);
            } else {
                String[] parts = input.split("_");
                if (parts[0].equals("0")) {
                    throw new IllegalArgumentException(String.format("Whole number part of a mixed fraction must be > 0. %s is invalid.", input));
                }
                wholeNumber = Integer.valueOf(parts[0]);
                String[] fractionPart = parts[1].split("/");
                if (fractionPart[1].equals("0")) {
                    throw new IllegalArgumentException("Denominator of a fraction must not be 0.");
                }
                numerator = Integer.valueOf(fractionPart[0]);
                denominator = Integer.valueOf(fractionPart[1]);
            }
        } else {
            wholeNumber = Integer.valueOf(input);
        }

        return new Fraction(numerator, denominator, wholeNumber, isFraction);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getWholeNumber() {
        return wholeNumber;
    }

    public boolean isFraction() {
        return isFraction;
    }

    public Fraction asImproperFraction() {
        return Fraction.fromString(String.format("%d/%d", (denominator * wholeNumber) + numerator, denominator));
    }

    /**
     * A fraction-aware method for printing the String representation of this Fraction.
     * Note: The printAsIs flag only has effect on fraction instances that are constructed from the user's original input.
     * @return A string representation of this Fraction.
     */
    public @NonNull String printForResult() {
        return new FractionToStringConverter(false).convert(this);
    }

    @Override
    public String toString() {
        return new FractionToStringConverter(true).convert(this);
    }
}