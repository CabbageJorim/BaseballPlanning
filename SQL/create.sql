    EMAIL       VARCHAR(256)                NOT NULL,
    PASSWORD    VARCHAR(20)                 NOT NULL,
    NAME        VARCHAR(21)                 NOT NULL,
    BIRTH       VARCHAR(10)                 NOT NULL,
    TEL         VARCHAR(13)                 NOT NULL,
    CREATE_DATE DATE        DEFAULT CURRENT_DATE NOT NULL,

    SELECT * FROM USER_TEAM
    WHERE BACKNUMBER = '10' AND