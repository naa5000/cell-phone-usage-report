/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import receiptbook.ReceiptBook;

/*
 * @author Ned Armstrong
 */
public class Report extends ReportMethods {

    public Report() {
    }

    public StringBuilder getReportText() {
        ArrayList<Receipt> receipts = ReceiptBook.getReceiptsForReport() == null ? null : ReceiptBook.getReceiptsForReport();
        String startDate;// = getStringDate(receiptBook.getReceipt);
        String endDate;// = getStringDate(null);

        //get Dates
        if (receipts != null && ReceiptBook.getStartDateForReport() == null) {
            startDate = getStringDate(getFirstReceipt(receipts).getDate());
        } else {
            startDate = getStringDate(ReceiptBook.getStartDateForReport());
        }
        if (receipts != null && ReceiptBook.getEndDateForReport() == null) {
            endDate = getStringDate(new Date());
        } else {
            endDate = getStringDateMinusOne(ReceiptBook.getEndDateForReport());
        }

        StringBuilder sb = new StringBuilder("");

        //Heading ---------------------------------------------------------------------------------
        sb.append("Receipts Marriage Licenses Report\t\t\t\t ").append("run: ").append(getStringDateWithTime(new Date()));
        sb.append("\n");
        sb.append("\nFor: ").append(startDate).append(" - ").append(endDate);

        //Variables --------------------------------------------------------------------------
        addVariables(sb);

        //Licenses --------------------------------------------------------------------------
        if (receipts != null) {

            sb.append("\n");
            sb.append("\n");
            sb.append("\nSorted by License Number");
             sb.append("\n");
            sb.append("\n");
            
            receipts = sortByLicenseNumber(receipts);
            for (Receipt r : receipts) {
                if (r != null && r.getMarriageLicensesReceipt() != null && r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber() != null && !r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber().equals("")) {
                    sb.append("\n").append(r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber()).append("\t").append(getStringDate(r.getDate()));
                }
            }
            
            sb.append("\n");
            sb.append("\n");
            sb.append("\n");
            sb.append("--------------------------------");
            sb.append("\n");
            sb.append("\n");
            sb.append("\nSorted by Date");
            sb.append("\n");
            sb.append("\n");
            
            receipts = sortByDate(receipts);
            for (Receipt r : receipts) {
                if (r != null && r.getMarriageLicensesReceipt() != null && r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber() != null && !r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber().equals("")) {
                    sb.append("\n").append(getStringDate(r.getDate())).append("\t").append(r.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber());
                }
            }
            
        }
        return sb;
    }

    public ArrayList<Receipt> sortByLicenseNumber(ArrayList<Receipt> receipts) {
        Collections.sort(receipts, (Receipt one, Receipt other) -> {
            return one.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber().compareTo(other.getMarriageLicensesReceipt().getCountyMarriageLicenseNumber());
        });
        return receipts;
    }
    
    public ArrayList<Receipt> sortByDate(ArrayList<Receipt> receipts) {
        Collections.sort(receipts, (Receipt one, Receipt other) -> {
            return one.getDate().compareTo(other.getDate());
        });
        return receipts;
    }

}
