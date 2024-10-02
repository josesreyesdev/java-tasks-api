CREATE TABLE IF NOT EXISTS tasks(
    id CHAR(36) NOT NULL,
    title VARCHAR(150) NOT NULL,
    description VARCHAR(300) NOT NULL,
    expiration_date DATETIME NOT NULL,
    task_status VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)
);