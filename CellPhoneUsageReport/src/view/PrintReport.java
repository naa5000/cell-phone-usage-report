package view;

import controller.CsvParser;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CellPhone;
import model.CellPhoneUsageByMonth;
import model.Report;

/**
 *
 * @author nedar
 */
public class PrintReport implements Printable, ActionListener {

    
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering */
        String report = getReport();
        
        g.drawString(report, 100, 100);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(PrintReport.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }

    private String getReport() {

        //TODO: Get these from database instead of local files.
        String cellPhonesCSVFilePath = "src/data/cell_phones.txt";
        String cellPhoneUsageByMonthCSVFilePath = "src/data/cell_phone_usages_by_month.txt";

        CsvParser csvParser = new CsvParser();

        List<CellPhone> cellPhones = new ArrayList<>();
        try {
            csvParser.parseCellPhonesFromCSV(cellPhonesCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth = new ArrayList<>();
        try {
            csvParser.parseCellPhoneUsageReportsFromCSV(cellPhoneUsageByMonthCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Report report = new Report(cellPhones, cellPhoneUsagesByMonth);
        
        return report.getReport();
    }
}
