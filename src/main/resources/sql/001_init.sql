BEGIN  TRANSACTION;

  DROP TABLE IF EXISTS "pins" CASCADE;
  DROP SEQUENCE IF EXISTS "pins_seq" CASCADE;

  CREATE SEQUENCE "pins_seq" START 1;
  CREATE TABLE "pins" (
    "id" BIGINT PRIMARY KEY DEFAULT "nextval"('"pins_seq"'),
    "email" VARCHAR(100)  NOT NULL,
    "api_token" VARCHAR(100) NOT NULL,
    "operation_id" BIGINT NOT NULL,
    "code" INT NOT NULL,
    "timestamp" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
  );

END TRANSACTION;