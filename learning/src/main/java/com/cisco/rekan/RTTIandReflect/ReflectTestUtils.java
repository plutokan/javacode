package com.cisco.rekan.RTTIandReflect;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * ReflectTestUtils.java
 * 
 * @author <a href="mailto:pluto@hf.webex.com">Pluto Kan</a>
 * @version v1.0 2010-4-26
 * @since MP8.5
 */
public final class ReflectTestUtils {
    
    private ReflectTestUtils() {
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param clazz the test class.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Class< ? > clazz, final String... fieldNames) {
        try {
            testGetSetMethod(clazz.newInstance(), false, fieldNames);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param testObject the test class object.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Object testObject, final String... fieldNames) {
        testGetSetMethod(testObject, false, fieldNames);
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param clazz the test class.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Class< ? > clazz, final boolean inverse, final String... fieldNames) {
        try {
            testGetSetMethod(clazz.newInstance(), inverse, fieldNames);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param testObject the test class object.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Object testObject, final boolean inverse, final String... fieldNames) {
        final Class< ? > cls = testObject.getClass();
        final Field[] fields = cls.getDeclaredFields();

        Object testValue = null;
        Object actualValue = null;
        String setMethodName = null;
        Method setMethod = null;
        String getMethodName = null;
        Method getMethod = null;
        
        for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            final Class< ? > fieldClass = field.getType();
            final String fieldName = field.getName();
            
            if (!validFieldName(inverse, fieldName, fieldNames)) {
                continue;
            }

            try {
                testValue = getTestValue(fieldClass);
            } catch (Exception e) {
                System.out.println(cls.getSimpleName() + " can not get the field instance of: " + fieldName);
                continue;
            }
            
            try {
                setMethodName = getSetMethodName(fieldName);
                setMethod = cls.getMethod(setMethodName, fieldClass);
                setMethod.invoke(testObject, testValue);
                
                getMethodName = getGetMethodName(fieldName, fieldClass);
                getMethod = cls.getMethod(getMethodName);
                actualValue = getMethod.invoke(testObject);
                
                assertEquals(cls.getSimpleName() + "." + fieldName + " test error.", testValue, actualValue);
            } catch (NoSuchMethodException e) {
                System.err.println("NoSuchMethodException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        
        return;
    }

    private static boolean validFieldName(final boolean inverse, final String fieldName, final String... fieldNames) {
        boolean valid = true;
        for (int j = 0; j < fieldNames.length; j++) {
            if (inverse) {
                if (fieldName.equals(fieldNames[j])) {
                    valid = false;
                    break;
                }
            } else {
                if (!fieldName.equals(fieldNames[j])) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    private static Object getTestValue(final Class< ? > fieldClass)
    throws InstantiationException, IllegalAccessException {
        final String stringTestData = "String-unit-test";
        final Integer intTestData = Integer.MAX_VALUE;
        final Long longTestData = Long.MAX_VALUE;
        final Float floatTestData = Float.MAX_VALUE;
        final Double doubleTestData = Double.MAX_VALUE;
        final Boolean boolTestData = true;
        final Character charTestData = 'K';
        final Byte byteTestData = Byte.MAX_VALUE;
        final Date dateTestData = new Date();
        
        Object testValue = null;
        // Now only process the fields which type is "String"/"int"/"long"/"boolean" etc.
        if (fieldClass.isAssignableFrom(String.class)) {
            testValue = stringTestData;
        } else if (fieldClass.isAssignableFrom(Integer.TYPE)) {
            testValue = intTestData;
        } else if (fieldClass.isAssignableFrom(Long.TYPE)) {
            testValue = longTestData;
        } else if (fieldClass.isAssignableFrom(Float.TYPE)) {
            testValue = floatTestData;
        } else if (fieldClass.isAssignableFrom(Double.TYPE)) {
            testValue = doubleTestData;
        } else if (fieldClass.isAssignableFrom(Boolean.TYPE)) {
            testValue = boolTestData;
        } else if (fieldClass.isAssignableFrom(Character.TYPE)) {
            testValue = charTestData;
        } else if (fieldClass.isAssignableFrom(Byte.TYPE)) {
            testValue = byteTestData;
        } else if (fieldClass.isAssignableFrom(Date.class)) {
            testValue = dateTestData;
        /*} else if (fieldClass.isAnnotation()
                        || fieldClass.isArray()
                        || fieldClass.isInterface()
                        || fieldClass.isAnonymousClass()) {*/
        } else {
            testValue = fieldClass.newInstance();
        }
        
        return testValue;
    }
    
    private static String getGetMethodName(final String fieldName, final Class< ? > fieldClass) {
        final String methodNamePrefix1 = "get";
        final String methodNamePrefix2 = "is";
        
        String methodName = null;
        String prefix = null;
        if (fieldClass.isAssignableFrom(Boolean.TYPE)) {
            prefix = methodNamePrefix2;
        } else {
            prefix = methodNamePrefix1;
        }
        final String firstLetter = fieldName.substring(0, 1).toUpperCase();
        methodName = prefix + firstLetter + fieldName.substring(1);
        
        return methodName;
    }
    
    private static String getSetMethodName(final String fieldName) {
        final String methodNamePrefix = "set";
        final String firstLetter = fieldName.substring(0, 1).toUpperCase();
        return methodNamePrefix + firstLetter + fieldName.substring(1);
    }
    
    /**
     * Run the get and set for java bean class.
     * 
     * @param cls Tested classes.
     * @return
     */
    public static void testGetSetMethods(final Class< ? >... cls) {
        for (int i = 0; i < cls.length; i++) {
            try {
                testGetSetMethod(cls[i].newInstance(), false);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param testObjects the test class objects.
     * @return
     */
    public static void testGetSetMethods(final Object... testObjects) {
        for (int i = 0; i < testObjects.length; i++) {
            testGetSetMethod(testObjects[i], false);
        }
    }
    
}
