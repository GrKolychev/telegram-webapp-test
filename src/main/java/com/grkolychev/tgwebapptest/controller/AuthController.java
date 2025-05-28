package com.grkolychev.tgwebapptest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @PostMapping
  public ResponseEntity<String> authenticate(@RequestBody String payload) {
    log.info("Получены данные от Telegram WebApp: {}", payload);
    return ResponseEntity.ok("ok");
  }

}
