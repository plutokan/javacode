package com.cisco.rekan;

import static java.lang.System.out;

import org.apache.commons.lang.RandomStringUtils;

public class TempTest {

    public static void main(String[] args) throws Exception {
        // 954205151
        out.println();

        for (int i=0; i<100; i++) {
            out.println(RandomStringUtils.random(2, 0, 99, false, true));
        }
    }

}
