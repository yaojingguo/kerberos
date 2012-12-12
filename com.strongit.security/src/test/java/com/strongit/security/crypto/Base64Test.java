package com.strongit.security.crypto;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import static org.junit.Assert.*;
import org.junit.Test;

@SuppressWarnings("all")
public class Base64Test {
  
  @Test
  public void testBase64() {
    Base64 base64 = new Base64();
    
    byte input[] = {'a'};
    String encoded = base64.encodeAsString(input);
    assertEquals("YQ==", encoded);
    
    byte output[] = base64.encode(input);
    assertArrayEquals(new byte[]{0x59, 0x51, 0x3d, 0x3d}, output); 
    
    byte decoded[] = base64.decode(output);
    assertArrayEquals(input, decoded);
  }
}
