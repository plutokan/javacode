/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webex.webapp.common.exception.WbxAppTokenException;
import com.webex.webapp.common.util.security.AppTokenUtil;


/**
 * <code>GetCICertificateTest</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Dec 4, 2013
 *
 */
public class GetCICertificateTest extends AbstractCASTest {

    @Override
    public void addParams(String... params) {
        super.addParam("appname", params[0]);
        super.addParam("token", params[1]);
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setServerURL("https://10.79.159.62:444/cas/GetCICertificate.do");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws IOException, ClassNotFoundException, WbxAppTokenException {
        String appToken = AppTokenUtil.makeToken(Constants.APPNAME_W11);
        
        Document dom = super.callCAS(Constants.APPNAME_W11, appToken);
        Element element = (Element) dom.selectSingleNode("//CASResponse/certificate");
        String certStr = element.getText();
        byte[] certBytes = Base64.decodeBase64(certStr);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(certBytes));
        X509Certificate cert = (X509Certificate) ois.readObject();
        assertNotNull(cert);
        System.out.println(cert.toString());
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        String appToken = "failed token";
        
        Document dom = super.callCAS(Constants.APPNAME_W11, appToken);
        Element element = (Element) dom.selectSingleNode("//CASResponse/result");
        assertEquals("FAILURE", element.getText());
    }

}
