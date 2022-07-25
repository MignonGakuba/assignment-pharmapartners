CREATE TABLE currency (
             id   INTEGER      NOT NULL AUTO_INCREMENT,
             name VARCHAR(128) NOT NULL,
             ticker VARCHAR(128) NOT NULL,
             number_of_coins VARCHAR(MAX)  NOT NULL,
             market_cap VARCHAR(MAX)  NOT NULL,
             PRIMARY KEY (id)
);