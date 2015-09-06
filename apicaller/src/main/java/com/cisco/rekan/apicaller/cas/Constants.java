/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * No. 308 Xiangzhang Drive, Hefei New and High Technology Area, Hefei,
 * Anhui, China All rights reserved.
 */

package com.cisco.rekan.apicaller.cas;


/**
 * <code>Constants</code>
 *
 * @author Pluto Kan, rekan@cisco.com
 * @since MyCode Oct 27, 2013
 *
 */
public final class Constants {

    private Constants() {
    }

    //String url = "http://localhost/cas/auth.do";
    //String url = "https://login-hm1-hn1.qa.webex.com:443/cas/auth.do";//HM/HN
    //String url = "https://login-hl1.qa.webex.com:443/cas/auth.do";//vscm
//    public static final String CAS_SERVER_URL =  "https://login-sz1-hf1.qa.webex.com:443/cas/auth.do"; //3 DC
//    public static final String CAS_SERVER_URL = "https://10.224.71.55:443/cas/auth.do";
    public static final String CAS_SERVER_URL = "https://10.79.159.62:444/cas/auth.do";
//    public static final String CAS_SERVER_URL = "http://10.79.159.62/cas/auth.do";

    public static final String APPNAME_W11 = "APP_WebEx11";
    public static final String APPNAME_CI = "APP_CommonIdentity";
    public static final String APPNAME_GLA = "APP_GLA";

    public static final String CLIENTID_PAGE = "beea24c7e93c56007a7eb4428bdba198";
    public static final String CLIENTID_PT = "5da5ab16460a9f1616d229ebee131535";
    public static final String CLIENTID_ANDRIOD = "458e3bf9c37c2dd93a93b2cb108adeab";
    public static final String CLIENTID_MANU = "b5a09c0ea044ffec48e433453732ecb1";
    public static final String CLIENTID_GLA = "576e0bb6014030d1d5c35f51ff4b3bf5";

    /** loginname */
    public static final String LOGINNAME_PAGE = "gadmin.0701@test.com";
    public static final String LOGINNAME_PT = "Kiki@kikiwebex11ef39061522a.com";

    /**
Meetings Only:
v14szef1@szwtv13ef1.com 
v14szef2@szwtv13ef1.com 
v14hfep1@hfwtv13ep001.com 
v14hfep2@hfwtv13ep001.com 
admin@reapermeetingef1.com (Not synched to CI, PW: P@ss123)

Meetings + IM:
v14szef3@szwtv13ef1.com
v14hfep3@hfwtv13ep001.com
ltuser_ta@lowtouch.com

EIM Only:
v14szeim1@szwtv13eim001.com
v14szeim2@szwtv13eim001.com
v14szeim3@szwtv13eim001.com

     */
    public static final String EMALI_NORMAL = "gadmin.0701@test.com";
    public static final String EMALI_MEETING_1 = "v14szef1@szwtv13ef1.com";
    public static final String EMALI_MEETING_2 = "v14szef2@szwtv13ef1.com";
    public static final String EMALI_MEETING_IM_1 = "v14szef3@szwtv13ef1.com";

    public static final String PASSWORD_NORMAL = "P@ssword123";

    /**
     *  Normal:9cad1c8c-abd0-4240-a0bc-9a43a7977bc3
        Pending activation:061eca5e-4d0f-427e-86ca-35896dc23226
        Pending approval: 14972b51-8293-48be-ab21-3c5cb54acaab
        Inactive user:709ba6f4-843d-4588-9c31-a45b646baecf
        Transient: e14cdd93-9f5c-482c-96ec-1e0e56e7b56d
        Fraud Admin: f46e99b2-2509-4c5b-bcb7-f594fb175b2c
        Fraud free trial: 42366314-4878-4b57-a991-56c5a97fac01
        Fraund member:1019648f-7513-4ab4-b71e-d0b7d3509d37
        Compliance admin: a5cc94e1-2400-41fb-87b1-02f321d0fb9b
        Compliance free trial: 22e04ec5-8459-41a2-bdd8-0d98bc9c7b1b
        Compliance member: 77bebf42-b503-4017-b49d-ae752bc0c16b
        Collection admin: 73393396-1a99-4ffe-b2f4-96dfb19341b8
        Collection member: a1a99a5c-3958-4a10-9a31-0ee5ac037e72
        
        
        Meeting only: bb078fce-9d80-4a91-a29d-4e45adb173c2/v14szef2@szwtv13ef1.com
        212aaaa5-2615-4bbc-b43a-fedeaacf385e
        EIM: ec532ea7-eb48-4fb2-a5c3-1770a87f07b0
     */
    public static final String USER_NORMAL = "9cad1c8c-abd0-4240-a0bc-9a43a7977bc3";
    public static final String USER_MEETINGONLY_2 = "bb078fce-9d80-4a91-a29d-4e45adb173c2";

}
