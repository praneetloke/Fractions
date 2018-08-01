package com.fractions.converters;

import com.fractions.models.FractionsInput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * A converter to conver the raw user input into a type for our shell command.
 */
@Component
public class FractionsInputConverter implements Converter<String, FractionsInput> {
    // regex to detect continuous whitespace characters (including \t\r)
    private static final Pattern CONTINUOUS_WHITESPACE_PATTERN = Pattern.compile("\\s\\s+");
    private static final Pattern WHOLE_NUMBERS_ONLY_PATTERN = Pattern.compile("[.]");
    private static final List<String> VALID_OPERATORS = Arrays.asList("+", "*","-", "/");

    @Override
    public FractionsInput convert(String source) {
        // remove leading and trailing whitespaces
        source = source.trim();
        String cleanedInput = CONTINUOUS_WHITESPACE_PATTERN.matcher(source).replaceAll(" ");
        String[] inputArray = cleanedInput.split(" ");
        // validate the argument length
        validateInputLength(inputArray);
        // validate operands are not floating point numbers
        validateOperandIsNotFloatingPointNumber(inputArray[0]);
        // validate the operator
        validateOperators(inputArray[1]);
        validateOperandIsNotFloatingPointNumber(inputArray[2]);

        return new FractionsInput(inputArray[0], inputArray[1], inputArray[2]);
    }

    private void validateInputLength(String[] inputArray) {
        // input length must be exactly 3
        if (inputArray.length == 3) {
            return;
        }

        throw new IllegalArgumentException("Invalid usage. Input must contain at most two operands and one operator.");
    }

    private void validateOperators(String operator) {
        if (VALID_OPERATORS.stream().anyMatch(o -> o.equals(operator))) {
            return;
        }

        throw new IllegalArgumentException(String.format("Invalid usage. Allowed operators are %s", VALID_OPERATORS.toString()));
    }

    private void validateOperandIsNotFloatingPointNumber(String operand) {
        if (!WHOLE_NUMBERS_ONLY_PATTERN.matcher(operand).matches()) {
            return;
        }

        throw new IllegalArgumentException(String.format("Invalid usage. Operands must be whole numbers. %s is not a whole number.", operand));
    }
}
