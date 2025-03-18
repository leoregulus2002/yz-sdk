package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.common.util.ArrayUtil;
import cn.yz.sdk.common.util.SecureRandomUtil;
import cn.yz.sdk.crypto.enmu.AsymmetricCryptoAlgorithm;
import cn.yz.sdk.crypto.enmu.GlobalBouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class AsymmetricCrypto {

    private AsymmetricCrypto(){};

    public static KeyPair generateKeyPair(AsymmetricCryptoAlgorithm algorithm){
        int keySize = 1024;
        if (AsymmetricCryptoAlgorithm.ECIES.equals(algorithm)) {
            keySize = 256;
        }else if (AsymmetricCryptoAlgorithm.SM2.equals(algorithm)){
            keySize = 256;
        }
        return generateKeyPair(algorithm,keySize);
    }

    public static KeyPair generateKeyPair(AsymmetricCryptoAlgorithm algorithm, int keySize) {
        return generateKeyPair(algorithm, keySize, null);
    }

    public static KeyPair generateKeyPair(AsymmetricCryptoAlgorithm algorithm, int keySize, byte[] seed) {
        if (AsymmetricCryptoAlgorithm.SM2.equals(algorithm)) {
            ECGenParameterSpec sm2p256v1 = new ECGenParameterSpec("sm2p256v1");
            return generateKeyPair(algorithm, keySize, seed, sm2p256v1);
        } else {
            return generateKeyPair(algorithm, keySize, seed, (AlgorithmParameterSpec[])null);
        }
    }

    public static KeyPair generateKeyPair(AsymmetricCryptoAlgorithm algorithm, int keySize, byte[] seed, AlgorithmParameterSpec... params) {
        return generateKeyPair(algorithm, keySize, SecureRandomUtil.createSecureRandom(seed), params);
    }

    public static KeyPair generateKeyPair(AsymmetricCryptoAlgorithm algorithm, int keySize, SecureRandom random, AlgorithmParameterSpec... params) {
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


    public static KeyPairGenerator getKeyPairGenerator(AsymmetricCryptoAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? KeyPairGenerator.getInstance(algorithm.getType()) : KeyPairGenerator.getInstance(algorithm.getType(), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
    }

    public static synchronized String encrypt(String data, AsymmetricCryptoAlgorithm algorithm,Key key){
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes(),algorithm,key));
    }

    public static synchronized byte[] encrypt(byte[] data, AsymmetricCryptoAlgorithm algorithm,Key key){
        Cipher cipher = createCipher(algorithm);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized String decrypt(String data, AsymmetricCryptoAlgorithm algorithm,Key key){
        return new String(decrypt(Base64.getDecoder().decode(data),algorithm,key));
    }

    public static synchronized byte[] decrypt(byte[] data, AsymmetricCryptoAlgorithm algorithm,Key key){
        Cipher cipher = createCipher(algorithm);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Cipher createCipher(AsymmetricCryptoAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? Cipher.getInstance(algorithm.getType()) : Cipher.getInstance(algorithm.getType(), provider);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }
}
