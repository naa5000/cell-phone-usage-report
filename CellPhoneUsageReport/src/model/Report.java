
package model;

import java.util.List;

/*
 * @author Ned Armstrong
 */
public class Report {

    private final List<CellPhone> cellPhones;
    private final List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth;

    public Report(List<CellPhone> cellPhones, List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth) {
        this.cellPhones = cellPhones;
        this.cellPhoneUsagesByMonth = cellPhoneUsagesByMonth;
    }
    
    

    public String getReport() {

        StringBuilder sb = new StringBuilder("");

        //Header Section ---------------------------------------------------------------------------------
        
        //TODO: Create report
        
        //Debug
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
       
        
        
        sb.append("Header Section info");
        sb.append("\n");
    
        System.out.print(sb.toString());
        
        //Debug
        return "";
    }

   

}
