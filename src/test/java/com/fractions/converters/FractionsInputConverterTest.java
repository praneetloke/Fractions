package com.fractions.converters;

import com.fractions.models.FractionsInput;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

public class FractionsInputConverterTest {

    @Test
    public void convert() {
        Converter<String, FractionsInput> converter = new FractionsInputConverter();
        FractionsInput fractionsInput = converter.convert("1 + 2");
        Assert.assertEquals("1 + 2", fractionsInput.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void failConvert() {
        Converter<String, FractionsInput> converter = new FractionsInputConverter();
        converter.convert("1 + 2.3");
    }

    @Test
    public void convertWithMultipleSpaces() {
        Converter<String, FractionsInput> converter = new FractionsInputConverter();
        FractionsInput fractionsInput = converter.convert("1 +      1/2");
        Assert.assertEquals("1 + 1/2", fractionsInput.toString());
    }

    @Test
    public void convertWithMultipleSpaces2() {
        Converter<String, FractionsInput> converter = new FractionsInputConverter();
        FractionsInput fractionsInput = converter.convert("   1_1/13          +      1/2  ");
        Assert.assertEquals("1_1/13 + 1/2", fractionsInput.toString());
    }
}