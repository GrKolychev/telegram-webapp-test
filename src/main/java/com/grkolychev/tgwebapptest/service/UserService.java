package com.grkolychev.tgwebapptest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grkolychev.tgwebapptest.exception.AuthException;
import com.grkolychev.tgwebapptest.exception.InvalidDataException;
import com.grkolychev.tgwebapptest.model.TelegramUser;
import com.grkolychev.tgwebapptest.util.TelegramWebAppUtils;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

  private static final String USER_PARAM = "user";

  private final JpaRepository<TelegramUser, Long> telegramUserRepository;
  private final AuthService authService;
  private final ObjectMapper objectMapper;

  public TelegramUser saveUser(String initDataPayload) {
    Map<String, String> initDataParams = TelegramWebAppUtils.parseInitDataPayload(initDataPayload);
    if (!authService.isInitDataValid(initDataParams)) {
      throw new AuthException("Authorization failed");
    }
    String telegramUserJsonStr = initDataParams.get(USER_PARAM);
    if (telegramUserJsonStr == null) {
      throw new InvalidDataException("User data is not present");
    }

    TelegramUser telegramUser;
    try {
      telegramUser = objectMapper.readValue(telegramUserJsonStr, TelegramUser.class);
    } catch (JsonProcessingException e) {
      throw new InvalidDataException("Unable to parse user data", e);
    }

    return saveTelegramUserIfNotExists(telegramUser);
  }

  public TelegramUser saveTelegramUserIfNotExists(TelegramUser user) {
    return telegramUserRepository.findById(user.getId())
        .orElseGet(() -> telegramUserRepository.save(user));
  }

}
