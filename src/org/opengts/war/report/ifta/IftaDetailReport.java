package org.opengts.war.report.ifta;

import org.opengts.war.report.*;
import org.opengts.war.tools.RequestProperties;

/**
 * Created by lazar on 2/22/14.
 */
public class IftaDetailReport
        extends ReportData
{

    public IftaDetailReport(ReportEntry rptEntry, RequestProperties reqState, ReportDeviceList devList)
            throws ReportException {
        super(rptEntry, reqState, devList);

        /* has account */
        if (this.getAccount() == null) {
            throw new ReportException("Account-ID not specified");
        }

        /* has at least one device */
        if (this.getDeviceCount() <= 0) {
            throw new ReportException("No Devices specified");
        }

    }

    public boolean isSingleDeviceOnly() {
        return true;
    }

    /**
     * ** Gets the bound ReportLayout singleton instance for this report
     * ** @return The bound ReportLayout
     */
    public static ReportLayout GetReportLayout() {
        // bind the report format to this data
        return IftaDetailLayout.getReportLayout();
    }

    /**
     * ** Gets the bound ReportLayout singleton instance for this report
     * ** @return The bound ReportLayout
     */
    public ReportLayout getReportLayout() {
        // bind the report format to this data
        return GetReportLayout();
    }

    /**
     * ** Creates and returns an iterator for the row data displayed in the body of this report.
     * ** @return The body row data iterator
     */
    public DBDataIterator getBodyDataIterator() {
        return super.getEventDataIterator();
    }

    /**
     * ** Creates and returns an iterator for the row data displayed in the total rows of this report.
     * ** @return The total row data iterator
     */
    public DBDataIterator getTotalsDataIterator() {
        // TODO: keep track of distance traveled for report?
        return null;
    }

    // ------------------------------------------------------------------------

}
