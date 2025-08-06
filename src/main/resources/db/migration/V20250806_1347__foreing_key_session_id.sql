ALTER TABLE user_has_answer DROP CONSTRAINT fk_test;
ALTER TABLE user_has_answer DROP COLUMN test_id;
ALTER TABLE user_has_answer ADD COLUMN session_id BIGINT;
ALTER TABLE user_has_answer ADD CONSTRAINT fk_user_has_answer_session FOREIGN KEY (session_id) REFERENCES test_session(id);