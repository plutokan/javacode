/*
 * Copyright (C) Cisco-WebEx (China) Software Co., Ltd. HeFei Branch
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.json;


/**
 * ClassA.java
 *
 * @version 1.0 2011-4-27
 * @author  Pluto Kan, rekan@cisco.com
 */
public class ClassA {
    
    /**
     * EnumTest.java
     *
     * @version 1.0 2011-4-27
     * @author  Pluto Kan, rekan@cisco.com
     */
    public enum EnumTest {
        EnumA,
        EnumB;
    }
    
    EnumTest enumType;
    
    int value;

    
    /**
     * @return the enumType
     */
    public EnumTest getEnumType() {
        return enumType;
    }

    
    /**
     * @param enumType the enumType to set
     */
    public void setEnumType(EnumTest enumType) {
        this.enumType = enumType;
    }

    
    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    
    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ClassA [enumType=" + enumType + ", value=" + value + "]";
    }

}
