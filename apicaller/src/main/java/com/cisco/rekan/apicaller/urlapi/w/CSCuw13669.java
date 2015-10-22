/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi.w;

import org.apache.http.HttpCoreUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.cisco.rekan.apicaller.Utils;
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
        Logger logger = LogManager.getLogger("com.cisco.rekan");
        logger.setLevel(Level.DEBUG);
        PLoginCaller loginCaller = new PLoginCaller();
        loginCaller.login("pluto", "P@ss1234");
        CookieStore cookieStore = loginCaller.getCookieStore();

        // javax.net.ssl.SSLHandshakeException: Remote host closed connection during handshake
//        SOCaller wSOCaller = new SOCaller();
////        wSOCaller.setProtocol(Constants.PROTOCOL_HTTP);
//        wSOCaller.setHttpClient(loginCaller.getHttpClient());
//        HttpResponse response = wSOCaller.getAPI(csrf);
//        Utils.printContent(response);

//        HttpClient client = Utils.getHttpsClientWithCookies(cookieStore);
        COCaller wCOCaller = new COCaller();
        wCOCaller.setCookieStore(cookieStore);
        String csrf = HttpCoreUtils.getCookieValue(cookieStore, "_csrf_");
        HttpResponse response2 = wCOCaller.get(csrf);
        Utils.printHeaders(response2);
        Utils.printCookies(wCOCaller.getCookieStore());
        Utils.printContent(response2);
    }

}
