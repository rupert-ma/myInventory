import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * Error handling to add
 * If duplicate
 * If not the right type of entry
 * If too big a number
 *
 * Need to allow spaces in inputs
 */
// get user input to add, delete, or modify inventory contents
public class userInput extends getInv {
    userInput() {
        boolean x = true;
        while (x = true) {
            Scanner userSelection = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Enter A to add an item to the inventory");
            System.out.println("Enter D to delete an item from the inventory");
            System.out.println("Enter C to change the quantity of an item an item from the inventory");
            String userIn = userSelection.next();

            if (userIn.equalsIgnoreCase("A")) {
                System.out.println("Enter the item to add?");
                String newItem = userSelection.next();
                System.out.println("Enter the quantity for this item.");
                int newQty = userSelection.nextInt();

                try {
                    // Get Connection
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false", "test", "tests");
                    // Creates statement
                    String sql = "insert into inventory_list (inventory_item, inventory_amount) values (?, ?)";
                    PreparedStatement myStmt = myConn.prepareStatement(sql);

                    //Sets parameters
                    myStmt.setString(1, newItem);
                    myStmt.setInt(2, newQty);

                    //Exexcutes the SQL query
                    myStmt.executeUpdate();

                    //displays new Inventory
                    System.out.println("Here is the updated Inventory:");
                    new getInv();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

            if (userIn.equalsIgnoreCase("D")) {

                System.out.println("Enter the item to delete?");
                String delItem = userSelection.next();

                try {
                    // Get Connection
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false", "test", "tests");
                    // Creates statement
                    String sql = "delete from inventory_list where inventory_item = ?";
                    //Statement myStmt = myConn.createStatement();
                    PreparedStatement myStmt = myConn.prepareStatement(sql);

                    //Sets parameters
                    myStmt.setString(1, delItem);

                    //Executes the SQL query
                    myStmt.executeUpdate();


                    //displays new Inventory
                    System.out.println("Here is the updated Inventory:");
                    new getInv();
                }
                catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

            if (userIn.equalsIgnoreCase("C")) {
                System.out.println("Enter the item to change?");
                String selItm = userSelection.next();
                System.out.println("Enter the new Quantity");
                int chgQty = Integer.parseInt(userSelection.next());
                System.out.println(chgQty);

                try {
                    // Get Connection
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false", "test", "tests");
                    // Creates statement
                    String sql = "update inventory_list set inventory_amount=? where inventory_item=?";
                    //Statement myStmt = myConn.createStatement();
                    PreparedStatement myStmt = myConn.prepareStatement(sql);

                    //Sets parameters
                    myStmt.setInt(1, chgQty);
                    myStmt.setString(2, selItm);

                    //Executes the SQL query
                    myStmt.executeUpdate();

                    //displays new Inventory
                    System.out.println("Here is the updated Inventory:");
                    new getInv();
                }
                catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

            System.out.println("Would you like to Exit (Y/N) ");
            String exit = userSelection.next();

            if (exit.equalsIgnoreCase("Y")) {
                System.out.println("Thanks for using our system!");
                System.exit(0);
            }
        }
    }
}
