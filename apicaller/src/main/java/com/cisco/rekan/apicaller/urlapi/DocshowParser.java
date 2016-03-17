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
import org.apache.commons.lang.BitField;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
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

    static Logger logger = Logger.getLogger(DocshowParser.class);

    private DocshowParser() {
    }

    /**
     * Gets the clientparam.
     *
     * @param docshow the docshow
     * @return the clientparam
     */
    public static Document getClientparam(Document docshow) {
        assertSuccess(docshow);

        Node node = docshow.selectSingleNode("//MeetingData/MeetingInfo");
        Assert.assertNotNull(node);
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
        logger.debug(param.asXML());

        return param;
    }

    /**
     * @param docshow docshow document.
     */
    public static void assertSuccess(Document docshow) {
        Node statusNode = docshow.selectSingleNode("//MeetingData/Status");
        Assert.assertNotNull(statusNode);
        String status = statusNode.getText();
        Assert.assertEquals(docshow.asXML(), "SUCCESS", status);
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

    public static boolean[] getBitValue4Long(int holder) {
        StringBuffer str = new StringBuffer();
        boolean[] result = new boolean[32];

        BitField bitField = null;
        for (int shift = 0; shift < 32; shift++) {
            int mask = 1 << shift;
            bitField = new BitField(mask);
            result[shift] = bitField.isSet(holder);
        }

        // print the result array.
        for (int i = 31; i >= 0; i--) {
            if (result[i]) {
                str.append("1");
            } else {
                str.append("0");
            }
        }

        return result;
    }

    public static boolean[] getBitValue4Int(int holder) {
        StringBuffer str = new StringBuffer();
        boolean[] result = new boolean[32];

        BitField bitField = null;
        for (int shift = 0; shift < 32; shift++) {
            int mask = 1 << shift;
            bitField = new BitField(mask);
            result[shift] = bitField.isSet(holder);
        }

        // print the result array.
        for (int i = 31; i >= 0; i--) {
            if (result[i]) {
                str.append("1");
            } else {
                str.append("0");
            }
        }

        return result;
    }

    /**
     * @param docshow
     */
    public static void printVideoAddresses(Document docshow) {
        logger.debug("------------------------------------------------------");
        String enableVideoCallBack = DocshowParser.getNodeContent(docshow, "//root/Site/EnableVideoCallBack");
        logger.debug("//root/Site/EnableVideoCallBack{" + enableVideoCallBack + "}");

        // E.g. video address:
        // SmVyZW15J3MgVFA=;amVyZW15ZXgyQHFhLndlYmV4LmNvbQ==;1|VFAwMg==;amVyZW15ZXgyQHFhLndlYmV4LmNvbQ==;0|
        String encodeVA = DocshowParser.getNodeContent(docshow, "//root/User/VideoCallBackInfo");
        StringBuffer decodeVA = new StringBuffer();
        String[] videoAddresses = encodeVA.split("\\|");
        for (String videoAddress : videoAddresses) {
            if (StringUtils.isNotEmpty(videoAddress)) {
                String [] arr = videoAddress.split(";");
                for (String str : arr) {
//                    if (Base64.isBase64(str)) {
                    if (!NumberUtils.isDigits(str)) {
                        decodeVA.append(new String(Base64.decodeBase64(str)));
                    } else {
                        decodeVA.append(str);
                    }
                    decodeVA.append(";");
                }
            }
            decodeVA.append("|");
        }
        logger.debug("//root/User/VideoCallBackInfo{" + decodeVA.toString() + "}");

        String miscOptions = DocshowParser.getNodeContent(docshow, "//root/MeetingType/MiscOptions");
        boolean[] bits = DocshowParser.getBitValue4Int(NumberUtils.toInt(miscOptions));
        String temp = "bit29 is " + bits[29] + ", bit28 is " + bits[28];
        logger.debug("//root/MeetingType/MiscOptions{" + miscOptions + "}{"
                + temp + "}");
    }

}
