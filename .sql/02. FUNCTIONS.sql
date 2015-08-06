-- Function: dwh.get_usage()

-- DROP FUNCTION dwh.get_usage(subscription_id bigint, pointer_id bigint, count integer);

CREATE OR REPLACE FUNCTION dwh.get_usage(subscription_id IN bigint, pointer_id IN bigint DEFAULT 0, count IN integer DEFAULT 3)
  RETURNS refcursor AS
$BODY$
	DECLARE
		ref refcursor := 'usage_cursor';

	BEGIN
		IF pointer_id = 0 THEN
			pointer_id := to_char(current_timestamp::timestamp, 'YYYYMMDDHH24MISS');
		END IF;

		OPEN ref FOR
		
			SELECT 	id, date_value as date, usage_type_key as usage_type, usage_value, 
				usage_cost, 
				case 	
					when (usage_type_key = 2 OR usage_type_key = 3) AND extra::json->>'bnumber' = 'true' THEN SUBSTRING(extra::json->>'dialednumber'::text FROM 0 FOR 7) || '*****'
					when (usage_type_key = 2 OR usage_type_key = 3) AND extra::json->>'bnumber' != 'true' THEN extra::json->>'dialednumber'::text
					else ''
				end as extra,		
				to_char(date_value, 'YYYYMMDDHH24MISS') as position
			FROM 	dwh.fact_usage
			WHERE 	subscription_key = subscription_id
				and date_value < to_timestamp(pointer_id::text, 'YYYYMMDDHH24MISS')::timestamp without time zone
			ORDER BY date_value DESC
			LIMIT 	count;
		
		RETURN (ref);
        END;

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

ALTER FUNCTION dwh.get_usage(subscription_id bigint, pointer_id bigint, count integer)
  OWNER TO aspiderngi;