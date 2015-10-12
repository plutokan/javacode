/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package org.jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * <code>JsoupUtils</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Oct 12, 2015
 *
 */
public class JsoupUtils {

    static Logger logger = Logger.getLogger(JsoupUtils.class);

    private JsoupUtils() {
    }

    /**
     * Get the location href from the http response.
     *
     * @param inStream InputStream in the HttpResponse.
     * @param charsetName UTF-8.
     * @param baseUri The base URI to add on the related link. {@literal E.g. https://qa.webex.com}
     * @return
     * @throws IOException
     */
    public static String parseLocationHref4JsScript(InputStream inStream, String charsetName, String baseUri)
            throws IOException {
        String url = null;
        Document doc = Jsoup.parse(inStream, charsetName, baseUri);
        Element script = doc.select("script").first();

        Pattern p = Pattern.compile("(?is)location.href=\"(.+?)\""); // Regex for the value of the key
        Matcher m = p.matcher(script.html()); // you have to use html here and NOT text! Text will drop the 'key' part
        while (m.find()) {
            logger.info(m.group()); // the whole key ('key = value')
            url = m.group(1); // value only
        }

        return url;
    }

}
