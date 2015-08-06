INSERT INTO TBL_SUBSCRIPTION_PROVIDER(name) VALUES ('simpel');
INSERT INTO TBL_SUBSCRIPTION_PROVIDER(name) VALUES ('simpel-pre');
INSERT INTO TBL_SUBSCRIPTION_PROVIDER(name) VALUES ('tele-2');

INSERT INTO TBL_SUBSCRIPTION_STATE(name) VALUES ('PREUSE');
INSERT INTO TBL_SUBSCRIPTION_STATE(name) VALUES ('ACTIVE');
INSERT INTO TBL_SUBSCRIPTION_STATE(name) VALUES ('TERMINATED');
INSERT INTO TBL_SUBSCRIPTION_STATE(name) VALUES ('DELETED');

INSERT INTO TBL_RC_STATE(name) VALUES ('PREACTIVE');
INSERT INTO TBL_RC_STATE(name) VALUES ('ACTIVE');
INSERT INTO TBL_RC_STATE(name) VALUES ('DELETED');
INSERT INTO TBL_RC_STATE(name) VALUES ('COOLDOWN');

INSERT INTO TBL_SIMSWAP_STATE(name) VALUES ('INIT');
INSERT INTO TBL_SIMSWAP_STATE(name) VALUES ('COMPLETED');
INSERT INTO TBL_SIMSWAP_STATE(name) VALUES ('CANCELED');
INSERT INTO TBL_SIMSWAP_STATE(name) VALUES ('ERROR');

INSERT INTO TBL_ROLE(name) VALUES ('USER');
INSERT INTO TBL_ROLE(name) VALUES ('ADMIN');

INSERT INTO TBL_CUSTOMER_STATE(name) VALUES('PREUSE');
INSERT INTO TBL_CUSTOMER_STATE(name) VALUES('ACTIVE');

INSERT INTO TBL_TOPUP_STATE(name) VALUES('INIT');
INSERT INTO TBL_TOPUP_STATE(name) VALUES('SUCCESS');
INSERT INTO TBL_TOPUP_STATE(name) VALUES('FAILED');

INSERT INTO TBL_TOPUP_TYPE(name) VALUES('NORMAL');
INSERT INTO TBL_TOPUP_TYPE(name) VALUES('PROMO');

CREATE OR REPLACE VIEW VIEW_SIMRESOURCESFLAT AS SELECT tbl_resource_configuration.id, tbl_resource_configuration.subscription_id, tbl_subscription.subscription_id as provider_sub_id, tbl_subscription.subscription_provider_id as provider_id, tbl_msisdn.msisdn, tbl_imsi.imsi, tbl_iccid.iccid, tbl_iccid.pin1, tbl_iccid.pin2, tbl_iccid.puk1, tbl_iccid.puk2, tbl_iccid.rc_state_id AS iccid_state, tbl_imsi.rc_state_id AS imsi_state, tbl_msisdn.rc_state_id AS msisdn_state FROM tbl_subscription right join tbl_resource_configuration on tbl_subscription.id = tbl_resource_configuration.subscription_id, tbl_iccid, tbl_imsi, tbl_msisdn WHERE tbl_iccid.rc_id = tbl_resource_configuration.id AND tbl_imsi.rc_id = tbl_resource_configuration.id AND tbl_msisdn.rc_id = tbl_resource_configuration.id;

CREATE OR REPLACE VIEW VIEW_CUSTOMERSIMRESOURCESFLAT AS SELECT tbl_customer.id as id, tbl_customer_details.dob, tbl_customer_details.email, tbl_customer_details.first_name, tbl_customer_details.last_name, tbl_customer_details.gender, tbl_resource_configuration.id as rcid, tbl_resource_configuration.subscription_id, tbl_msisdn.msisdn, tbl_imsi.imsi, tbl_iccid.iccid, tbl_iccid.pin1, tbl_iccid.pin2, tbl_iccid.puk1, tbl_iccid.puk2, tbl_iccid.rc_state_id AS iccid_state, tbl_imsi.rc_state_id AS imsi_state, tbl_msisdn.rc_state_id AS msisdn_state FROM tbl_customer, tbl_customer_details, tbl_subscription, tbl_resource_configuration, tbl_iccid, tbl_imsi, tbl_msisdn WHERE tbl_customer.id = tbl_customer_details.customer_id AND tbl_customer.subscription_id = tbl_subscription.id AND tbl_subscription.id = tbl_resource_configuration.subscription_id AND tbl_iccid.rc_id = tbl_resource_configuration.id AND tbl_imsi.rc_id = tbl_resource_configuration.id AND tbl_msisdn.rc_id = tbl_resource_configuration.id;