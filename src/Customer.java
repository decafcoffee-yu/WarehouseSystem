import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Customer extends Person implements Comparable<Customer>, Serializable {
    private int customerID;
    private boolean status;
    private HashMap<Integer, Invoice> InvoiceAssociated;
    private BigDecimal salesTax;
    private LocalDate lastOrderDate;

//    public Customer(){
//        this.customerID = 0;
//        status= true;
//        lastOrderDate = null;
//    }


    public Customer(String firstName, String lastName, String phone, String address, BigDecimal salesTax, int customerID) {
        super(firstName, lastName, phone, address);
        this.customerID = customerID;
        status = true;
        this.salesTax = salesTax;
        lastOrderDate = null;
        InvoiceAssociated = new HashMap<>();
    }

    public void addInvoiceAssociated(Invoice ia){
        InvoiceAssociated.put(ia.getInvoiceNumber(), ia);
    }

    public HashMap<Integer, Invoice> getInvoiceAssociated(){
        return InvoiceAssociated;
    }


    public void displayInvoiceAssociated() {
        for(Invoice invoice: InvoiceAssociated.values()){
            System.out.println(invoice);
        }
    }

    public boolean isActive(){
        if(lastOrderDate == null){
            return status = true; //active
        }
        else {
            LocalDate today = LocalDate.now();
            long p = ChronoUnit.DAYS.between(lastOrderDate, today);
            if(p < 365){
                return status = true; //active
            }
            else
                return status = false; //inactive, last order more than 365 days
        }

    }
    public void changeStatus() {
        if(status = false){
            status = true;
            System.out.println("the customer is now marked active");}
        else if (status = true){
            status = false;
            System.out.println("the customer is now marked inactive");}
    }
    public int getCustomerID() { return customerID; }
    public boolean getStatus() { return status; }
    public BigDecimal getSalesTax() { return salesTax; }
    public void setSalesTax(BigDecimal salesTax) { this.salesTax = salesTax; }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInvoiceAssociated(HashMap<Integer, Invoice> invoiceAssociated) {
        InvoiceAssociated = invoiceAssociated;
    }

    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(LocalDate lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public String getType() {
        return "Customer";
    }
    @Override
    public String toString()
    {
        return "Customer ID: " + getCustomerID() + ", Type: "+ getType() + ", " + super.toString() +
                ", Status: " + getStatus()  + ", Sales Tax: " + getSalesTax() + "%";
    }

    @Override
    public int compareTo(Customer o)
    {
        if(o == null)
            return 0;
        else
            return (getName().compareTo(o.getName()));
    }

}
