package cn.yz.sdk.common.util;

import cn.yz.sdk.common.exception.CollectionException;
import cn.yz.sdk.common.exception.ExceptionCode;

public class CollectionUtil {

    private CollectionUtil() {
    }

    public static <E,T extends Iterable<E>> Boolean isEmpty(T collection){
        if (collection == null){
            throw new CollectionException(ExceptionCode.NOT_NULL);
        }
        return !collection.iterator().hasNext();
    }
}
