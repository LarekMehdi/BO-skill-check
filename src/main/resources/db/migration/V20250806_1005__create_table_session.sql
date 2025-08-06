CREATE TABLE session (
    id BIGSERIAL PRIMARY KEY,
    test_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_session_test FOREIGN KEY (test_id) REFERENCES test(id),
    CONSTRAINT fk_session_user FOREIGN KEY (user_id) REFERENCES app_user(id)
);
    
