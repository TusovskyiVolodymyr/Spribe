package org.spribe.utils;

import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AssertObject {
    private final Object actual;
    private final Object expected;
    private final SoftAssert softAssert;

    public AssertObject(Object actual, Object expected) {
        this.actual = actual;
        this.expected = expected;
        this.softAssert = new SoftAssert();
    }

    public AssertObject assertMatchingFields() {
        softAssert.assertNotNull(actual, "Actual object is null");
        softAssert.assertNotNull(expected, "Expected object is null");

        if (actual == null || expected == null) {
            softAssert.assertAll();
            return this;
        }

        Set<String> commonFields = getCommonFields(actual.getClass(), expected.getClass());

        for (String fieldName : commonFields) {
            try {
                Field actualField = actual.getClass().getDeclaredField(fieldName);
                Field expectedField = expected.getClass().getDeclaredField(fieldName);

                actualField.setAccessible(true);
                expectedField.setAccessible(true);

                Object actualValue = actualField.get(actual);
                Object expectedValue = expectedField.get(expected);

                softAssert.assertEquals(actualValue, expectedValue,
                        "Field '" + fieldName + "' mismatch: expected ["
                                + expectedValue + "] but found [" + actualValue + "]");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                softAssert.fail("Error accessing field: " + fieldName + " - " + e.getMessage());
            }
        }
        return this;
    }

    public void assertAll() {
        softAssert.assertAll();
    }

    private Set<String> getCommonFields(Class<?> class1, Class<?> class2) {
        Set<String> fields1 = new HashSet<>();
        for (Field field : class1.getDeclaredFields()) {
            fields1.add(field.getName());
        }

        Set<String> commonFields = new HashSet<>();
        for (Field field : class2.getDeclaredFields()) {
            if (fields1.contains(field.getName())) {
                commonFields.add(field.getName());
            }
        }
        return commonFields;
    }
}
