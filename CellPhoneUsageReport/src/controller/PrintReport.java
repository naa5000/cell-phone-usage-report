package controller;

import java.awt.Color;
import java.awt.Font;
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
import view.CellPhoneUsageReport;

/**
 *
 * @author nedar
 */
public class PrintReport implements Printable, ActionListener {

    private final int linesPerPage = 30;

  
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        
        String sTable[] = getReport();
        
        if (pageIndex * linesPerPage >= sTable.length) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Monospaced", Font.PLAIN, 9));
        g2.setPaint(Color.black);
        Paper copy = pf.getPaper();
        copy.setSize(600, pf.getHeight());
        pf.setPaper(copy);
        
        int x = 20;
        int y = 100;
        for (int i = linesPerPage * pageIndex; i < sTable.length
                && i < linesPerPage * (pageIndex + 1); i++) {
            g2.drawString(sTable[i], x, y);
            y += 10;
        }
        return PAGE_EXISTS;
    }

    @Override
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

    private String[] getReport() {

        //TODO: Get these from database instead of local files.
        String cellPhonesCSVFilePath = "src/data/cell_phones.txt";
        String cellPhoneUsageByMonthCSVFilePath = "src/data/cell_phone_usages_by_month.txt";

        CsvParser csvParser = new CsvParser();

        List<CellPhone> cellPhones = new ArrayList<>();
        try {
            cellPhones = csvParser.parseCellPhonesFromCSV(cellPhonesCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth = new ArrayList<>();
        try {
            cellPhoneUsagesByMonth = csvParser.parseCellPhoneUsageReportsFromCSV(cellPhoneUsageByMonthCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        Report report = new Report(cellPhones, cellPhoneUsagesByMonth);

        return report.getReport().split("\\r?\\n");
        
    }
}
