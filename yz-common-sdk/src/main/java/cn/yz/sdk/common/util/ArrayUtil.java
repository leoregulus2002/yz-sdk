package cn.yz.sdk.common.util;

import cn.yz.sdk.common.exception.ArrayException;
import cn.yz.sdk.common.exception.ExceptionCode;

public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <T> Boolean isEmpty(T[] array){
        if (array == null){
            throw new ArrayException(ExceptionCode.NOT_NULL);
        }
        return array.length == 0;
    }
}
