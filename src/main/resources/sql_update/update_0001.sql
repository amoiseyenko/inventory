ALTER TABLE tbl_res_flat
    ALTER COLUMN subscription_id TYPE bigint
    using (subscription_id::integer);