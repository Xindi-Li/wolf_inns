import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        System.out.println("Welcome to Wolf Inns Database System");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. Maintaining billing accounts");
        System.out.println("4. Reports");
        DBconnection.initialize();
        Statement statement = DBconnection.getStatement();

        try {
            ResultSet set = statement.executeQuery("SELECT CNAME, WEIGHT FROM CATS");
            while (set.next()) {
                String name = set.getString("CNAME");
                float weight = set.getFloat("WEIGHT");
                System.out.println(name + "  " + weight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    System.out.println(input);
            }
        }
    }
}
