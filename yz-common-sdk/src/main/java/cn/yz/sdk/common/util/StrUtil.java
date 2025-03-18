package cn.yz.sdk.common.util;

public class StrUtil{

    private StrUtil() {
    }

    public static Boolean contains(CharSequence str, CharSequence strSearch){
        return str != null && strSearch != null && str.toString().contains(strSearch);
    }

    /**
     * 字符串格式化
     */
    public static String format(CharSequence format,Object... param){
        if (format == null){
            return "null";
        }else {
            String pattern = format.toString();
            for (Object o : param) {
                pattern = pattern.replaceFirst("\\{}", o.toString());
            }
            // 使用 String.format 实现标准的格式化
            return pattern;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        System.out.println(StrUtil.format("{},{}",123,245));
    }
}
