package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Ned Armstrong
 */
public class Report {

    private List<CellPhone> cellPhones = new ArrayList();
    private List<CellPhoneUsageByMonth> cellPhoneUsagesByMonth = new ArrayList();

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

        sb.append("Cell Phone Usage Report");
        sb.append("\n");

        Date todaysDate = new Date();

        //Header Section
        sb.append("\n")
                .append("Run: ").append(getDateString())
                .append("\nNumber of phones: ").append(cellPhones.size())
                .append("\nTotal Data: ").append(getTotalData())
                .append("\nAverage Minutes: ").append(getAverageMinutes())
                .append("\nAverage Data: ").append(getAverageData())
                .append("\n")
                .append("\nDetails\n\n").append(getCellMinutesUsage())
                .append("\nEnd of Report");

        //Debug
        System.out.print(sb.toString());

        return sb.toString();
    }

    private String getDateString() {
        Date todaysDate = new Date();
        return new SimpleDateFormat("MM/dd yyyy").format(todaysDate);
    }

    private Double getTotalData() {
        Double total = 0.0;
        for (CellPhoneUsageByMonth usage : cellPhoneUsagesByMonth) {
            if (usage.getDataUsed() != null) {
                total += usage.getDataUsed();
            }
        }
        return total;
    }

    private Double getAverageMinutes() {
        Double total = 0.0;
        for (CellPhoneUsageByMonth usage : cellPhoneUsagesByMonth) {
            if (usage.getMinutesUsed() != null) {
                total += usage.getMinutesUsed();
            }
        }
        return total / cellPhoneUsagesByMonth.size();
    }

    private Double getAverageData() {
        Double total = 0.0;
        for (CellPhoneUsageByMonth usage : cellPhoneUsagesByMonth) {
            if (usage.getDataUsed() != null) {
                total += usage.getDataUsed();
            }
        }
        return total / cellPhoneUsagesByMonth.size();
    }

    private String getCellMinutesUsage() {
        StringBuilder sb = new StringBuilder();

        //Minute map
        Map<String, Map<Integer, Map<String, List<Double>>>> employeeMinutesMap = new HashMap();
        for (CellPhone cellPhone : cellPhones) {
            String employeeId = cellPhone.getEmployeeId();
            employeeMinutesMap.put(employeeId, getEmployeeMinutesMap(employeeId));
        }
        //Data usage map
        Map<String, Map<Integer, Map<String, List<Double>>>> employeeDataUsageMap = new HashMap();
        for (CellPhone cellPhone : cellPhones) {
            String employeeId = cellPhone.getEmployeeId();
            employeeDataUsageMap.put(employeeId, getEmployeeDataUsageMap(employeeId));
        }

        for (Map.Entry<String, Map<Integer, Map<String, List<Double>>>> entry : employeeMinutesMap.entrySet()) {
            //Add employee cell phone info
            for (int i = 0; i < cellPhones.size(); i++) {
                if (entry.getKey().equals(cellPhones.get(i).getEmployeeId())) {
                    sb.append("\n----------------------\n");
                    sb.append(cellPhones.get(i));
                    break;
                }
            }
            
            //TODO: Sort by Month
            
            //Add minutes
            sb.append("\n\nMinutes Used\n");
            for (Map.Entry<Integer, Map<String, List<Double>>> entry2 : entry.getValue().entrySet()) {
                sb.append("\nYear: ").append(entry2.getKey()).append("\n\n");
                sb.append(getColumns(entry2.getValue()));
            }

            //Add data usage
            sb.append("\n\nData Used\n");
            for (Map.Entry<Integer, Map<String, List<Double>>> entry2 : employeeDataUsageMap.get(entry.getKey()).entrySet()) {
                sb.append("\nYear: ").append(entry2.getKey()).append("\n\n");
                sb.append(getColumns(entry2.getValue()));
            }
        }

        //Add cell phone info to String
        return sb.toString();
    }

    private Map<Integer, Map<String, List<Double>>> getEmployeeMinutesMap(String employeeId) {

        List<CellPhoneUsageByMonth> employeeCellPhoneUsages = new ArrayList<>();
        for (CellPhoneUsageByMonth usage : cellPhoneUsagesByMonth) {
            if (usage.getEmployeeId().equals(employeeId)) {
                employeeCellPhoneUsages.add(usage);
            }
        }

        //Separate by Year
        Map<Integer, List<CellPhoneUsageByMonth>> yearlyMap = new HashMap();
        for (CellPhoneUsageByMonth outer : employeeCellPhoneUsages) {
            int outerYear = outer.getYear();
            List<CellPhoneUsageByMonth> monthlyList = new ArrayList<>();
            for (CellPhoneUsageByMonth inner : employeeCellPhoneUsages) {
                if (outerYear == inner.getYear()) {
                    monthlyList.add(inner);
                }
            }
            yearlyMap.put(outerYear, monthlyList);
        }

        //Create data map of year/ month /date
        Map<Integer, Map<String, List<Double>>> employeeDataMap = new HashMap();
        for (Map.Entry<Integer, List<CellPhoneUsageByMonth>> entry : yearlyMap.entrySet()) {
            //Separate by Month
            Map<String, List<Double>> monthlyMinutes = new HashMap<>();
            for (CellPhoneUsageByMonth outer : entry.getValue()) {
                String outerMonth = outer.getMonth();
                //Get all minutes for a given month
                List<Double> monthlyData = new ArrayList<>();
                for (CellPhoneUsageByMonth inner : entry.getValue()) {
                    if (outerMonth.equals(inner.getMonth())) {
                        monthlyData.add(inner.getMinutesUsed());
                    }
                }
                monthlyMinutes.put(outerMonth, monthlyData);
            }
            employeeDataMap.put(entry.getKey(), monthlyMinutes);
        }

        return employeeDataMap;
    }

    private Map<Integer, Map<String, List<Double>>> getEmployeeDataUsageMap(String employeeId) {
        List<CellPhoneUsageByMonth> employeeCellPhoneUsages = new ArrayList<>();
        for (CellPhoneUsageByMonth usage : cellPhoneUsagesByMonth) {
            if (usage.getEmployeeId().equals(employeeId)) {
                employeeCellPhoneUsages.add(usage);
            }
        }

        //Separate by Year
        Map<Integer, List<CellPhoneUsageByMonth>> yearlyMap = new HashMap();
        for (CellPhoneUsageByMonth outer : employeeCellPhoneUsages) {
            int outerYear = outer.getYear();
            List<CellPhoneUsageByMonth> monthlyList = new ArrayList<>();
            for (CellPhoneUsageByMonth inner : employeeCellPhoneUsages) {
                if (outerYear == inner.getYear()) {
                    monthlyList.add(inner);
                }
            }
            yearlyMap.put(outerYear, monthlyList);
        }

        //Create data map of year/ month /date
        Map<Integer, Map<String, List<Double>>> employeeDataMap = new HashMap();
        for (Map.Entry<Integer, List<CellPhoneUsageByMonth>> entry : yearlyMap.entrySet()) {
            //Separate by Month
            Map<String, List<Double>> monthlyMinutes = new HashMap<>();
            for (CellPhoneUsageByMonth outer : entry.getValue()) {
                String outerMonth = outer.getMonth();
                //Get all minutes for a given month
                List<Double> monthlyData = new ArrayList<>();
                for (CellPhoneUsageByMonth inner : entry.getValue()) {
                    if (outerMonth.equals(inner.getMonth())) {
                        monthlyData.add(inner.getDataUsed());
                    }
                }
                monthlyMinutes.put(outerMonth, monthlyData);
            }
            employeeDataMap.put(entry.getKey(), monthlyMinutes);
        }

        return employeeDataMap;
    }

    private String getColumns(Map<String, List<Double>> inputMap) {
        StringBuilder sb = new StringBuilder();

        int maxColumnWidth = 8;

        for (Map.Entry<String, List<Double>> entry : inputMap.entrySet()) {
            StringBuilder value = new StringBuilder();
            value.append(entry.getKey());
            while (value.length() < maxColumnWidth) {
                value.append(" ");
            }
            sb.append(value);
        }
        sb.append("\n");

        int maxCount = 0;
        for (Map.Entry<String, List<Double>> entry : inputMap.entrySet()) {
            if (entry.getValue().size() > maxCount) {
                maxCount = entry.getValue().size();
            }
        }

        for (int i = 0; i < maxCount; i++) {
            for (Map.Entry<String, List<Double>> entry : inputMap.entrySet()) {
                StringBuilder value = new StringBuilder();
                if (entry.getValue().size() > i) {
                    value.append(entry.getValue().get(i));
                }
                while (value.length() < maxColumnWidth) {
                    value.append(" ");
                }
                sb.append(value);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
