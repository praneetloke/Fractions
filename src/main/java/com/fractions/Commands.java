package com.fractions;

import com.fractions.models.Fraction;
import com.fractions.models.FractionsInput;
import com.fractions.operations.IOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {

    private final IOperation sumOperation;
    private final IOperation differenceOperation;
    private final IOperation divisionOperation;
    private final IOperation multiplyOperation;

    @Autowired
    public Commands(@Qualifier("Sum") IOperation sumOperation,
                    @Qualifier("Difference") IOperation differenceOperation,
                    @Qualifier("Division") IOperation divisionOperation,
                    @Qualifier("Multiply") IOperation multiplyOperation) {
        this.divisionOperation = divisionOperation;
        this.multiplyOperation = multiplyOperation;
        this.sumOperation = sumOperation;
        this.differenceOperation = differenceOperation;
    }

    @ShellMethod("Performs an arithmetic operation on a pair of fraction/whole number operands.")
    public String calculate(@NonNull FractionsInput fractionsInput) {
        System.out.println(String.format("Calculating %s %s %s.", fractionsInput.getLeftOperand(), fractionsInput.getOperator(), fractionsInput.getRightOperand()));

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
            default:
                throw new IllegalArgumentException(String.format("Illegal usage. Unknown operator %s was used.", fractionsInput.getOperator()));
        }

        //TODO should the result be reduced? Greatest Common Divisor?
        // on one hand reducing the fraction makes sense but the experience it gives leads to a bit of confusion because
        // sometimes you just may want to see the simple result of an operation.
        // NOTE: if this is enabled, all of the tests must be updated!

//        int gcd = MathUtils.gcd(result.getNumerator(), result.getDenominator());
//        if (gcd > 1) {
//            result = Fraction.fromString(String.format("%d/%d", (result.getNumerator() / gcd), (result.getDenominator() / gcd)));
//        }
        return result.printForResult();
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
    private @NonNull Fraction calculateSum(@NonNull FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = this.sumOperation.calculateResultForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() + rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = this.sumOperation.calculateResultForFractionAndWholeNumber(leftOperand, rightOperand);
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
    private @NonNull Fraction calculateProduct(@NonNull FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = this.multiplyOperation.calculateResultForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() * rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = this.multiplyOperation.calculateResultForFractionAndWholeNumber(leftOperand, rightOperand);
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
    private @NonNull Fraction calculateDivision(@NonNull FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = this.divisionOperation.calculateResultForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            if (rightOperand.getWholeNumber() == 0) {
                throw new IllegalArgumentException("Cannot divide by 0.");
            }
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() / rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = this.divisionOperation.calculateResultForFractionAndWholeNumber(leftOperand, rightOperand);
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
    private @NonNull Fraction calculateDifference(@NonNull FractionsInput fractionsInput) {
        Fraction result;
        Fraction leftOperand = fractionsInput.getLeftOperand();
        Fraction rightOperand = fractionsInput.getRightOperand();
        // if both of them are fractions
        if (leftOperand.isFraction() && rightOperand.isFraction()) {
            result = this.differenceOperation.calculateResultForFractions(leftOperand, rightOperand);
        } else if (!leftOperand.isFraction() && !rightOperand.isFraction()) {
            // if neither of them are fractions
            result = Fraction.fromString(String.format("%d", leftOperand.getWholeNumber() - rightOperand.getWholeNumber()));
        } else {
            // if one of them is a fraction
            result = this.differenceOperation.calculateResultForFractionAndWholeNumber(leftOperand, rightOperand);
        }

        return result;
    }
}
