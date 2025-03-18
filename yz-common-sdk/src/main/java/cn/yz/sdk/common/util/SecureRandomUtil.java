package cn.yz.sdk.common.util;

import java.security.SecureRandom;

public class SecureRandomUtil {

    public static SecureRandom createSecureRandom(byte[] seed){
        return null == seed ? new SecureRandom() : new SecureRandom(seed);
    }
}
