-- View: view_customersimresourcesflat

-- DROP VIEW view_customersimresourcesflat;

CREATE OR REPLACE VIEW view_customersimresourcesflat AS 
    SELECT 
	tbl_customer.id as id,
	tbl_customer_details.dob,
	tbl_customer_details.email,
	tbl_customer_details.first_name,
	tbl_customer_details.last_name,
	tbl_customer_details.gender,
	tbl_resource_configuration.id as rcid,
	tbl_resource_configuration.subscription_id,
	tbl_msisdn.msisdn,
	tbl_imsi.imsi,
	tbl_iccid.iccid,
	tbl_iccid.pin1,
	tbl_iccid.pin2,
	tbl_iccid.puk1,
	tbl_iccid.puk2,
	tbl_iccid.rc_state_id AS iccid_state,
	tbl_imsi.rc_state_id AS imsi_state,
	tbl_msisdn.rc_state_id AS msisdn_state
   FROM tbl_customer,
	tbl_customer_details,
	tbl_subscription,
	tbl_resource_configuration,
	tbl_iccid,
	tbl_imsi,
	tbl_msisdn
  WHERE tbl_customer.id = tbl_customer_details.customer_id
	AND tbl_customer.subscription_id = tbl_subscription.id
	AND tbl_subscription.id = tbl_resource_configuration.subscription_id
	AND tbl_iccid.rc_id = tbl_resource_configuration.id 
	AND tbl_imsi.rc_id = tbl_resource_configuration.id 
	AND tbl_msisdn.rc_id = tbl_resource_configuration.id;

ALTER TABLE view_customersimresourcesflat
  OWNER TO aspiderngi;
