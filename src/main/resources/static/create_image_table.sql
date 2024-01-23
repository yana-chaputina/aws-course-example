create table image(
                      id               BIGINT          PRIMARY KEY AUTO_INCREMENT,
                      name             VARCHAR(20),
                      size             BIGINT,
                      file_extension   VARCHAR(40) ,
                      last_update      TIMESTAMP       DEFAULT NOW()
);