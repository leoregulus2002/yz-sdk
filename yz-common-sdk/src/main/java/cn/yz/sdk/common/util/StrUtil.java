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
            String pattern = format.toString();
            for (int i = 0; i < param.length; i++) {
                pattern = pattern.replaceFirst("\\{}",  param[i].toString());
            }
            // 使用 String.format 实现标准的格式化
            return String.format(pattern, param);
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
