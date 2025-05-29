CREATE TABLE telegram_user
(
    id            BIGINT PRIMARY KEY,
    user_name     VARCHAR(255),
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255),
    language_code VARCHAR(10),
    is_premium    BOOLEAN
);