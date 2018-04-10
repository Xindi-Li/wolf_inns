import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by JamesLee on 2018/4/10.
 */
public class ServiceOperration {
    public static void enterService() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("checkin_ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int checkin_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("staff ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int staff_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("Service name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String service_name = input;

        while (true) {
            System.out.print("Price: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input) || Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float price = Float.valueOf(input);

        String sql = "insert into service(checkin_ID, staff_ID, service_name, price) values(?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, checkin_ID);
            ptmt.setInt(2, staff_ID);
            ptmt.setString(3, service_name);
            ptmt.setFloat(4, price);
            ptmt.execute();
            System.out.println("A new service has been entered!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateService() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("service_ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int service_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("checkin_ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int checkin_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("staff ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int staff_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("Service name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String service_name = input;

        while (true) {
            System.out.print("Price: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input) || Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float price = Float.valueOf(input);

        String sql = "UPDATE service SET checkin_ID=?, staff_ID=?, service_name=?, price=? WHERE service_ID=?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, checkin_ID);
            ptmt.setInt(2, staff_ID);
            ptmt.setString(3, service_name);
            ptmt.setFloat(4, price);
            ptmt.setInt(5, service_ID);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The service has been updated!");
            } else {
                System.out.println("The service does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
