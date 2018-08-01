package com.fractions;

import com.fractions.models.Fraction;
import com.fractions.models.FractionsInput;
import com.fractions.operations.Division;
import com.fractions.operations.Multiply;
import com.fractions.operations.Sum;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@ShellComponent
public class Commands {

    @ShellMethod("Performs the operations on a pair of fraction/whole number operands.")
    public String calculate(FractionsInput fractionsInput) {
        System.out.println(String.format("Calculating %s %s %s", fractionsInput.getLeftOperand(), fractionsInput.getOperator(), fractionsInput.getRightOperand()));

        Fraction result = null;
        switch(fractionsInput.getOperator()) {
            case "+":
                result = calculateSum(fractionsInput);
                break;
            case "-":
                result = calculateDifference(fractionsInput);
                break;
            case "/":
                result = calculateDivision(fractionsInput);
                break;
            case "*":
                result = calculateProduct(fractionsInput);
                break;
        }

        //TODO should the result be reduced? Greatest Common Divisor?
        return result.printForResult(false);
    }

    /**
     * Calculates the sum of the operands.
     * The operands have 3 cases:
     * - both of them are fractions
     * - neither of them are fractions
     * - only one of them is a fraction
     *
     * @param fractionsInput The converted FractionsInput to calculate the sum.
     * @return A Fraction containing the result. The result can be printed using the Fraction.printForResult() method.
     */
    private Fraction calculateSum(FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = Sum.calculateSumForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() + rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = Sum.calculateSumForFractionAndWholeNumber(leftOperand, rightOperand);
        }

        return result;
    }

    /**
     * Calculates the product of the operands.
     * The operands have 3 cases:
     * - both of them are fractions
     * - neither of them are fractions
     * - only one of them is a fraction
     *
     * @param fractionsInput The converted FractionsInput to calculate the product.
     * @return A Fraction containing the result. The result can be printed using the Fraction.printForResult() method.
     */
    private Fraction calculateProduct(FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = Multiply.calculateProductForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() * rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = Multiply.calculateProductForFractionAndWholeNumber(leftOperand, rightOperand);
        }

        return result;
    }

    /**
     * Calculates the division of the operands.
     * The operands have 3 cases:
     * - both of them are fractions
     * - neither of them are fractions
     * - only one of them is a fraction
     *
     * @param fractionsInput The converted FractionsInput to calculate the division.
     * @return A Fraction containing the result. The result can be printed using the Fraction.printForResult() method.
     */
    private Fraction calculateDivision(FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = Division.calculateDivisionForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            if (rightOperand.getWholeNumber() == 0) {
                throw new IllegalArgumentException("Cannot divide by 0.");
            }
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() / rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = Division.calculateDivisionForFractionAndWholeNumber(leftOperand, rightOperand);
        }

        return result;
    }

    /**
     * Calculates the difference of the operands.
     * The operands have 3 cases:
     * - both of them are fractions
     * - neither of them are fractions
     * - only one of them is a fraction
     *
     * @param fractionsInput The converted FractionsInput to calculate the difference.
     * @return A Fraction containing the result. The result can be printed using the Fraction.printForResult() method.
     */
    private Fraction calculateDifference(FractionsInput fractionsInput) {
        throw new NotImplementedException();
    }
}
