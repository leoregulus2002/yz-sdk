package cn.yz.sdk.common.util;

public class StrUtil{

    private StrUtil() {
    }

    public static Boolean contains(CharSequence str, CharSequence strSearch){
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
