/*
 * Copyright (C) Cisco Systems(China)Research and Development Co., Ltd. Hefei
 * Branch Office No. 308 Xiangzhang Drive, Hefei New and High Technology Area,
 * Hefei, Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import static org.junit.Assert.assertNotNull;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.webex.webapp.common.exception.WbxAppTokenException;
import com.webex.webapp.common.util.security.AppTokenUtil;

/**
 * <code>LoginTest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Oct 27, 2013
 */
public class LoginTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("cmd", "login");
        super.addParam("clientid", params[0]);
        super.addParam("email", params[1]);
        super.addParam("password", params[2]);
        super.addParam("appname", params[3]);
        if (params.length > 4) {
            super.addParam("token", params[4]);
        }
    }

    /**
     * Gets the token.
     *
     * @param responseDom the response dom
     * @return the token
     */
    public static String getToken(Document responseDom) {
        String token = null;
        
        Element element = (Element) responseDom.selectSingleNode("//LoginResponse/returnmsg/token");
        Assert.notNull(element);
        token = element.getText();
        System.out.println(token);

        return token;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setServerURL("https://10.79.159.62:444/cas/auth.do");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test4GLA() throws WbxAppTokenException {
        String appToken = AppTokenUtil.makeToken(Constants.APPNAME_GLA);
        
        Document dom = super.callCAS(Constants.CLIENTID_GLA,
                                     "yong03@reaperxuguang01.com",
                                     "P@ssword12345",
                                     Constants.APPNAME_GLA,
                                     appToken);
        assertXMLResultSuccess(dom);
    }

    @Test
    public void LoginNormalUser() {
        Document dom = super.callCAS(Constants.CLIENTID_PAGE, Constants.EMALI_NORMAL,
                                     Constants.PASSWORD_NORMAL, Constants.APPNAME_CI);
        String token = getToken(dom);

        Document userInfoDom = new GetUserMetaInfoTest().callCAS(Constants.EMALI_NORMAL, token, Constants.CLIENTID_PAGE);
        assertNotNull(userInfoDom);

    }

    @Test
    public void LoginMeetingIM1User() {
        Document dom = super.callCAS(Constants.CLIENTID_PAGE,
                                     Constants.EMALI_MEETING_IM_1,
                                     Constants.PASSWORD_NORMAL,
                                     Constants.APPNAME_CI);
        String token = getToken(dom);

        Document userInfoDom = new GetUserMetaInfoTest().
                        callCAS(Constants.EMALI_MEETING_IM_1, token, Constants.CLIENTID_PAGE);
        assertNotNull(userInfoDom);

    }

    @Test
    public void LoginMeeting1User() {
        Document dom = super.callCAS(Constants.CLIENTID_PAGE,
                                     Constants.EMALI_MEETING_1,
                                     Constants.PASSWORD_NORMAL,
                                     Constants.APPNAME_CI);
        String token = getToken(dom);

        Document userInfo = new GetUserMetaInfoTest().
                        callCAS(Constants.EMALI_MEETING_1, token, Constants.CLIENTID_PAGE);
        assertNotNull(userInfo);

    }

    @Test
    public void LoginMeeting2User() {
        Document dom = super.callCAS(Constants.CLIENTID_PAGE,
                                     Constants.EMALI_MEETING_2,
                                     Constants.PASSWORD_NORMAL,
                                     Constants.APPNAME_CI);
        String token = getToken(dom);

        Document userInfo = new GetUserMetaInfoTest().
                        callCAS(Constants.EMALI_MEETING_2, token, Constants.CLIENTID_PAGE);
        assertNotNull(userInfo);

    }

    @Test
    public void login_MANU_EF_MeetOnly() {
        Document dom = super.callCAS(Constants.CLIENTID_MANU,
                                     "ciadmin@szv14efmeetingonly2.com",
                                     Constants.PASSWORD_NORMAL,
                                     Constants.APPNAME_CI);
        assertXMLResultSuccess(dom);
    }

    @Test
    public void login_MANU_EP_MeetOnly() {
        Document dom = super.callCAS(Constants.CLIENTID_MANU,
                                     "ciadmin@szv14epmeetonly.com",
                                     Constants.PASSWORD_NORMAL,
                                     Constants.APPNAME_CI);
        assertXMLResultFailed(dom);
    }


}
