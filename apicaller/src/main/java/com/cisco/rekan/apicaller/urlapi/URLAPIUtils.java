/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import org.apache.http.HttpCoreUtils;
import org.apache.http.client.CookieStore;
import org.apache.log4j.Logger;

import com.cisco.rekan.apicaller.Utils;
import com.cisco.rekan.apicaller.urlapi.datemeeting.RegistrationCaller;
import com.cisco.rekan.apicaller.urlapi.user.UserGetAuthInfoCaller;

/**
 * <code>URLAPIUtils</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Oct 23, 2015
 *
 */
public final class URLAPIUtils {

    protected static Logger logger = Logger.getLogger(URLAPIUtils.class);

    private URLAPIUtils() {
    }

    public static String getCsrf(CookieStore cookieStore) {
        if (null == cookieStore) {
            return null;
        }

        String csrf = HttpCoreUtils.getCookieValue(cookieStore, "_csrf_");
        return csrf;
    }

    public static String getCsrf(String url) {
        if (null == url) {
            return null;
        }

        String csrf = Utils.getParamValue4URI(url, "CSRF");
        return csrf;
    }

    public static String getSessionTicket(final String userID, final String password) {
        return UserGetAuthInfoCaller.getEPW(userID, password);
    }

    /**
     * Get session ticket via "datameeting.php?AT=Registration".
     * @param userID WebEx user ID.
     * @param password WebEx user password.
     * @return raw type session ticket.
     */
    public static String getSessionTicket2(final String userID, final String password) {
        RegistrationCaller caller = new RegistrationCaller();

        return caller.register(userID, password);
    }

}
