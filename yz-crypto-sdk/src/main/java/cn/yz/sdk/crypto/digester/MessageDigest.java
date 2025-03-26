package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.common.util.StrUtil;
import cn.yz.sdk.crypto.enumerate.SummaryAlgorithm;
import cn.yz.sdk.crypto.enumerate.GlobalBouncyCastleProvider;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class MessageDigest {
    private MessageDigest(){

    }

    public static String hex(String str, SummaryAlgorithm summaryAlgorithm){
        java.security.MessageDigest messageDigest = createMessageDigest(summaryAlgorithm);
        byte[] hashBytes = messageDigest.digest(str.getBytes());
        return StrUtil.bytesToHex(hashBytes);
    }

    private static java.security.MessageDigest createMessageDigest(SummaryAlgorithm summaryAlgorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? java.security.MessageDigest.getInstance(summaryAlgorithm.getType()) : java.security.MessageDigest.getInstance(summaryAlgorithm.getType(), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

}
