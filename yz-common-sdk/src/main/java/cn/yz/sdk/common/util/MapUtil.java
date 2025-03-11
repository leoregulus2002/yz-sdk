package cn.yz.sdk.common.util;

import cn.yz.sdk.common.exception.ExceptionCode;
import cn.yz.sdk.common.exception.MapException;

import java.util.Map;

public class MapUtil {

    public static  <K,V,T extends Map<K,V>> Boolean isEmpty(T map){
        if (map == null){
            throw new MapException(ExceptionCode.NOT_NULL);
        }
        return map.isEmpty();
    }
}
