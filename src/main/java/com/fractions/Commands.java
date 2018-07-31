package com.fractions;

import com.fractions.models.FractionsInput;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class Commands {

    @ShellMethod("Performs the operations on a pair of fraction/whole number operands.")
    public String calculate(FractionsInput fractionsInput) {
        switch(fractionsInput.getOperator()) {
            case "+":
                break;
            case "-":
            case "/":
            case "*":
        }
        return String.format("You entered %s %s %s", fractionsInput.getLeftOperand(), fractionsInput.getOperator(), fractionsInput.getRightOperand());
    }
}
