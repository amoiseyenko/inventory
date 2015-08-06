
    create table REVINFO (
        REV int4 not null,
        REVTSTMP int8,
        primary key (REV)
    );

    create table TBL_AGENT (
        ID  bigserial not null,
        FIRST_NAME varchar(255),
        LAST_NAME varchar(255),
        LOGIN varchar(255),
        PASSWORD varchar(255),
        ROLE_ID int8,
        primary key (ID)
    );

    create table TBL_CATEGORIES (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_CUSTOMER (
        ID  bigserial not null,
        CONFIRMATION_TOKEN varchar(255),
        ROLE_ID int4,
        ADDRESS_ID int8,
        DETAILS_ID int8,
        STATE_ID int8,
        subscription_ID int8,
        primary key (ID)
    );

    create table TBL_CUSTOMER_ADDRESS (
        ID  bigserial not null,
        CITY varchar(255),
        ADDRESS varchar(255),
        HOUSE_NUMBER varchar(255),
        ZIP_CODE varchar(255),
        CUSTOMER_ID int8,
        primary key (ID)
    );

    create table TBL_CUSTOMER_AUD (
        ID int8 not null,
        REV int4 not null,
        REVTYPE int2,
        STATE_ID int8,
        primary key (ID, REV)
    );

    create table TBL_CUSTOMER_DETAILS (
        ID  bigserial not null,
        DOB date,
        EMAIL varchar(255),
        FIRST_NAME varchar(255),
        GENDER varchar(255),
        LAST_NAME varchar(255),
        PASSWORD varchar(255),
        CUSTOMER_ID int8 not null,
        primary key (ID)
    );

    create table TBL_CUSTOMER_NOTE (
        ID  bigserial not null,
        DATE timestamp,
        NOTE varchar(255),
        AGENT_ID int8,
        category_ID int8,
        CUSTOMER_ID int8,
        primary key (ID)
    );

    create table TBL_CUSTOMER_SETTINGS (
        ID  bigserial not null,
        NAME varchar(255),
        VALUE varchar(255),
        CUSTOMER_ID int8,
        primary key (ID)
    );

    create table TBL_CUSTOMER_STATE (
        ID  bigserial not null,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_CUSTOMER_STATE_AUD (
        ID int8 not null,
        REV int4 not null,
        REVTYPE int2,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        primary key (ID, REV)
    );

    create table TBL_ICCID (
        ID  bigserial not null,
        ICCID varchar(22) not null,
        PIN1 varchar(255),
        PIN2 varchar(255),
        PUK1 varchar(255),
        PUK2 varchar(255),
        RC_STATE_ID int8 not null,
        RC_ID int8,
        primary key (ID)
    );

    create table TBL_IMSI (
        ID  bigserial not null,
        IMSI varchar(15) not null,
        RC_STATE_ID int8 not null,
        RC_ID int8,
        primary key (ID)
    );

    create table TBL_MSISDN (
        ID  bigserial not null,
        MSISDN int8 not null,
        RC_STATE_ID int8 not null,
        RC_ID int8,
        primary key (ID)
    );

    create table TBL_NP_REQUEST (
        ID  bigserial not null,
        ICCID varchar(22) not null,
        ACCOUNT_ID varchar(50) not null,
        DONOR_NAME varchar(30) not null,
        INPORT_MSISDN int8 not null,
        NOTES varchar(255) not null,
        WISH_DATE date,
        SUBSCRIPTION_ID int8 not null,
        primary key (ID)
    );

    create table TBL_PASSWORD_MGMT (
        ID  bigserial not null,
        ACTIVATION_DATE timestamp,
        REQUESTED_DATE timestamp,
        TOKEN varchar(255),
        CUSTOMER_ID int8,
        primary key (ID)
    );

    create table TBL_RC_STATE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_RC_STATE_AUD (
        ID int8 not null,
        REV int4 not null,
        REVTYPE int2,
        NAME varchar(255),
        primary key (ID, REV)
    );

    create table TBL_RESOURCE_CONFIGURATION (
        ID  bigserial not null,
        SUBSCRIPTION_ID int8,
        primary key (ID)
    );

    create table TBL_ROLE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_SIMSWAP (
        ID  bigserial not null,
        REQUEST_ICCID varchar(255),
        REQUEST_DATE date,
        RC_ID int8 not null,
        SUBSCRIPTION_STATE_ID int8 not null,
        SUBSCRIPTION_ID int8,
        primary key (ID)
    );

    create table TBL_SIMSWAP_AUD (
        ID int8 not null,
        REV int4 not null,
        REVTYPE int2,
        REQUEST_ICCID varchar(255),
        REQUEST_DATE date,
        SUBSCRIPTION_STATE_ID int8,
        primary key (ID, REV)
    );

    create table TBL_SIMSWAP_STATE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_SIMSWAP_STATE_AUD (
        ID int8 not null,
        REV int4 not null,
        REVTYPE int2,
        NAME varchar(255),
        primary key (ID, REV)
    );

    create table TBL_SUBSCRIPTION (
        ID  bigserial not null,
        SUBSCRIPTION_ID varchar(255),
        SUSPEND_STATE integer,
        SUBSCRIPTION_PROVIDER_ID int8 not null,
        SUBSCRIPTION_STATE_ID int8 not null,
        primary key (ID)
    );

    create table TBL_SUBSCRIPTION_PROVIDER (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_SUBSCRIPTION_STATE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_TOPUP (
        ID  bigserial not null,
        DATE timestamp,
        ISSUER_ID int8,
        PAYMENT_DESRIPTION varchar(255),
        PAYMENT_ID int8,
        PAYMENT_REF varchar(255),
        RETURN_URL varchar(255),
        TOPUP_AMOUNT float8,
        TRANSACTION_ID varchar(255),
        CUSTOMER_ID int8,
        TOPUP_STATE_ID int8 not null,
        TOPUP_TYPE_ID int8 not null,
        primary key (ID)
    );

    create table TBL_TOPUP_STATE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    create table TBL_TOPUP_TYPE (
        ID  bigserial not null,
        NAME varchar(255),
        primary key (ID)
    );

    alter table TBL_AGENT 
        add constraint UK_6x5ib09pxxteqpkmps0r4s501  unique (LOGIN);

    alter table TBL_ICCID 
        add constraint UK_hedjwlbq8eys2nq719opxxnol  unique (ICCID);

    alter table TBL_IMSI 
        add constraint UK_o6tuhhoemhicw1xc4jol709jx  unique (IMSI);

    alter table TBL_SUBSCRIPTION 
        add constraint UK_1jg5gfttmmfkluk13dijnqen9  unique (SUBSCRIPTION_ID, SUBSCRIPTION_PROVIDER_ID);

    alter table TBL_AGENT 
        add constraint FK_1g8opu7jb8m993y2u5wfmcx99 
        foreign key (ROLE_ID) 
        references TBL_ROLE;

    alter table TBL_CUSTOMER 
        add constraint FK_rq2v0rpmglc2k6240wwf9abyv 
        foreign key (ADDRESS_ID) 
        references TBL_CUSTOMER_ADDRESS;

    alter table TBL_CUSTOMER 
        add constraint FK_6tpxblcwd2b1t3oh1ptfslko5 
        foreign key (DETAILS_ID) 
        references TBL_CUSTOMER_DETAILS;

    alter table TBL_CUSTOMER 
        add constraint FK_gjgmfedbpoyai8jqd7bsdb286 
        foreign key (STATE_ID) 
        references TBL_CUSTOMER_STATE;

    alter table TBL_CUSTOMER 
        add constraint FK_8u8v7d0gdwtnk9n6mx5h5f82e 
        foreign key (subscription_ID) 
        references TBL_SUBSCRIPTION;

    alter table TBL_CUSTOMER_ADDRESS 
        add constraint FK_skqreob2r7jgdlo2jtkfrpcc 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_CUSTOMER_AUD 
        add constraint FK_4a717a9wq5vv5rffd8x0bbron 
        foreign key (REV) 
        references REVINFO;

    alter table TBL_CUSTOMER_DETAILS 
        add constraint FK_arpq72hmpta1clwyff126888f 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_CUSTOMER_NOTE 
        add constraint FK_aowt43i58g7yy9vb4su5n81cy 
        foreign key (AGENT_ID) 
        references TBL_AGENT;

    alter table TBL_CUSTOMER_NOTE 
        add constraint FK_hmgftwwiokx6w69cqp37c38y8 
        foreign key (category_ID) 
        references TBL_CATEGORIES;

    alter table TBL_CUSTOMER_NOTE 
        add constraint FK_jqc31oin9q5b0rkus1t8wt32g 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_CUSTOMER_SETTINGS 
        add constraint FK_fxc1f4nvtprdbncsi4ycprld 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_CUSTOMER_STATE_AUD 
        add constraint FK_sj4t6s2q248oeim8n3tlt8uv8 
        foreign key (REV) 
        references REVINFO;

    alter table TBL_ICCID 
        add constraint FK_bi0li9e2wi0dmt7kpq0i0ntu2 
        foreign key (RC_STATE_ID) 
        references TBL_RC_STATE;

    alter table TBL_ICCID 
        add constraint FK_2u7tfsuw7f64subtui13de6k4 
        foreign key (RC_ID) 
        references TBL_RESOURCE_CONFIGURATION;

    alter table TBL_IMSI 
        add constraint FK_2lpmm420392m3covi73266s70 
        foreign key (RC_STATE_ID) 
        references TBL_RC_STATE;

    alter table TBL_IMSI 
        add constraint FK_3678eafjpse6f3y8oe7ca24tc 
        foreign key (RC_ID) 
        references TBL_RESOURCE_CONFIGURATION;

    alter table TBL_MSISDN 
        add constraint FK_aub4qgmgdwux7f20et7t7j8ji 
        foreign key (RC_STATE_ID) 
        references TBL_RC_STATE;

    alter table TBL_MSISDN 
        add constraint FK_q0a7et8nd4yb8odgi2f5loo8m 
        foreign key (RC_ID) 
        references TBL_RESOURCE_CONFIGURATION;

    alter table TBL_NP_REQUEST 
        add constraint FK_iqwbxokwbmjaquetmoadm5wc3 
        foreign key (SUBSCRIPTION_ID) 
        references TBL_SUBSCRIPTION;

    alter table TBL_PASSWORD_MGMT 
        add constraint FK_ao0gron12fo381mo2ioys8mi4 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_RC_STATE_AUD 
        add constraint FK_qjikujnkyje5wroy5sjmdv21e 
        foreign key (REV) 
        references REVINFO;

    alter table TBL_RESOURCE_CONFIGURATION 
        add constraint FK_6o6g9fd6g9ab4atll2kdtf3bb 
        foreign key (SUBSCRIPTION_ID) 
        references TBL_SUBSCRIPTION;

    alter table TBL_SIMSWAP 
        add constraint FK_e2tkiyhurg6u9bwvgljqfbgw4 
        foreign key (RC_ID) 
        references TBL_RESOURCE_CONFIGURATION;

    alter table TBL_SIMSWAP 
        add constraint FK_do5ot1h17y93wxqdgx71fv3wo 
        foreign key (SUBSCRIPTION_STATE_ID) 
        references TBL_SIMSWAP_STATE;

    alter table TBL_SIMSWAP 
        add constraint FK_pjowpq0vyxi16x9pxw3epmg5q 
        foreign key (SUBSCRIPTION_ID) 
        references TBL_SUBSCRIPTION;

    alter table TBL_SIMSWAP_AUD 
        add constraint FK_bnsj4ij0xduq9h0fntb2bt8tp 
        foreign key (REV) 
        references REVINFO;

    alter table TBL_SIMSWAP_STATE_AUD 
        add constraint FK_gupoqvbut1048tlg9bwql7ygb 
        foreign key (REV) 
        references REVINFO;

    alter table TBL_SUBSCRIPTION 
        add constraint FK_ntpy70os86wqkrc95rklaov3v 
        foreign key (SUBSCRIPTION_PROVIDER_ID) 
        references TBL_SUBSCRIPTION_PROVIDER;

    alter table TBL_SUBSCRIPTION 
        add constraint FK_4fiwxkparce853ywg4mvw3flu 
        foreign key (SUBSCRIPTION_STATE_ID) 
        references TBL_SUBSCRIPTION_STATE;

    alter table TBL_TOPUP 
        add constraint FK_l357n3mth5ry8ys0nbm6y52tq 
        foreign key (CUSTOMER_ID) 
        references TBL_CUSTOMER;

    alter table TBL_TOPUP 
        add constraint FK_1j1j2t0xw9eujy2x5r7reya6v 
        foreign key (TOPUP_STATE_ID) 
        references TBL_TOPUP_STATE;

    alter table TBL_TOPUP 
        add constraint FK_gaq7640y5h5gqo36mlwq1t7y2 
        foreign key (TOPUP_TYPE_ID) 
        references TBL_TOPUP_TYPE;

    create sequence hibernate_sequence;
