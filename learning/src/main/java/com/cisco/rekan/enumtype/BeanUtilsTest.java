/*
 * Copyright (C) Cisco-WebEx (China) Software Co., Ltd. HeFei Branch
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.enumtype;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;


class Orig {
    private String printValue;
    
    
    /**
     * @return the printValue
     */
    public String getPrintValue() {
        return printValue;
    }


    
    /**
     * @param printValue the printValue to set
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }


    private String alertType;

    
    /**
     * @return the alertType
     */
    public String getAlertType() {
        return alertType;
    }

    
    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
}

enum AlertType {
    SitePerformanceAlert;
}

class Target {
    private String printValue;
    
    
    /**
     * @return the printValue
     */
    public String getPrintValue() {
        return printValue;
    }


    
    /**
     * @param printValue the printValue to set
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }


    private AlertType alertType;

    
    /**
     * @return the alertType
     */
    public AlertType getAlertType() {
        return alertType;
    }

    
    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }
}

class Dest2 {
    
    private String printValue;

    private String alertType;
    
    
    
    /**
     * @return the alertType
     */
    public String getAlertType() {
        return alertType;
    }


    
    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }


    /**
     * @return the printValue
     */
    public String getPrintValue() {
        return printValue;
    }

    
    /**
     * @param printValue the printValue to set
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }
}

/**
 * BeanUtilsTest.java
 *
 * @version 1.0 2011-4-25
 * @author  Pluto Kan, rekan@cisco.com
 */
public class BeanUtilsTest {

    /**
     * @param args
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        
        Orig orig = new Orig();
        orig.setAlertType("SitePerformanceAlert");
        orig.setPrintValue("Pluto test");
        
        Target dest = new Target();
        System.out.println(dest.getAlertType());
        System.out.println(dest.getPrintValue());
        
        BeanUtils.copyProperties(dest, orig);
        System.out.println(dest.getAlertType());
        System.out.println(dest.getPrintValue());

        Dest2 dest2 = new Dest2();
        System.out.println(dest2.getPrintValue());
        
        BeanUtils.copyProperties(dest2, orig);
        System.out.println(dest2.getPrintValue());

    }

}
