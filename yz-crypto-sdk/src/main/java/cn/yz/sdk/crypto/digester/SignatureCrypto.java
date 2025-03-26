package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.crypto.enumerate.SignatureAlgorithm;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignatureCrypto {

    private SignatureCrypto(){}

    /**
     * 签名
     */
    public static String sign(String data, String key, SignatureAlgorithm algorithm) {
        PrivateKey privateKey = getPrivateKeyFromBase64(key, algorithm);
        return sign(data,privateKey,algorithm);
    }

    public static String sign(String data, PrivateKey privateKey, SignatureAlgorithm algorithm) {
        try {
            Signature signature = createSignature(algorithm);
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            byte[] signedData = signature.sign();
            return Base64.getEncoder().encodeToString(signedData);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    public static boolean verify(String data, String signedData, String key, SignatureAlgorithm algorithm) {
        PublicKey publicKey = getPublicKeyFromBase64(key, algorithm);
        return verify(data,signedData,publicKey,algorithm);
    }
    public static boolean verify(String data, String signedData, PublicKey publicKey, SignatureAlgorithm algorithm) {
        try {
            Signature signature = createSignature(algorithm);
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            byte[] signatureBytes = Base64.getDecoder().decode(signedData);
            return signature.verify(signatureBytes);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }
    private static Signature createSignature(SignatureAlgorithm algorithm) {
        try {
            return Signature.getInstance(algorithm.getType());
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    // 从Base64字符串获取公钥
    private static PublicKey getPublicKeyFromBase64(String key, SignatureAlgorithm algorithm) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm.getType());
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    // 从Base64字符串获取私钥
    private static PrivateKey getPrivateKeyFromBase64(String key, SignatureAlgorithm algorithm) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm.getType());
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));

        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }
}
