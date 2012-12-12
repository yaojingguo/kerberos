package com.strongit.security.crypto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESExample {

  private static Base64 base64 = new Base64();

  public static String base64Encode(byte raw[]) {
    return base64.encodeAsString(raw);
  }

  public static Cipher getAESCBCEncryptor(byte[] keyBytes,
                                          byte[] IVBytes,
                                          String padding) throws Exception {
    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/" + padding);
    IvParameterSpec ivSpec = new IvParameterSpec(IVBytes);
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    return cipher;
  }

  public static Cipher getCipher(String mode,
                                 byte[] key,
                                 byte[] iv,
                                 String padding,
                                 int opmode) {
    try {
      SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
      Cipher cipher = Cipher.getInstance("AES/" + mode + "/" + padding);
      if (iv != null) {
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(opmode, keySpec, ivSpec);
      } else {
        cipher.init(opmode, keySpec);
      }
      return cipher;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encrypt(Cipher cipher, byte[] dataBytes)
      throws Exception {
    ByteArrayInputStream bIn = new ByteArrayInputStream(dataBytes);
    CipherInputStream cIn = new CipherInputStream(bIn, cipher);
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    int ch;
    while ((ch = cIn.read()) >= 0) {
      bOut.write(ch);
    }
    return bOut.toByteArray();
  }

  public static byte[] decrypt(Cipher cipher, byte[] dataBytes)
      throws Exception {
    ByteArrayOutputStream bOut = new ByteArrayOutputStream();
    CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
    cOut.write(dataBytes);
    cOut.close();
    return bOut.toByteArray();
  }

  public static byte[] encryptCBC(byte[] key,
                                  byte[] iv,
                                  String padding,
                                  byte[] raw) throws Exception {
    Cipher cipher = getCipher("CBC", key, iv, padding, Cipher.ENCRYPT_MODE);
    return encrypt(cipher, raw);
  }

  public static byte[] decryptCBC(byte[] key,
                                  byte[] iv,
                                  String padding,
                                  byte[] encrypted) throws Exception {
    Cipher decipher = getCipher("CBC", key, iv, padding, Cipher.DECRYPT_MODE);
    return decrypt(decipher, encrypted);
  }

  public static byte[] encryptECB(byte[] key, String padding, byte[] raw)
      throws Exception {
    Cipher cipher = getCipher("ECB", key, null, padding, Cipher.ENCRYPT_MODE);
    return encrypt(cipher, raw);
  }

  public static byte[] decryptECB(byte[] key, String padding, byte[] encrypted)
      throws Exception {
    Cipher cipher = getCipher("ECB", key, null, padding, Cipher.DECRYPT_MODE);
    return decrypt(cipher, encrypted);
  }

  public static void main(String[] args) throws Exception {
    String text = "This is a demo message from Java!";
    byte[] raw = text.getBytes();

    byte[] key = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

    // Initialization Vector - usually a random data, stored along with the
    // shared secret, or transmitted along with a message. Not all the ciphers
    // require IV - we use IV in this particular sample
    byte[] iv = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
        0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
    String padding = "PKCS5Padding";

    System.out.println("Key (base64): " + base64Encode(key));
    System.out.println("IV (base64): " + base64Encode(iv));

    /**
     * openssl command to do ECB encryption:
     * 
     * <pre>
     * echo -n 'This is a demo message from Java!' \
     * | openssl enc -aes-128-ecb \
     * -K 000102030405060708090a0b0c0d0e0f  -out message -a
     * </pre>
     */
    byte[] cbcEncrypted = encryptCBC(key, iv, padding, raw);
    System.out.println("aes-128-cbc encrypted (base64): "
        + base64Encode(cbcEncrypted));
    byte[] cbcDecrypted = decryptCBC(key, iv, padding, cbcEncrypted);
    System.out.println("decrypted message : " + new String(cbcDecrypted));

    /**
     * openssl command to do CBC encryption:
     * 
     * <pre>
     * echo -n 'This is a demo message from Java!' \
     * | openssl enc -aes-128-cbc \
     * -iv 000102030405060708090a0b0c0d0e0f \
     * -K 000102030405060708090a0b0c0d0e0f  -out message -a
     * </pre>
     */
    byte[] ecbEncrypted = encryptECB(key, padding, raw);
    System.out.println("aes-128-ecb (base64): " + base64Encode(ecbEncrypted));
    byte[] ecbDecrypted = decryptECB(key, padding, ecbEncrypted);
    System.out.println("decrypted message : " + new String(ecbDecrypted));
  }

}
