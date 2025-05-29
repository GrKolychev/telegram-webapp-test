package com.grkolychev.tgwebapptest.util;

import jakarta.annotation.Nonnull;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelegramWebAppUtils {

  private static final String AMPERSAND = "&";
  private static final String EQUAL_SIGN = "=";
  private static final String INIT_DATA = "initData";

  @Nonnull
  public static Map<String, String> parseInitDataPayload(@Nonnull String initDataPayload) {
    return Arrays.stream(URLDecoder.decode(initDataPayload, StandardCharsets.UTF_8).split(EQUAL_SIGN, 2))
        .map(s -> URLDecoder.decode(s, StandardCharsets.UTF_8))
        .filter(s -> !s.equals(INIT_DATA))
        .map(s -> URLDecoder.decode(s, StandardCharsets.UTF_8))
        .flatMap(s -> Arrays.stream(s.split(AMPERSAND)))
        .map(s -> s.split(EQUAL_SIGN))
        .filter(a -> a.length == 2)
        .collect(Collectors.toMap(a -> a[0], a -> URLDecoder.decode(a[1], StandardCharsets.UTF_8)));
  }

}
