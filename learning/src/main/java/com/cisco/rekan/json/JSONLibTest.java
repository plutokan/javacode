/*
 * Copyright (C) Cisco-WebEx (China) Software Co., Ltd. HeFei Branch
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.json;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * JSONLibTest.java
 *
 * @version 1.0 2011-4-27
 * @author  Pluto Kan, rekan@cisco.com
 */
public class JSONLibTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "[{\"enumType\":\"EnumA\",\"value\":7890},{\"enumType\":\"EnumB\",\"value\":9787}]";

        JSONArray paramsArray = JSONArray.fromObject(str);
        Collection<ClassA> confKeyList = JSONArray.toCollection(paramsArray, ClassA.class);
        
        for (ClassA classA : confKeyList) {
            System.out.println(classA);
        }
        
        String objStr = "{\"enumType\":\"EnumA\",\"value\":3483574}";
        System.out.println(convertStr2Obj(objStr));
    }

    public static ClassA convertStr2Obj(String jsonStr) {
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        ClassA obj = (ClassA) JSONObject.toBean(jsonObj, ClassA.class);
        
        return obj;
    }

}
