package ca.hccis.backspin;

import ca.hccis.backspin.entity.Order;
import ca.hccis.backspin.util.CisUtility;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

/** Controls the program
 *
 * @author misha
 * @since 20260918
 *
 */
public class Controller {
    public static final int EXIT = 0;

    public static final String MENU = "1) Add\n2)View\n0) Exit";

    public static final String PATH_NAME = "c:\\cis2232\\data_nosachenko_misha.json";

    private static HashMap<Integer, Order> orderList = new HashMap<>();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        initialize();
        int menuOption;
        do {
            menuOption = CisUtility.getInputInt(MENU);
            switch (menuOption) {
                case EXIT:
                    System.out.println("Goodbye");
                    break;
                case 1:
                    add();
                    break;
                case 2:
                    viewAll();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (menuOption != EXIT);
    }

    public static void add() {
        Order order = new Order();
        System.out.println("--Add Order--");
        order.getInformation();
        if (orderList.containsKey(order.getOrderNumber())) {
            System.out.println("That order number already exists.");
            return;
        }
        orderList.put(order.getOrderNumber(), order);
        writeAll();
    }

    public static void viewAll() {
        readAll();
        for (Order order : orderList.values()) {
            System.out.println(order);
        }
    }

    public static void writeAll() {
        try (FileWriter writer = new FileWriter(PATH_NAME, false)) {
            for (Order order : orderList.values()) {
                writer.write(gson.toJson(order));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Could not save orders.");
        }
    }

    public static void readAll() {
        try {
            List<String> lines = Files.readAllLines(Path.of(PATH_NAME));
            orderList.clear();
            for (String line : lines) {
                Order order = gson.fromJson(line, Order.class);
                orderList.put(order.getOrderNumber(), order);
            }
        } catch (IOException e) {
            System.out.println("Could not read orders.");
        }
    }

    public static void initialize() {
        Path path = Path.of(PATH_NAME);

        try {
            // Create the cis2232 folder if it does not exist.
            Files.createDirectories(path.getParent());

            if (Files.exists(path)) {
                readAll();
            } else {
                // Create an empty orders.json file.
                writeAll();
            }
        } catch (IOException e) {
            System.out.println("Could not create the folder.");
        }
    }
}

