/*

 DROP TABLE dwh.dim_subscription;
 DROP TABLE dwh.dim_usage;
 DROP TABLE dwh.dim_usage_type;
 DROP TABLE dwh.fact_usage;

 DROP SEQUENCE dwh.dim_usage_id_seq;
 DROP SEQUENCE dwh.fact_usage_id_seq;

 DROP INDEX dwh.fki_subscription_id;
 DROP INDEX dwh.fki_usage_type_key;
 DROP INDEX dwh.fki_usage_key;

 DROP SCHEMA dwh CASCADE;
*/

-- Schema: dwh

CREATE SCHEMA dwh
  AUTHORIZATION aspiderngi;

-- Sequence: dwh.dim_usage_id_seq

CREATE SEQUENCE dwh.dim_usage_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE dwh.dim_usage_id_seq
  OWNER TO aspiderngi;

-- Sequence: dwh.fact_usage_id_seq

CREATE SEQUENCE dwh.fact_usage_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE dwh.fact_usage_id_seq
  OWNER TO aspiderngi;
  
-- Table: dwh.dim_subscription

CREATE TABLE dwh.dim_subscription
(
  id bigint NOT NULL,
  CONSTRAINT dim_subscription_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dwh.dim_subscription
  OWNER TO aspiderngi;

  
-- Table: dwh.dim_usage

CREATE TABLE dwh.dim_usage
(
  id bigint NOT NULL DEFAULT nextval('dwh.dim_usage_id_seq'::regclass),
  data json,
  CONSTRAINT dim_usage_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dwh.dim_usage
  OWNER TO aspiderngi;

  
-- Table: dwh.dim_usage_type

CREATE TABLE dwh.dim_usage_type
(
  id bigint NOT NULL,
  usage_type_name character varying(50),
  CONSTRAINT dim_usage_type_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dwh.dim_usage_type
  OWNER TO aspiderngi;

-- Table: dwh.fact_usage

CREATE TABLE dwh.fact_usage
(
  id bigint NOT NULL DEFAULT nextval('dwh.fact_usage_id_seq'::regclass),
  subscription_key bigint,
  usage_type_key bigint,
  usage_key bigint,
  date_value timestamp without time zone,
  usage_cost integer NOT NULL,
  usage_value bigint,
  extra json,
  CONSTRAINT fact_usage_pkey PRIMARY KEY (id),
  CONSTRAINT fk_subscription_key FOREIGN KEY (subscription_key)
      REFERENCES dwh.dim_subscription (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usage_key FOREIGN KEY (usage_key)
      REFERENCES dwh.dim_usage (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usage_type_key FOREIGN KEY (usage_type_key)
      REFERENCES dwh.dim_usage_type (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE dwh.fact_usage
  OWNER TO aspiderngi;

-- Index: dwh.fki_subscription_id

CREATE INDEX fki_subscription_id
  ON dwh.fact_usage
  USING btree
  (subscription_key);

-- Index: dwh.fki_usage_key

CREATE INDEX fki_usage_key
  ON dwh.fact_usage
  USING btree
  (usage_key);

-- Index: dwh.fki_usage_type_key

CREATE INDEX fki_usage_type_key
  ON dwh.fact_usage
  USING btree
  (usage_type_key);
