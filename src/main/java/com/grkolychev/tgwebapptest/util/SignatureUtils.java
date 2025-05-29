package com.grkolychev.tgwebapptest.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Hex;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignatureUtils {

  private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

  public static String getHmacSha256HashInHexStr(byte[] src, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
    byte[] sha256Byte = getHmacSha256Hash(src, key);
    return new String(Hex.encodeHex(sha256Byte));
  }

  public static byte[] getHmacSha256Hash(byte[] src, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA256_ALGORITHM);
    Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
    mac.init(signingKey);
    return mac.doFinal(src);
  }

}
