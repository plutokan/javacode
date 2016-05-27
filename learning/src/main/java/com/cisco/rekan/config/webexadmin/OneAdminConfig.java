package com.cisco.rekan.config.webexadmin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * <code>OneAdminConfig</code>
 *
 * @author <a href="mailto:rekan@cisco.com">Pluto Kan</a>
 * @since learning May 12, 2016, T31R2
 *
 */
public final class OneAdminConfig {

    private static final String ONE_ADMIN_PROPERTY_FILE = "webex-one-admin.properties";
    private static final String T31_REMOVED_ITEMS_KEY = "site.options.removed.T31";

    private OneAdminConfig() {
    }

    /**
     * Singleton.
     */
    private static OneAdminConfig instance = new OneAdminConfig();

    /**
     * The only way to get this config.
     *
     * @return config container.
     */
    public static OneAdminConfig getInstance() {
        return instance;
    }

    // To improve the performance of HashSet, needn't thread safe.
    /*private final Set<String> t31RemovedItems = Collections.synchronizedSet(new HashSet<String>());*/
    private final Set<String> t31RemovedItems = new HashSet<String>();

    private static void fillProp2Set(String propStr, final Set<String> set) {
        if (StringUtils.isBlank(propStr)
                || null == set) {
            return;
        }

        propStr = StringUtils.deleteWhitespace(propStr);
        String[] items = StringUtils.split(propStr, ',');
        // propStr is not null, so items should not be null.
        for (String item : items) {
            if (StringUtils.isNotEmpty(item)) {
                set.add(item);
            }
        }
    }

    public void init(String configDir) throws IOException {
        t31RemovedItems.clear();

        Properties prop = new Properties();
        ConfigUtil.loadFile2Prop(configDir + ONE_ADMIN_PROPERTY_FILE, prop);
        String t31RemovedItemsStr = prop.getProperty(T31_REMOVED_ITEMS_KEY);
        fillProp2Set(t31RemovedItemsStr, this.t31RemovedItems);
    }

    public void initDefault(String configDir) throws IOException {
        t31RemovedItems.clear();

        Properties prop = new Properties();
        ConfigUtil.loadDefaultFile2Prop(configDir + ONE_ADMIN_PROPERTY_FILE, prop);
        String t31RemovedItemsStr = prop.getProperty(T31_REMOVED_ITEMS_KEY);
        fillProp2Set(t31RemovedItemsStr, this.t31RemovedItems);
    }

    /**
     * Check a item for site configuration removed or not in T31.
     *
     * @param itemName DB item name.
     * @return true or false.
     */
    public boolean isT31Removed(String itemName) {
        return t31RemovedItems.contains(itemName);
    }

}
