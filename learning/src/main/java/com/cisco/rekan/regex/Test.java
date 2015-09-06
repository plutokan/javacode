/**
 * 
 */
package com.cisco.rekan.regex;

import static java.lang.System.out;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author pluto
 *
 */
public class Test {
    
    private static String trimMCCUrl(String mccUrl) {
        if (null == mccUrl) {
            return null;
        }
        
        mccUrl = mccUrl.replaceAll("https://", "");
        mccUrl = mccUrl.replaceAll("http://", "");
        int index = mccUrl.lastIndexOf(":");
        if (index > -1) {
            mccUrl = mccUrl.substring(0, index);
        }
        
        return mccUrl;
    }

    public static void main(String[] args) {
        String mccUrl = "http://pluto.hf.webex";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "https://pluto.hf.webex";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "pluto.hf.webex";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "pluto.hf.webex:8081";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "https://pluto.hf.webex:8081";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "http://pluto.hf.webex:8081";
        out.println(trimMCCUrl(mccUrl));
        mccUrl = null;
        out.println(trimMCCUrl(mccUrl));
        mccUrl = "";
        out.println(trimMCCUrl(mccUrl));
        
        String s = "^(\\s|\\w|`|~|!|@|\\$|%|\\^|&|\\(|\\)|-|=|\\+|\\[|\\{|\\]|\\}|'|,|\\.){1,128}$";
        out.println(match(s, " aA0  `~!@$%^&()-_+=[]{}',."));
        out.println(match(s, "#"));
        out.println(match(s, "\\"));
        out.println(match(s, ";"));
        out.println(match(s, ":"));
        out.println(match(s, "\""));
        out.println(match(s, "/"));
        out.println(match(s, "?"));
        out.println(match(s, "<"));
        out.println(match(s, ">"));
        out.println(match(s, "����"));
    }
    
    static boolean match(String patternString, String matchString) {
        Pattern p = Pattern.compile(patternString);
        Matcher m = p.matcher(matchString);
        boolean b = m.matches();
        return b;
    }
    
}
