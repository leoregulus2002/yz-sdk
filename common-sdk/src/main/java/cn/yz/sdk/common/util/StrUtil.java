package cn.yz.sdk.common.util;

import com.sun.tools.javac.util.ArrayUtils;
import sun.security.util.ArrayUtil;

public class StrUtil{

    public static Boolean contains(CharSequence str,CharSequence strSearch){
        return str != null && strSearch != null && str.toString().contains(strSearch);
    }

    public static String format(CharSequence format,Object... param){
        if (format == null){
            return "null";
        }else {
            return String.format(format.toString(),param);
        }
    }
}
