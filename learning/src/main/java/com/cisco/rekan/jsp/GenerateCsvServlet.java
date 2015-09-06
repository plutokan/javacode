/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */
package com.cisco.rekan.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.webex.webapp.common.cof.WbxConstants;
import com.webex.webapp.common.util.HttpURLUtil;

/**
 * <code>GenerateCsvServlet</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since T29L10NSP3-mywebex Dec 24, 2013
 *
 */
public class GenerateCsvServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3655794073055745979L;

    /**
     * export vector data to csv format.
     *
     * @param vecCsvData Vector
     * @return StringBuffer
     */
    private StringBuffer convertVector2String(Vector vecCsvData) {
        StringBuffer strOut = new StringBuffer();
        for (int i = 0; i < vecCsvData.size(); i++) {
            String[] strLine = (String[]) vecCsvData.elementAt(i);

            if (null != strLine) {
                int colNum = strLine.length;

                for (int j = 0; j < colNum; j++) {
                    if (null != strLine[j]) {
                        strOut.append(strLine[j]);

                        //strOut += strLine[j];
                    }

                    if (j < (colNum - 1)) {
                        strOut.append(",");

                        //strOut += ",";
                    }
                }

                //strOut += "\n";
                strOut.append("\n");
            }
        }
        return strOut;
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     * @param response DOCUMENT ME!
     *
     * @exception ServletException DOCUMENT ME!
     * @exception IOException DOCUMENT ME!
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Vector vecCsvData;
        //set the download file name
        String fileName = (String) request.getAttribute("csvFileName");
        if (StringUtils.isEmpty(fileName)) {
            fileName = "tmp.csv";
        } else {
            fileName += ".csv";
        }

        //set the download file data
        StringBuffer strOut = new StringBuffer();
        String errMsg = (String) request.getAttribute("errMsg");
        if (errMsg == null) {
            vecCsvData = new Vector();
            vecCsvData = (Vector) request.getAttribute("data");
            strOut = this.convertVector2String(vecCsvData);
        } else {
            strOut.append(strOut);
        }
        
        //set the header config
//        response.reset();
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        String strBrowser = new HttpURLUtil().getBrowser(request);

        response.setHeader("Expires", "");
        response.setHeader("Cache-Control", "");
        response.setHeader("Pragma", "cache");
        response.setHeader("Connection", "close");

        if (strBrowser.indexOf(WbxConstants.BROWERS_IE_NAME) >= 0) {
            response.setHeader("Content-type", "application/download");
            response.setHeader("Content-Disposition", "attachment;filename=\""
                    + encode(fileName) + "\"");
            response.setHeader("Cache-Control", "store, cache, must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Keep-Alive", "close");
        } else {
            response.setHeader("Content-type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            response.setHeader("Cache-Control", "store, cache, must-revalidate, post-check=0, pre-check=0");
        }
        response.flushBuffer();

        PrintWriter out;

        try {
            out = response.getWriter();
            out.write(strOut.toString());

            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        this.doGet(request, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#getServletInfo()
     */
    @Override
    public String getServletInfo() {
        return "Download CSV file servlet.";
    }

    /**
     * encode
     *
     * @param str
     *            String
     * @return String
     */
    public static String encode(String str) {
        String newStr = str;
        if (newStr != null && newStr.trim().length() > 0) {
            try {
                newStr = URLEncoder.encode(newStr, WbxConstants.CHARSET_ISO_8859_1.toUpperCase());
                newStr = StringUtils.replace(newStr, "+", "%20");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return newStr;
    }

}
