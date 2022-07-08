// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.string;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringUtil
{
    private static Logger logger;
    
    public static String concatStrings(final String... strings) {
        if (strings == null) {
            StringUtil.logger.warn("The String to concatenate is null! an empty String will be returned");
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        for (final String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }
    
    static {
        StringUtil.logger = LogManager.getLogger();
    }
}
