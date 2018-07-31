package com.fractions.models;

public class FractionsInput {

    private Fraction leftOperand;
    private String operator;
    private Fraction rightOperand;

    public FractionsInput(String leftOperand, String operator, String rightOperand) {
        this.leftOperand = new Fraction(leftOperand);
        this.operator = operator;
        this.rightOperand = new Fraction(rightOperand);
    }

    public Fraction getLeftOperand() {
        return leftOperand;
    }

    public String getOperator() {
        return operator;
    }

    public Fraction getRightOperand() {
        return rightOperand;
    }

    class Fraction {
        int numerator;
        int denominator;
        int wholeNumber;

        Fraction(String input) {
            if (input.contains("/")) {
                if (!input.contains("_")) {
                    String[] parts = input.split("/");
                    if (parts[1].equals("0")) {
                        throw new IllegalArgumentException("Denominator of a fraction must not be 0.");
                    }
                    this.numerator = Integer.valueOf(parts[0]);
                    this.denominator = Integer.valueOf(parts[1]);
                } else {
                    String[] parts = input.split("_");
                    this.wholeNumber = Integer.valueOf(parts[0]);
                    String[] fractionPart = parts[1].split("/");
                    if (fractionPart[1].equals("0")) {
                        throw new IllegalArgumentException("Denominator of a fraction must not be 0.");
                    }
                    this.numerator = Integer.valueOf(fractionPart[0]);
                    this.denominator = Integer.valueOf(fractionPart[1]);
                }
            }
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
    }
}
