package com.gs.crms.common.utils;

import com.springcryptoutils.core.cipher.Mode;
import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Base64;

/**
 *  Created by  on 2018/1/17
 *  .
 */
public class CipherUtil {
    private static Logger logger = LoggerFactory.getLogger(CipherUtil.class);
    /**
     * <crypt:b64SymmetricCipherer cipherAlgorithm="DESede/CBC/PKCS5Padding"
	 * id="encrypter" keyAlgorithm="DESede" mode="ENCRYPT"/>
	 * <crypt:b64SymmetricCipherer cipherAlgorithm="DESede/CBC/PKCS5Padding"
	 * id="decrypter" keyAlgorithm="DESede" mode="DECRYPT"/>
	 */
    /**
     * Initialization Vector
     */
    private static final String IV = "AQIDBAUGAQI=";

    /**
     * 方法构造
     */
    private CipherUtil() {
    }

    /**
     * Get certificate of public key
     *
     * @return
     */
    public static PublicKey getPublicKey() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("x.509");
            InputStream in = CipherUtil.class.getResourceAsStream("/sec007.cer");
            java.security.cert.Certificate cert = cf.generateCertificate(in);
            in.close();
            return cert.getPublicKey();
        } catch (CertificateException | IOException e) {
            logger.error("Remote Server Exception:getPublicKey", e);
            return null;
        }
    }

    /**
     * Encrypt using RSA private key
     *
     * @param plainText
     * @param key
     * @return
     */
    public static String encryptByRSA(String plainText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            logger.error("Remote Server Exception:encryptByRSA", e);
            return null;
        }

    }

    /**
     * Decrypt using RSA private key
     *
     * @param encryptedText
     * @param key
     * @return
     */
    public static String decryptByRSA(String encryptedText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
        } catch (Exception e) {
            logger.error("Remote Server Exception:decryptByRSA", e);
            return null;
        }

    }

    /**
     * getRandomSecretKey
     *
     * @return
     */
    public static String getRandomSecretKey() {
        try {
            String symPass = RandomStringUtils.randomAlphanumeric(32);
            return symPass + System.currentTimeMillis() / 1000;
        } catch (Exception e) {
            logger.error("Remote Server Exception:getRandomSecretKey", e);
            return null;
        }

    }

    /**
     * Encrypy content by cipher
     *
     * @param cipher
     * @param content
     * @return
     */
    public static String encryptByCipher(String cipher, String content) {
        try {
            Base64EncodedCiphererImpl encrypter = new Base64EncodedCiphererImpl();
            encrypter.setMode(Mode.ENCRYPT);
            return encrypter.encrypt(cipher, IV, content);
        } catch (Exception e) {
            logger.error("Remote Server Exception:encryptByCipher", e);
            return null;
        }

    }

    /**
     * Decry content by cipher
     *
     * @param cipher
     * @param content
     * @return
     */
    public static String decryptByCipher(String cipher, String content) {
        try {
            Base64EncodedCiphererImpl decrypter = new Base64EncodedCiphererImpl();
            decrypter.setMode(Mode.DECRYPT);
            return decrypter.encrypt(cipher, IV, content);
        } catch (Exception e) {
            logger.error("Remote Server Exception:decryptByCipher", e);
            return null;
        }
    }
}
