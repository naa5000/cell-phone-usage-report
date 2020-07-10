package model;

/**
 *
 * @author nedar
 */
public class CellPhone {

    private String employeeId;
    private String employeeName;
    private String purchaseDate;
    private String model;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return  "EmployeeId: " + employeeId + 
                "\nEmployeeName: " + employeeName + 
                "\nModel: " + model + 
                "\nPurchaseDate: " + purchaseDate; 
    }

}
