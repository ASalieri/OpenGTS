package org.brongus.shapefile.bcross;

import org.brongus.shapefile.files.dbf.DBF_Field;

import java.util.HashMap;

/**
 * Created by lazar on 3/22/14.
 */
public class StateInfo {
    public static final String UNKNOWN_INFO_STR = "N/A";

    // Note: These IDs depends on dbf file field names
    private static final String STATE_NAME_FIELD_DBF_ID = "STATE_NAME";
    private static final String STATE_ABBR_FIELD_DBF_ID = "STATE_ABBR";
    private static final String STATE_SUB_REGION_FIELD_DBF_ID = "SUB_REGION";

    HashMap<String, Integer> dbfMap;


    public String stateName = new String(UNKNOWN_INFO_STR);
    public String stateAbbreviation = new String(UNKNOWN_INFO_STR);
    public String stateSubRegion = new String(UNKNOWN_INFO_STR);

    public StateInfo(DBF_Field[] dbf_fields) {

        dbfMap = new HashMap<String, Integer>();
        for(int i = 0; i < dbf_fields.length; i++) {
            dbfMap.put(dbf_fields[i].getName(), i);
        }
    }

    public void hydrateFromDbfRecord(String[] stateInfo) {
        stateName = stateInfo[dbfMap.get(STATE_NAME_FIELD_DBF_ID)].trim();
        stateAbbreviation = stateInfo[dbfMap.get(STATE_ABBR_FIELD_DBF_ID)].trim();
        stateSubRegion = stateInfo[dbfMap.get(STATE_SUB_REGION_FIELD_DBF_ID)].trim();
    }
}
