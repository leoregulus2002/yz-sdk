package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.crypto.enmu.GlobalBouncyCastleProvider;
import cn.yz.sdk.crypto.enmu.SymmetricCryptoAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.util.Base64;

public class SymmetricCrypto {
    private SymmetricCrypto(){}

    public static String encrypt(String data, String key,SymmetricCryptoAlgorithm algorithm) {
        byte[] encryptedData;
        try {
            Cipher cipher = createCipher(algorithm);
            byte[] decodeKey = Base64.getDecoder().decode(key);
            SecretKeySpec secretKey = new SecretKeySpec(decodeKey, algorithm.getType());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedData = cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new CryptoException(e);
        }
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, String key,SymmetricCryptoAlgorithm algorithm) {
        byte[] decryptedData;
        try {
            Cipher cipher = createCipher(algorithm);
            byte[] decodeKey = Base64.getDecoder().decode(key);
            SecretKeySpec secretKey = new SecretKeySpec(decodeKey, algorithm.getType());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        } catch (Exception e) {
            throw new CryptoException(e);
        }
        return new String(decryptedData);
    }



    private static Cipher createCipher(SymmetricCryptoAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();

        try {
            return null == provider ? Cipher.getInstance(algorithm.getType()) : Cipher.getInstance(algorithm.getType(), provider);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

}
