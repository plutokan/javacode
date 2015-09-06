/*
 * Copyright (C) Cisco-WebEx (China) Software Co., Ltd. HeFei Branch
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.reflect;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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
    
    public static List<String> getClasseNamesInPackage(String jarName,
            String packageName) {
        List<String> arrayList = new ArrayList<String>();
        packageName = packageName.replaceAll("\\.", "/");
        System.out.println("Jar " + jarName + " for " + packageName);
        try {
            JarInputStream jarFile = new JarInputStream(new FileInputStream(
                    jarName));
            JarEntry jarEntry;
            while (true) {
                jarEntry = jarFile.getNextJarEntry();
                if (jarEntry == null) {
                    break;
                }
                if ((jarEntry.getName().startsWith(packageName))
                        && (jarEntry.getName().endsWith(".class"))) {
                    arrayList.add(jarEntry.getName().replaceAll("/", "\\."));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param testObject the test class object.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Object testObject, final String... fieldNames) {
        testGetSetMethod(testObject.getClass(), testObject, false, fieldNames);
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param clazz the test class.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Class<?> clazz, final String... fieldNames) {
        try {
            testGetSetMethod(clazz, clazz.newInstance(), false, fieldNames);
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
        testGetSetMethod(testObject.getClass(), testObject, inverse, fieldNames);
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param clazz the test class.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Class<?> clazz, final boolean inverse, final String... fieldNames) {
        try {
            testGetSetMethod(clazz, clazz.newInstance(), inverse, fieldNames);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param cls the test class.
     * @param testInstance the instance of test class.
     * @param inverse if false, test all the fields in fieldNames. Otherwise, test other fields except fieldNames.
     * @param fieldNames the fields name.
     * @return
     */
    public static void testGetSetMethod(final Class<?> cls,
                                        final Object testInstance,
                                        final boolean inverse,
                                        final String... fieldNames) {
        final Field[] fields = cls.getDeclaredFields();
        System.out.println(cls.getSimpleName());
        Object testValue = null;
        Object actualValue = null;
        String setMethodName = null;
        Method setMethod = null;
        String getMethodName = null;
        Method getMethod = null;
        
        for (int i = 0; i < fields.length; i++) {
            final Field field = fields[i];
            if(Modifier.isStatic(field.getModifiers())
                  ||  Modifier.isFinal(field.getModifiers())
            ) {
                continue;
            }
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
                setMethod.invoke(testInstance, testValue);
                
                getMethodName = getGetMethodName(fieldName, fieldClass);
                getMethod = cls.getMethod(getMethodName);
                actualValue = getMethod.invoke(testInstance);
                
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
        } else if (fieldClass.isEnum()) {
            testValue = fieldClass.getEnumConstants()[0];
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
    public static void testGetSetMethods(final Class<?>... cls) {
        for (int i = 0; i < cls.length; i++) {
            testGetSetMethod(cls[i], false);
        }
    }
    
    /**
     * Unit test for get and set methods of java bean.
     * 
     * @param testObjects the test class objects.
     * @return
     */
    protected static void testGetSetMethods(final Object... testObjects) {
        for (int i = 0; i < testObjects.length; i++) {
            testGetSetMethod(testObjects[i], false);
        }
    }
    
    /**
     * @param object
     * @return is it a standard java bean
     */
    public static boolean getSetBean(Object object) {
        Class cls = object.getClass();
        
        Field fields[] = cls.getDeclaredFields();

        boolean isJavaBean = true;
        
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            
            String fieldName = field.getName();
            
            if (fieldName.equals("serialVersionUID") || fieldName.equals("logger") ) {
                continue;
            }
            if(Modifier.isFinal(field.getModifiers())
                    && Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            
            String firstLetter = fieldName.substring(0, 1).toUpperCase();

            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            String setMethodName = "set" + firstLetter + fieldName.substring(1);

            if(getMethodName.startsWith("getIs")) {
                getMethodName = getMethodName.replaceAll("getIs", "is");
            }
            if(setMethodName.startsWith("setIs")) {
                setMethodName = setMethodName.replaceAll("setIs", "set");
            }
            Method getMethod = null;
            try {
                getMethod = cls.getMethod(getMethodName,
                        new Class[] {});
            } catch (NoSuchMethodException e) {
                System.err.println("NoSuchMethodException: " + e.getMessage());
                continue;
            }
                                   
            System.out.println("call get method: " + getMethodName);            
            Object value = null;
            try {
                value = getMethod.invoke(object, new Object[] {});
            } catch (IllegalArgumentException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            Method setMethod = null;
            try {
                setMethod = cls.getMethod(setMethodName,
                        new Class[] { field.getType() });
                
            }  catch (NoSuchMethodException e) {
                System.err.println("NoSuchMethodException: " + e.getMessage());
                //isJavaBean = false;
                continue;
            }
            
            System.out.println("call set method: " + setMethodName);
            try {
                setMethod.invoke(object, new Object[] { value });
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }  

        }
        return isJavaBean;
    }

    public static boolean getSetBean(Class cls) throws Exception  {
        return getSetBean(cls.newInstance());
    }
}
