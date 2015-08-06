DO $$
BEGIN
	IF NOT EXISTS (SELECT 'Email_Unique' 
			   FROM information_schema.constraint_column_usage 
			   WHERE table_name = 'tbl_customer_details'  AND constraint_name = 'email_unique') THEN
		ALTER TABLE tbl_customer_details 
		ADD CONSTRAINT Email_Unique
		UNIQUE (email);
	    END IF;
END
$$