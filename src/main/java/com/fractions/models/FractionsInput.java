package com.fractions.models;

public class FractionsInput {

    private Fraction leftOperand;
    private String operator;
    private Fraction rightOperand;

    public FractionsInput(String leftOperand, String operator, String rightOperand) {
        this.leftOperand = Fraction.fromString(leftOperand);
        this.operator = operator;
        this.rightOperand = Fraction.fromString(rightOperand);
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

    @Override
    public String toString() {
        return String.format("%s %s %s", leftOperand.toString(), operator, rightOperand.toString());
    }
}
