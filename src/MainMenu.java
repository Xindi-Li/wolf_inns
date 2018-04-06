import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        printItem();
        //DBconnection.initialize();
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    MenuItem.infoProcessing();
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    public static void printItem(){
        System.out.println("Welcome to Wolf Inns Database System");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. Maintaining billing accounts");
        System.out.println("4. Reports");
        System.out.println("5. Exit");
    }
}
