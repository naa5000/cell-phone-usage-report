package model;

/**
 *
 * @author nedar
 */
public class CellPhoneUsageByMonth {

    private String employeeId;
    private int year;
    private String month;
    private Double minutesUsed;
    private Double dataUsed;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getMinutesUsed() {
        return minutesUsed;
    }

    public void setMinutesUsed(Double minutesUsed) {
        this.minutesUsed = minutesUsed;
    }

    public Double getDataUsed() {
        return dataUsed;
    }

    public void setDataUsed(Double dataUsed) {
        this.dataUsed = dataUsed;
    }

    @Override
    public String toString() {
        return "CellPhoneUsageByMonth [ employeeId = " + employeeId + ", year = " + year + ", month = " + month + ", minutesUsed = " + minutesUsed + ", dataUsed = " + dataUsed + "]";
    }

}
