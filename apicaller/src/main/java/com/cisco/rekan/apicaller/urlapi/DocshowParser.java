/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.junit.Assert;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.Node;

import com.cisco.rekan.apicaller.Utils;

/**
 * <code>DocshowParser</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Aug 27, 2014
 *
 */
public final class DocshowParser {

    private DocshowParser() {
    }

    /**
     * Gets the clientparam.
     *
     * @param docshow the docshow
     * @return the clientparam
     */
    public static Document getClientparam(Document docshow) {
        Node statusNode = docshow.selectSingleNode("//MeetingData/Status");
        String status = statusNode.getText();
        Assert.assertEquals("SUCCESS", status);

        Node node = docshow.selectSingleNode("//MeetingData/MeetingInfo");
        String meetingInfo = node.getText();
        Properties props = new Properties();
        try {
            props.load(new StringReader(meetingInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String clientParams = props.getProperty("clientparam");
        clientParams = new String(Base64.decodeBase64(clientParams));

        Document param = Utils.convertStr2Dom(clientParams);
        System.out.println(param.asXML());

        return param;
    }

    /**
     * Gets the upload url.
     *
     * @param docshow the docshow
     * @return the upload url
     */
    public static String getUploadURL(Document docshow) {
        return getNodeContent(docshow, "//root/URLs/UploadURL");
    }

    /**
     * Gets the node content.
     *
     * @param docshow the docshow
     * @param path the path
     * @return the node content
     */
    public static String getNodeContent(Document docshow, String path) {
        Node node = docshow.selectSingleNode(path);
        String uploadURL = node.getText();

        return uploadURL;
    }

}
