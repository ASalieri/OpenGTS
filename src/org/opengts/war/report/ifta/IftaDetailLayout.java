package org.opengts.war.report.ifta;

import org.opengts.war.report.DataRowTemplate;
import org.opengts.war.report.ReportLayout;

/**
 * Created by lazar on 2/22/14.
 */

public class IftaDetailLayout
        extends ReportLayout
{
    private static IftaDetailLayout reportDef = null;

    public static ReportLayout getReportLayout()
    {
        if (reportDef == null) {
            reportDef = new IftaDetailLayout();
        }
        return reportDef;
    }

    /**
     *** Standard singleton constructor
     **/
    private IftaDetailLayout()
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