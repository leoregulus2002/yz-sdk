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
            return java.lang.String.format(format.toString(),param);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
