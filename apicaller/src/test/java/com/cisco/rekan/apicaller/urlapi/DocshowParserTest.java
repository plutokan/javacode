/*
 * Copyright (C) Cisco Systems(China)Research and Development Co.,
 * Ltd. Hefei Branch Office
 * Building D1, Innovation Park, 800 Wangjiang Xi Road, High-tech Zone, Hefei City,
 * Anhui Province, China All rights reserved.
 */
package com.cisco.rekan.apicaller.urlapi;

import static java.lang.System.out;

import org.junit.Test;
/**
 * <code>DocshowParserTest</code>
 *
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 * @since apicaller Nov 11, 2015
 *
 */
public class DocshowParserTest {

    @Test
    public void getBitValue4Int() {
        // 2.1 Enable PMR with callin + voip, MTGCONFERENCE.OPTIONS=954205183 (5bit-1; 27bit-1), WbxMMConfParam.PARAMVALUE=0 for PARAMNAME='HybridVoipOnly';  --should be 0 in WBXMMCONFPARAM.
        // 2.2 Change callin + voip to voip, MTGCONFERENCE.OPTIONS=954205151 (5bit-0; 27bit-1), WbxMMConfParam.PARAMVALUE=1 for PARAMNAME='HybridVoipOnly';
        // 2.3 Change voip to callin + voip, MTGCONFERENCE.OPTIONS=954205183 (5bit-1; 27bit-1), WbxMMConfParam.PARAMVALUE=0 for PARAMNAME='HybridVoipOnly';  --should be 0 in WBXMMCONFPARAM.
        boolean[] bits = DocshowParser.getBitValue4Int(954205183);
        out.println(bits[5]); // telephony
        out.println(bits[27]); // voip

        // 2.1 Enable PMR with voip, MTGCONFERENCE.OPTIONS=954205151 (5bit-0; 27bit-1), WbxMMConfParam.PARAMVALUE=1 for PARAMNAME='HybridVoipOnly';
        // 2.2 Change voip to callin, MTGCONFERENCE.OPTIONS=954205183 (5bit-1; 27bit-1), WbxMMConfParam.PARAMVALUE=0 for PARAMNAME='HybridVoipOnly';
        // 2.3 Change callin to voip, MTGCONFERENCE.OPTIONS=954205151 (5bit-0; 27bit-1), WbxMMConfParam.PARAMVALUE=1 for PARAMNAME='HybridVoipOnly';
    }

}
