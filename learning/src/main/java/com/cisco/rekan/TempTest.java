package com.cisco.rekan;

import static java.lang.System.out;

import org.apache.commons.lang.BitField;
import org.apache.commons.lang.RandomStringUtils;

public class TempTest {

    public static void main(String[] args) throws Exception {
        final int AUTOLOCK_BIT = 1 << 0;
        final int FORCELOGIN_BIT = 1 << 1;
        final int NOTIFY_BIT = 1 << 2;
        BitField bitField1 = new BitField(AUTOLOCK_BIT);
        BitField bitField2 = new BitField(FORCELOGIN_BIT);
        BitField bitField3 = new BitField(NOTIFY_BIT);

        int holder = 0;
        holder = bitField1.setBoolean(holder, true);
        holder = bitField2.setBoolean(holder, true);
        holder = bitField3.setBoolean(holder, true);
        out.println(holder);
        out.println(bitField3.isSet(holder));

        /*for (int i=0; i<100; i++) {
            out.println(RandomStringUtils.random(2, 0, 99, false, true));
        }*/
    }

}
