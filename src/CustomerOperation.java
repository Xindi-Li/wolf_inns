import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerOperation {
    public static void enterCustomer() {
        String input;
        String patternForDate = "\\d{4}-\\d{2}-\\d{2}";
        String patternForSSN = "^[0-9]{9}$";
        String patternForPhoneNumber = "^[0-9]{10}$";
        String patternForEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Customer Name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("SSN: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForSSN, input)) break;
            else System.out.println("Your input is illegal");
        }
        String ssn = input;

        while (true) {
            System.out.print("Date of Birth: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForDate, input)) break;
            else System.out.println("Your input is illegal");
        }
        String date_of_birth = input;

        while (true) {
            System.out.print("Phone Number: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForPhoneNumber, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("E-mail: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForEmail, input)) break;
            else System.out.println("Your input is illegal");
        }
        String email = input;

        while (true) {
            System.out.print("billing_address: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String bill_add = input;

        String sql = "insert into customer(ssn, customer_name, date_of_birth, phone, email, billing_address) values(?,?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ssn);
            ptmt.setString(2, name);
            ptmt.setString(3, date_of_birth);
            ptmt.setString(4, phone_number);
            ptmt.setString(5, email);
            ptmt.setString(6, bill_add);
            ptmt.execute();
            System.out.println("A new customer has been entered!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateCustomer() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Customer Name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("SSN: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String ssn = input;

        while (true) {
            System.out.print("Date of Birth: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String date_of_birth = input;

        while (true) {
            System.out.print("Phone Number: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String phone_number = input;

        while (true) {
            System.out.print("E-mail: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String email = input;

        while (true) {
            System.out.print("billing_address: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String bill_add = input;


        String sql = "update customer set customer_name=?, date_of_birth=?, phone=?, email=?, billing_address=? where ssn = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, date_of_birth);
            ptmt.setString(3, phone_number);
            ptmt.setString(4, email);
            ptmt.setString(5, bill_add);
            ptmt.setString(6, ssn);
            int count = ptmt.executeUpdate();
            // System.out.println("customer ");
            if (count > 0) {
                System.out.println("The customer has been updated!");
            } else {
                System.out.println("The customer does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteCustomer() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Customer SSN: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        int customerSSN = Integer.valueOf(input);

        String sql = "delete from customer where ssn = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, customerSSN);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The customer has been deleted!");
            } else {
                System.out.println("The customer does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}