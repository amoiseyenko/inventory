ALTER TABLE dwh.fact_usage DROP CONSTRAINT fk_usage_key;



DROP TABLE dwh.dim_usage;

DROP SEQUENCE dwh.dim_usage_id_seq;