package cn.yz.sdk.crypto.digester;

import cn.yz.sdk.common.exception.CryptoException;
import cn.yz.sdk.common.util.ExceptionUtil;
import cn.yz.sdk.crypto.enmu.GlobalBouncyCastleProvider;
import cn.yz.sdk.crypto.enmu.SymmetricCryptoAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Base64;

public class SymmetricCrypto {
    private static final Logger log = LoggerFactory.getLogger(SymmetricCrypto.class);
    private SymmetricCrypto(){}

    public static String encrypt(String data, String key,SymmetricCryptoAlgorithm algorithm) {
        byte[] encryptedData = new byte[0];
        try {
            Cipher cipher = createCipher(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm.getType());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedData = cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
        }
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, String key,SymmetricCryptoAlgorithm algorithm) {
        byte[] decryptedData = new byte[0];
        try {
            Cipher cipher = createCipher(algorithm);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm.getType());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
        }
        return new String(decryptedData);
    }

    public static String generateKey(SymmetricCryptoAlgorithm algorithm,int keySize) {
        KeyGenerator keyGenerator = createSymmetricCrypto(algorithm);
        keyGenerator.init(keySize);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private static KeyGenerator createSymmetricCrypto(SymmetricCryptoAlgorithm algorithm) {
        Provider provider = GlobalBouncyCastleProvider.INSTANCE.getProvider();
        try {
            return null == provider ? KeyGenerator.getInstance(algorithm.getType()) : KeyGenerator.getInstance(algorithm.getType(), provider);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptoException(e);
        }
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
