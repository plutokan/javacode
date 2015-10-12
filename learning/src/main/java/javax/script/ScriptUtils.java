/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package javax.script;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * <code>ScriptUtils</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since learning Oct 12, 2015
 *
 */
public final class ScriptUtils {

    static Logger logger = Logger.getLogger(ScriptUtils.class);

    private ScriptUtils() {
    }

    /**
     * Run the javascript method <code>decodeURIComponent</code> to parse the URL string.
     * <pre>
     * E.g. 
     * {@literal
     * https\x3a\x2f\x2fpluto.qa.webex.com\x2fmw3100\x2fmywebex\x2fdefault.do\x3fsiteurl\x3dpluto\x26AT\x3dLI\x26WID\x3dpluto\x26ST\x3dSUCCESS\x26CSRF\x3dc6a1c843-24cd-42d4-bfc5-13bcbd35ac20
     * to
     * 
     * }
     * </pre>
     *
     * @param jsEncodeUrl the encoded URL by javascript.
     * @return the decode URL.
     * @throws ScriptException script exception.
     */
    public static String getUrl4JSDecode(String jsEncodeUrl) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        Object o = engine.eval("decodeURIComponent('" + jsEncodeUrl + "')");
        logger.info(o);

        Assert.notNull(o);
        return o.toString();
    }

}
