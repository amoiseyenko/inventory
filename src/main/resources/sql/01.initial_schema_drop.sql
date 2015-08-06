
    alter table TBL_AGENT 
        drop constraint FK_1g8opu7jb8m993y2u5wfmcx99;

    alter table TBL_CUSTOMER 
        drop constraint FK_rq2v0rpmglc2k6240wwf9abyv;

    alter table TBL_CUSTOMER 
        drop constraint FK_6tpxblcwd2b1t3oh1ptfslko5;

    alter table TBL_CUSTOMER 
        drop constraint FK_gjgmfedbpoyai8jqd7bsdb286;

    alter table TBL_CUSTOMER 
        drop constraint FK_8u8v7d0gdwtnk9n6mx5h5f82e;

    alter table TBL_CUSTOMER_ADDRESS 
        drop constraint FK_skqreob2r7jgdlo2jtkfrpcc;

    alter table TBL_CUSTOMER_AUD 
        drop constraint FK_4a717a9wq5vv5rffd8x0bbron;

    alter table TBL_CUSTOMER_DETAILS 
        drop constraint FK_arpq72hmpta1clwyff126888f;

    alter table TBL_CUSTOMER_NOTE 
        drop constraint FK_aowt43i58g7yy9vb4su5n81cy;

    alter table TBL_CUSTOMER_NOTE 
        drop constraint FK_hmgftwwiokx6w69cqp37c38y8;

    alter table TBL_CUSTOMER_NOTE 
        drop constraint FK_jqc31oin9q5b0rkus1t8wt32g;

    alter table TBL_CUSTOMER_SETTINGS 
        drop constraint FK_fxc1f4nvtprdbncsi4ycprld;

    alter table TBL_CUSTOMER_STATE_AUD 
        drop constraint FK_sj4t6s2q248oeim8n3tlt8uv8;

    alter table TBL_ICCID 
        drop constraint FK_bi0li9e2wi0dmt7kpq0i0ntu2;

    alter table TBL_ICCID 
        drop constraint FK_2u7tfsuw7f64subtui13de6k4;

    alter table TBL_IMSI 
        drop constraint FK_2lpmm420392m3covi73266s70;

    alter table TBL_IMSI 
        drop constraint FK_3678eafjpse6f3y8oe7ca24tc;

    alter table TBL_MSISDN 
        drop constraint FK_aub4qgmgdwux7f20et7t7j8ji;

    alter table TBL_MSISDN 
        drop constraint FK_q0a7et8nd4yb8odgi2f5loo8m;

    alter table TBL_NP_REQUEST 
        drop constraint FK_iqwbxokwbmjaquetmoadm5wc3;

    alter table TBL_PASSWORD_MGMT 
        drop constraint FK_ao0gron12fo381mo2ioys8mi4;

    alter table TBL_RC_STATE_AUD 
        drop constraint FK_qjikujnkyje5wroy5sjmdv21e;

    alter table TBL_RESOURCE_CONFIGURATION 
        drop constraint FK_6o6g9fd6g9ab4atll2kdtf3bb;

    alter table TBL_SIMSWAP 
        drop constraint FK_e2tkiyhurg6u9bwvgljqfbgw4;

    alter table TBL_SIMSWAP 
        drop constraint FK_do5ot1h17y93wxqdgx71fv3wo;

    alter table TBL_SIMSWAP 
        drop constraint FK_pjowpq0vyxi16x9pxw3epmg5q;

    alter table TBL_SIMSWAP_AUD 
        drop constraint FK_bnsj4ij0xduq9h0fntb2bt8tp;

    alter table TBL_SIMSWAP_STATE_AUD 
        drop constraint FK_gupoqvbut1048tlg9bwql7ygb;

    alter table TBL_SUBSCRIPTION 
        drop constraint FK_ntpy70os86wqkrc95rklaov3v;

    alter table TBL_SUBSCRIPTION 
        drop constraint FK_4fiwxkparce853ywg4mvw3flu;

    alter table TBL_TOPUP 
        drop constraint FK_l357n3mth5ry8ys0nbm6y52tq;

    alter table TBL_TOPUP 
        drop constraint FK_1j1j2t0xw9eujy2x5r7reya6v;

    alter table TBL_TOPUP 
        drop constraint FK_gaq7640y5h5gqo36mlwq1t7y2;

    drop table if exists REVINFO cascade;

    drop table if exists TBL_AGENT cascade;

    drop table if exists TBL_CATEGORIES cascade;

    drop table if exists TBL_CUSTOMER cascade;

    drop table if exists TBL_CUSTOMER_ADDRESS cascade;

    drop table if exists TBL_CUSTOMER_AUD cascade;

    drop table if exists TBL_CUSTOMER_DETAILS cascade;

    drop table if exists TBL_CUSTOMER_NOTE cascade;

    drop table if exists TBL_CUSTOMER_SETTINGS cascade;

    drop table if exists TBL_CUSTOMER_STATE cascade;

    drop table if exists TBL_CUSTOMER_STATE_AUD cascade;

    drop table if exists TBL_ICCID cascade;

    drop table if exists TBL_IMSI cascade;

    drop table if exists TBL_MSISDN cascade;

    drop table if exists TBL_NP_REQUEST cascade;

    drop table if exists TBL_PASSWORD_MGMT cascade;

    drop table if exists TBL_RC_STATE cascade;

    drop table if exists TBL_RC_STATE_AUD cascade;

    drop table if exists TBL_RESOURCE_CONFIGURATION cascade;

    drop table if exists TBL_ROLE cascade;

    drop table if exists TBL_SIMSWAP cascade;

    drop table if exists TBL_SIMSWAP_AUD cascade;

    drop table if exists TBL_SIMSWAP_STATE cascade;

    drop table if exists TBL_SIMSWAP_STATE_AUD cascade;

    drop table if exists TBL_SUBSCRIPTION cascade;

    drop table if exists TBL_SUBSCRIPTION_PROVIDER cascade;

    drop table if exists TBL_SUBSCRIPTION_STATE cascade;

    drop table if exists TBL_TOPUP cascade;

    drop table if exists TBL_TOPUP_STATE cascade;

    drop table if exists TBL_TOPUP_TYPE cascade;

    drop sequence hibernate_sequence;
