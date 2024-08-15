DROP TABLE IF EXISTS url_checks;
DROP TABLE IF EXISTS urls;

CREATE TABLE urls (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE url_checks (
    id SERIAL PRIMARY KEY,
    status_code INT,
    title VARCHAR(255),
    h1 VARCHAR(255),
    description TEXT,
    url_id INT REFERENCES urls(id),
    created_at TIMESTAMP
);