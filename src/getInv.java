import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class getInv {
    Statement myStmt;

    getInv() {
        // display inventory contents
        try {
            // Get Connection
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useSSL=false", "test", "tests");
            // Create a Statement
            Statement myStmt = myConn.createStatement();

            // Submit SQL query -
            ResultSet myRs = myStmt.executeQuery("select * from inventory_list");
            System.out.println("Item, Qty");

            // Process result set
            while ((myRs.next())) {
                System.out.println(myRs.getString("inventory_item") + ", " + myRs.getString("inventory_amount"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

