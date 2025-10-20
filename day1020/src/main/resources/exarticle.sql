USE exArticle;
CREATE TABLE article
(
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    title   VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL,
    PRIMarticleARY KEY (id)
);