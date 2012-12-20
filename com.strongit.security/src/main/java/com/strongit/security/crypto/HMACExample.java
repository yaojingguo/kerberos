package com.strongit.security.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

public class HMACExample {
  public static void main(String[] args) throws Exception {
    String text = "qnscAdgRlkIhAUPY44oiexBKtQbGY0orf7OV1I50";
    SecretKeySpec keySpec = new SecretKeySpec(text.getBytes(), "HmacSHA1");
    Mac mac = Mac.getInstance("HmacSHA1");
    mac.init(keySpec);
    byte[] result = mac.doFinal("foo".getBytes());

    BASE64Encoder encoder = new BASE64Encoder();
    System.out.println(encoder.encode(result));
  }

}
