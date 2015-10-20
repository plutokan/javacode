/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller;

import org.junit.Assert;
import org.junit.Test;

/**
 * <code>UtilsTest</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class UtilsTest {

    @Test
    public void testParseCsrf4URI() {
        String url = "http://pluto.qa.webex.com/pluto/p.php?AT=LI&csrf=12345";
        String csrf = Utils.getParamValue4URI(url, "csrf");
        Assert.assertEquals("12345", csrf);
    }

}
