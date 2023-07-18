package com.example.javaapidemo;


import com.example.javaapidemo.Controller.Parameters;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;


public class ParameterTests {

        private static Parameters parameters;
        private static HashMap<String, String> invalidParameters;



    @Test
    public void testValidParametersOK () {
        parameters = new Parameters("a", "DESC", 10, 1);

        invalidParameters = parameters.checkInvalidParams();
        Assert.isTrue(invalidParameters.isEmpty(),
                "Valid parameters should return empty HashMap");
    }

    @Test
    public void testSortOrderInvalid () {
        parameters = new Parameters("a", "DESCENDING", 10, 1);
        invalidParameters = parameters.checkInvalidParams();

        Assert.isTrue(invalidParameters.containsKey("Sort Order"),
                "Sort Order other than 'ASC' or 'DESC' should be invalid");
    }

}