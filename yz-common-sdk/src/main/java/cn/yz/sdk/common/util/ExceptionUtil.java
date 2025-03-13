package cn.yz.sdk.common.util;

public class ExceptionUtil {

    private ExceptionUtil(){}

    public static String getMessage(Throwable e) {
        return null == e ? "null" : StrUtil.format("{}: {}", new Object[]{e.getClass().getSimpleName(), e.getMessage()});
    }
}
