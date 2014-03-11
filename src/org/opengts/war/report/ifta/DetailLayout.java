package org.opengts.war.report.ifta;

import org.opengts.war.report.DataRowTemplate;
import org.opengts.war.report.ReportLayout;

/**
 * Created by lazar on 2/22/14.
 */

public class DetailLayout
        extends ReportLayout
{
    private static DetailLayout reportDef = null;

    public static ReportLayout getReportLayout()
    {
        if (reportDef == null) {
            reportDef = new DetailLayout();
        }
        return reportDef;
    }

    /**
     *** Standard singleton constructor
     **/
    private DetailLayout()
    {
        super();
        this.setDataRowTemplate(new IftaDataRow());
    }

    protected static class IftaDataRow
            extends DataRowTemplate
    {
        public IftaDataRow() {
            super();

        }
    }

}