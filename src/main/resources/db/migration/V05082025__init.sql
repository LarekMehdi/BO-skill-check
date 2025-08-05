CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    pseudo VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE tag (
    id BIGSERIAL PRIMARY KEY,
    label VARCHAR(255) NOT NULL
);

CREATE TABLE test (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_by BIGINT NOT NULL
);

CREATE TABLE question (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    is_multiple_answer BOOLEAN NOT NULL,
    success_rate DOUBLE PRECISION NOT NULL,
    done_count INTEGER NOT NULL,
    correct_count INTEGER NOT NULL,
    time_limit INTEGER NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    created_by BIGINT NOT NULL
);

CREATE TABLE answer (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id)
        REFERENCES question (id)
        ON DELETE CASCADE
);

CREATE TABLE stat (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_tests_completed INTEGER NOT NULL,
    total_questions_answered INTEGER NOT NULL,
    success_rate DOUBLE PRECISION NOT NULL,
    time_passed INTEGER NOT NULL,
    CONSTRAINT fk_stat_user FOREIGN KEY (user_id) REFERENCES app_user(id)
);

CREATE TABLE question_has_tag (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT fk_question_has_tag_question FOREIGN KEY (question_id) REFERENCES question(id),
    CONSTRAINT fk_question_has_tag_tag FOREIGN KEY (tag_id) REFERENCES tag(id)
);

CREATE TABLE test_has_question (
    id BIGSERIAL PRIMARY KEY,
    test_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    CONSTRAINT fk_test_has_question_test FOREIGN KEY (test_id) REFERENCES test(id),
    CONSTRAINT fk_test_has_question_question FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE user_has_answer (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    test_id BIGINT,
    question_id BIGINT NOT NULL,
    answer_id BIGINT NOT NULL,
    is_correct BOOLEAN NOT NULL,
    response_time INTEGER,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_test FOREIGN KEY (test_id) REFERENCES test(id),
    CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES question(id),
    CONSTRAINT fk_answer FOREIGN KEY (answer_id) REFERENCES answer(id)
);