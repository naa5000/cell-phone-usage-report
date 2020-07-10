
package model;

/**
 *
 * @author nedar
 */
public class CellPhoneUsageByMonth {
    
    private String employeeId;
    private int year;
    private int month;
    private long minutesUsed;
    private long dataUsed;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getMinutesUsed() {
        return minutesUsed;
    }

    public void setMinutesUsed(long minutesUsed) {
        this.minutesUsed = minutesUsed;
    }

    public long getDataUsed() {
        return dataUsed;
    }

    public void setDataUsed(long dataUsed) {
        this.dataUsed = dataUsed;
    }
    
    @Override
    public String toString() {
        return "CellPhoneUsageByMonth [ employeeId = " + employeeId + ", year = " + year + ", month = " + month + ", minutesUsed = " + minutesUsed + ", dataUsed = " + dataUsed + "]";
    }
    
    
}
