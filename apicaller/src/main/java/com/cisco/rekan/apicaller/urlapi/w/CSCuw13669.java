/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.w;

import org.junit.Test;

import com.cisco.rekan.apicaller.urlapi.p.PLoginCaller;

/**
 * <a href="https://cdetsng.cisco.com/webui/#view=CSCuw13669">CSCuw13669</a>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 12, 2015
 *
 */
public class CSCuw13669 {

    @Test
    public void test() {
        PLoginCaller loginCaller = new PLoginCaller();
        String csrf = loginCaller.login("pluto", "P@ss1234");

        SOCaller wSOCaller = new SOCaller();
//        wSOCaller.setHttpClient(loginCaller.getHttpClient());
        wSOCaller.getAPI(csrf);

    }

}
