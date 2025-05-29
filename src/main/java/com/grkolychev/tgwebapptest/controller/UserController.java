package com.grkolychev.tgwebapptest.controller;

import com.grkolychev.tgwebapptest.model.TelegramUser;
import com.grkolychev.tgwebapptest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private static final String USER_ATTRIBUTE = "user";
  private static final String USER_INFO_TEMPLATE = "user-info";

  private final UserService userService;

  @PostMapping("/auth")
  public String authenticate(@RequestBody String payload, Model model) {
    log.info("Получены данные от Telegram WebApp: {}", payload);
    TelegramUser user  = userService.saveUser(payload);
    model.addAttribute(USER_ATTRIBUTE, user);
    return USER_INFO_TEMPLATE;
  }

}
