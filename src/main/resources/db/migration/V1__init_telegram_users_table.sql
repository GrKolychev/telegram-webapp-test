CREATE TABLE telegram_users
(
    id            BIGINT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255),
    username      VARCHAR(255),
    language_code VARCHAR(10),
    is_premium    BOOLEAN,
    auth_date     BIGINT       NOT NULL
);