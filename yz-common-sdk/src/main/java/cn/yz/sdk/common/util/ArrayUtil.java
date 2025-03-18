package cn.yz.sdk.common.util;

public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <T> Boolean isEmpty(T[] array){
        if (array == null){
            return true;
        }
        return array.length == 0;
    }

    public static <T> Boolean isNotEmpty(T[] array){
        if (array == null){
            return false;
        }
        return !(array.length == 0);
    }
}
