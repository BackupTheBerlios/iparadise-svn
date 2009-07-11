package com.pdpsoft.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.*;
import java.io.Serializable;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jul 6, 2009
 * Time: 4:06:48 PM
 */
public final class SecurityAlgorithm {
    private final static Log LOG = LogFactory.getLog(SecurityAlgorithm.class);
    // the key for signing
    private static SecretKey secretKey;
    // Triple DES Encryption (DES-EDE). The Digital Encryption Standard as described in FIPS PUB 46-2. 
    static final String ALGORITHM_NAME = "DESede";

    static {
        try {
            // generate the secret key
            secretKey = KeyGenerator.getInstance(ALGORITHM_NAME).generateKey();
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mode it can be Cipher.ENCRYPT_MODE, Cipher.DECRYPT_MODE,
     *                       Cipher.WRAP_MODE, and Cipher.UNWRAP_MODE
     * @return Cipher for encrypting an object
     */
    public static Cipher getCipher(final int mode) {
        try {
            Cipher instance = Cipher.getInstance(ALGORITHM_NAME);
            instance.init(mode, secretKey);
            return instance;
        } catch (InvalidKeyException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * this method handles the sealing the object
     * @param unsealed the object that shoul be sealed
     * @return an instance of SealedObject
     */
    public static SealedObject seal(Serializable unsealed) {
        try {
            return new SealedObject(unsealed, getCipher(Cipher.ENCRYPT_MODE));
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * this method handle to unseal the object
     * @param sealed the sealed one
     * @return unsealed the object and returns the main value
     */
    public static Object unseal(SealedObject sealed) {
        try {
            return sealed.getObject(getCipher(Cipher.DECRYPT_MODE));
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * handles the encryption of the password or any other string
     * @param object the string value that should be encrypted
     * @return the encrypted of input parameter
     */
    public static String encrypt(String object) {
        // Initialize a TEA cipher using the first 16 bytes of a passphrase
        TEA tea = new TEA("And is there honey still for tea?".getBytes());
        // Then use it to encrypt clear text...
        return new String(tea.encrypt(object.getBytes()));
    }

    public static void main(String args[]) throws Exception {
        System.out.println("encrypt(\"hi\") = " + encrypt("hi"));
        System.out.println("encrypt(\"hii\") = " + encrypt("hii"));
        System.out.println("encrypt(\"hiii\") = " + encrypt("hiii"));
        System.out.println("encrypt(\"hiii\") = " + encrypt("hiii"));
    }
}
