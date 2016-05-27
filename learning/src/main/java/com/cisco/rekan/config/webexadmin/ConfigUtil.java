package com.cisco.rekan.config.webexadmin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>ConfigUtil</code>
 *
 * @author <a href="mailto:rekan@cisco.com">Pluto Kan</a>
 * @since webex-web-applicationframework May 12, 2016
 *
 */
public final class ConfigUtil {
    
    private static final Log logger = LogFactory.getLog(ConfigUtil.class);

    private ConfigUtil() {
    }

    /**
     * Load configure file into properties collection.
     *
     * @param fileName properties file name.
     * @param prop properties collection.
     */
    public static void loadFile2Prop(String fileName, Properties prop) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileName);
            prop.load(in);
        } catch (IOException e) {
            logger.error("File Name:" + fileName, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(null, e);
                }
            }
        }
    }

    /**
     * Load configure file into properties collection.
     *
     * @param fileName properties file name.
     * @param prop properties collection.
     */
    public static void loadDefaultFile2Prop(String fileName, Properties prop) {
        java.io.InputStream in = null;
        try {
            in = ConfigUtil.class.getResourceAsStream(fileName);
            prop.load(in);
        } catch (IOException e) {
            logger.error("File Name:" + fileName, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(null, e);
                }
            }
        }
    }

}
