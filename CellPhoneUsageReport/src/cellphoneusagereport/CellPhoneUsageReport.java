/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cellphoneusagereport;

import controller.CsvParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nedar
 */
public class CellPhoneUsageReport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String cellPhonesCSVFilePath = "src/data/cell_phones.txt";
        String cellPhoneUsageByMonthCSVFilePath = "src/data/cell_phone_usages_by_month.txt";
        
        CsvParser csvParser = new CsvParser();
        
        
        try {
            csvParser.parseCellPhonesFromCSV(cellPhonesCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            csvParser.parseCellPhoneUsageReportsFromCSV(cellPhoneUsageByMonthCSVFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CellPhoneUsageReport.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
    
}
