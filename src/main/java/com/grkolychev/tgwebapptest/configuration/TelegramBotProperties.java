package com.grkolychev.tgwebapptest.configuration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotProperties {

  @NotBlank
  private String token;

}
