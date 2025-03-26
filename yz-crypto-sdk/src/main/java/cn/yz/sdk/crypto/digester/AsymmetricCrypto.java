package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.crypto.enumerate.AsymmetricCryptoAlgorithm;
import cn.yz.sdk.crypto.enumerate.GlobalBouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Provider;
import java.util.Base64;

public class AsymmetricCrypto {

    private AsymmetricCrypto(){}



    public static String encrypt(String data, AsymmetricCryptoAlgorithm algorithm,Key key){
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes(),algorithm,key));
    }

    public static byte[] encrypt(byte[] data, AsymmetricCryptoAlgorithm algorithm,Key key){
        Cipher cipher = createCipher(algorithm);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String data, AsymmetricCryptoAlgorithm algorithm,Key key){
        return new String(decrypt(Base64.getDecoder().decode(data),algorithm,key));
    }

    public static byte[] decrypt(byte[] data, AsymmetricCryptoAlgorithm algorithm,Key key){
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
