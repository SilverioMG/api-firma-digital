CREATE TABLE IF NOT EXISTS user_key (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    encoded_private_key VARCHAR(400) NOT NULL,
    encoded_public_key VARCHAR(400) NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_user_key_name ON user_key(name);