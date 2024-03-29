import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerController {
    CustomerBoundary customerBoundary = new CustomerBoundary(this);
    Customer customer;

    // Look at the ID value in the last line Customer.txt and add 1 to it.
    public int findNextCustomerID() {
        // If the file doesn't exist, that implies it's the first customer
        if (!new File("Customer.txt").exists()) {
            return 1;
        }
        int max = 0;
        for (Integer i : Main.customers.keySet()) {
            if (i > max) {
                max = i;
            }
        }
        return max + 1;
    }

    public void writeCustomer(String fn, String ln, String phone, String add, BigDecimal tax, int id) throws IOException {
        customer = new Customer(fn, ln, phone, add, tax, id);
        Main.customers.put(id, customer);

        FileOutputStream f = new FileOutputStream("Customer.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.customers);
        o.flush();
        f.close();
        o.close();
    }

    // Modifies a customer's existing fields
    public void modifyCustomer(Customer customer) throws IOException {
        Main.customers.put(customer.getCustomerID(), customer);
        FileOutputStream f = new FileOutputStream("Customer.txt");
        ObjectOutputStream o = new ObjectOutputStream(f);

        o.writeObject(Main.customers);
        o.flush();
        f.close();
        o.close();
    }

    // Loads the hashmap in Customer.txt and places it in the hashtable in the Main
    public void getCustomers() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("Customer.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);

        Main.customers = (HashMap<Integer, Customer>) oi.readObject();
        oi.close();
        fi.close();
    }

    public void displayCustomers() {
        for (Customer c : Main.customers.values()) {
            System.out.println(c.toString());
        }
    }

    public void searchCustomerID(int id) {
        try{
            System.out.println(Main.customers.get(id).toString());}
        catch (Exception e){
            System.out.println("No customer with given ID was found");
        }
    }

    public void changeStatusToFalse(Customer c) throws IOException {
        c.setStatus(false);
        if(Main.customers.containsKey(c.getCustomerID())){
        modifyCustomer(c);
        }
    }

    public void changeStatusToTrue(Customer c) throws IOException {
        c.setStatus(true);
        if(Main.customers.containsKey(c.getCustomerID())){
            modifyCustomer(c);
        }
    }
}
