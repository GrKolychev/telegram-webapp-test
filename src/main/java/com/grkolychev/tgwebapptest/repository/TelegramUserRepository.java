package com.grkolychev.tgwebapptest.repository;

import com.grkolychev.tgwebapptest.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {

}
