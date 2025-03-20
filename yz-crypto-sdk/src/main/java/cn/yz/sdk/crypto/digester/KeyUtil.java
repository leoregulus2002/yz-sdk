package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.common.util.ArrayUtil;
import cn.yz.sdk.common.util.SecureRandomUtil;
import cn.yz.sdk.crypto.enmu.GlobalBouncyCastleProvider;
import cn.yz.sdk.crypto.enmu.KeyAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class KeyUtil {
    private KeyUtil(){}


    public static String generateSymmetricKey(KeyAlgorithm algorithm) {
        return generateSymmetricKey(algorithm,-1);
    }
    public static String generateSymmetricKey(KeyAlgorithm algorithm,int keySize) {
        KeyGenerator keyGenerator = createSymmetricCrypto(algorithm);
        if (keySize <= 0 && KeyAlgorithm.AES.equals(algorithm)) {
            keySize = 128;
        }
        if (keySize > 0){
            keyGenerator.init(keySize);
        }
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private static KeyGenerator createSymmetricCrypto(KeyAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? KeyGenerator.getInstance(algorithm.getType()) : KeyGenerator.getInstance(algorithm.getType(), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }


    public static KeyPair generateKeyPair(KeyAlgorithm algorithm){
        int keySize = 1024;
        if (KeyAlgorithm.ECIES.equals(algorithm)) {
            keySize = 256;
        }else if (KeyAlgorithm.SM2.equals(algorithm)){
            keySize = 256;
        }else if (KeyAlgorithm.ECDSA.equals(algorithm)){
            keySize = 256;
        }
        return generateKeyPair(algorithm,keySize);
    }

    public static KeyPair generateKeyPair(KeyAlgorithm algorithm, int keySize) {
        return generateKeyPair(algorithm, keySize, null);
    }

    public static KeyPair generateKeyPair(KeyAlgorithm algorithm, int keySize, byte[] seed) {
        if (KeyAlgorithm.SM2.equals(algorithm)) {
            ECGenParameterSpec sm2p256v1 = new ECGenParameterSpec("sm2p256v1");
            return generateKeyPair(algorithm, keySize, seed, sm2p256v1);
        } else {
            return generateKeyPair(algorithm, keySize, seed, (AlgorithmParameterSpec[])null);
        }
    }

    public static KeyPair generateKeyPair(KeyAlgorithm algorithm, int keySize, byte[] seed, AlgorithmParameterSpec... params) {
        return generateKeyPair(algorithm, keySize, SecureRandomUtil.createSecureRandom(seed), params);
    }

    public static KeyPair generateKeyPair(KeyAlgorithm algorithm, int keySize, SecureRandom random, AlgorithmParameterSpec... params) {
        KeyPairGenerator keyPairGen = getKeyPairGenerator(algorithm);
        if (keySize > 0) {
            if (null != random) {
                keyPairGen.initialize(keySize, random);
            } else {
                keyPairGen.initialize(keySize);
            }
        }

        if (ArrayUtil.isNotEmpty(params)) {
            for (AlgorithmParameterSpec param : params) {
                if (null != param) {
                    try {
                        if (null != random) {
                            keyPairGen.initialize(param, random);
                        } else {
                            keyPairGen.initialize(param);
                        }
                    } catch (InvalidAlgorithmParameterException e) {
                        throw new CryptoException(e);
                    }
                }
            }
        }
        return keyPairGen.generateKeyPair();
    }

    private static KeyPairGenerator getKeyPairGenerator(KeyAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? KeyPairGenerator.getInstance(algorithm.getType()) : KeyPairGenerator.getInstance(algorithm.getType(), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }
}
