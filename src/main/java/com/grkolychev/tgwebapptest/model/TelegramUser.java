package com.grkolychev.tgwebapptest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class TelegramUser implements Serializable {

  @Id
  @JsonProperty("id")
  private Long id;

  @JsonProperty("username")
  private String userName;

  @JsonProperty("first_name")
  private String firstName;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("language_code")
  private String languageCode;

  @JsonProperty("is_premium")
  private Boolean isPremium = false;

}
