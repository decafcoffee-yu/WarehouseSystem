import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WarehouseBoundary {
    WarehouseController warehouseController;

    public WarehouseBoundary(WarehouseController warehouseController) {
        this.warehouseController = warehouseController;
    }

    public void showWarehouseUI () throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);

        if (new File("Warehouse.txt").exists()) {
            warehouseController.getWarehouses();
        }

        sop("Warehouse");
        sop("""
                1. Add warehouse
                2. Show quantity for each product
                3. Return to main menu""");
        int choice2 = input.nextInt();
        switch (choice2) {
            case 1 -> {
                // load up warehouses, since were adding a new one
                if (new File("Warehouse.txt").exists()) {
                    warehouseController.getWarehouses();
                }

                Scanner scanner = new Scanner(System.in);
                sop("Adding warehouse\n");
                Warehouse wh;
                sop("Name of warehouse: ");
                String name = scanner.nextLine();
                sop("Address: ");
                String address = scanner.nextLine();
                sop("City: ");
                String city = scanner.nextLine();
                sop("State: ");
                String state = scanner.nextLine();
                sop("Zip: ");
                String zip = scanner.nextLine();
                sop("Phone number: ");
                String phoneNumber = scanner.nextLine();

                warehouseController.writeWarehouse(name, address, city, state, zip, phoneNumber);

                sop("New warehouse added..");
            }
            case 2 -> {
                if (new File("Warehouse.txt").exists()) {
                    warehouseController.getWarehouses();

                    Scanner scanner = new Scanner(System.in);
                    sop("Enter warehouse's name: ");
                    String warehouse = scanner.nextLine();
                    if (Main.warehouses.containsKey(warehouse)) {
                        Warehouse foundWareHouse = Main.warehouses.get(warehouse);
                        warehouseController.showQuantity(foundWareHouse);
                    }
                    else {
                        sop("Warehouse not found");
                    }
                }
                else {
                    sop("No warehouses exist!");
                }
            }
        }
    }
    private static void sop(String s){
        System.out.println(s);
    }


}
