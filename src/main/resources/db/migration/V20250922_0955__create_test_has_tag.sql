CREATE TABLE test_has_tag (
    id BIGSERIAL PRIMARY KEY,
    test_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT fk_test_has_tag_test FOREIGN KEY (test_id) REFERENCES test(id),
    CONSTRAINT fk_test_has_tag_tag FOREIGN KEY (tag_id) REFERENCES tag(id)
);