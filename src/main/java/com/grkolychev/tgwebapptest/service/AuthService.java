package com.grkolychev.tgwebapptest.service;

import com.grkolychev.tgwebapptest.configuration.TelegramBotProperties;
import com.grkolychev.tgwebapptest.util.SignatureUtils;
import jakarta.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

  private static final String CONST_KEY = "WebAppData";
  private static final String HASH_PARAM_NAME = "hash";
  private static final String EQUAL_SIGN = "=";
  private static final String LINE_BREAK = "\n";

  private final TelegramBotProperties telegramBotProperties;

  public boolean isInitDataValid(Map<String, String> initDataParams) {
    String hash = initDataParams.get(HASH_PARAM_NAME);

    String hashPayload = prepareHashPayload(initDataParams);
    byte[] secretKey;
    String calculatedHash;
    try {
      secretKey = SignatureUtils.getHmacSha256Hash(telegramBotProperties.getToken().getBytes(StandardCharsets.UTF_8),
          CONST_KEY.getBytes(StandardCharsets.UTF_8));
      calculatedHash = SignatureUtils.getHmacSha256HashInHexStr(hashPayload.getBytes(StandardCharsets.UTF_8), secretKey);
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      log.warn("Error while calculating hash", e);
      throw new IllegalArgumentException(e);
    }

    return calculatedHash.equals(hash);
  }

  @Nonnull
  private String prepareHashPayload(@Nonnull Map<String, String> params) {
    return params.entrySet().stream()
        .sorted(Entry.comparingByKey())
        .filter(entry -> !entry.getKey().equals(HASH_PARAM_NAME))
        .map(entry -> entry.getKey() + EQUAL_SIGN + entry.getValue())
        .collect(Collectors.joining(LINE_BREAK));
  }

}
