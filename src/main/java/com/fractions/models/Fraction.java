package com.fractions.models;

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

    private String printImproperFractionAsMixedFraction() {
        return String.format("%d_%d/%d", (numerator / denominator), (numerator % denominator), denominator);
    }

    public Fraction asImproperFraction() {
        return Fraction.fromString(String.format("%d/%d", (denominator * wholeNumber) + numerator, denominator));
    }

    /**
     * A fraction-aware method for printing the String representation of this Fraction.
     *
     * @return String The string representation.
     */
    public @NonNull String printForResult(boolean printAsIs) {
        if (!isFraction) {
            return String.format("%d", wholeNumber);
        }

        // if both numerator and the whole number are 0's, then this fraction has no value
        if (numerator == 0 && wholeNumber == 0) {
            return "0";
        }

        // for a fraction, if the denominator is a 1, then the numerator is the result
        if (denominator == 1) {
            return String.format("%d", numerator);
        }

        if (wholeNumber > 0) {
            return String.format("%d_%d/%d", wholeNumber, numerator, denominator);
        } else if (!printAsIs && numerator > denominator) {
            // if we don't have to print this Fraction instance as-is, then convert the improper fraction to a mixed one
            // and this is an improper fraction since there is no whole number part and the numerator is greater than the denominator
            return printImproperFractionAsMixedFraction();
        }

        return String.format("%d/%d", numerator, denominator);
    }

    @Override
    public String toString() {
        return printForResult(true);
    }
}