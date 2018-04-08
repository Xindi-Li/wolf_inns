import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerOperation {
	public static void enterCustomer() {
		String input;
        Scanner sc = new Scanner(System.in);

        while(true) {
        	System.out.print("Customer Name: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while(true) {
        	System.out.print("SSN: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String ssn = input;

        while(true) {
        	System.out.print("Date of Birth: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String date_of_birth = input;

        while(true) {
        	System.out.print("Phone Number: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String phone_number = input;

        while(true) {
        	System.out.print("E-mail: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String email = input;

        String sql = "insert into customer(ssn, customer_name, date_of_birth, phone, email) values(?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ssn);
            ptmt.setString(2, name);
            ptmt.setString(3, date_of_birth);
            ptmt.setString(4, phone_number);
            ptmt.setString(5, email);
            ptmt.execute();
            System.out.println("A new customer has been entered!");
        } catch (SQLException e) {
            System.out.println("Customer creation failed. Please try again.");
        }
	}

	public static void updateCustomer() {
		String input;
		Scanner sc = new Scanner(System.in);

        while(true) {
        	System.out.print("Customer Name: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while(true) {
        	System.out.print("SSN: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String ssn = input;

        while(true) {
        	System.out.print("Date of Birth: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String date_of_birth = input;

        while(true) {
        	System.out.print("Phone Number: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String phone_number = input;

        while(true) {
        	System.out.print("E-mail: ");
        	input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String email = input;

        String sql = "update customer set customer_name=?, date_of_birth=?, phone=?, email=? where ssn = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, date_of_birth);
            ptmt.setString(3, phone_number);
            ptmt.setString(4, email);
            ptmt.setString(5, ssn);
            int count = ptmt.executeUpdate();
            // System.out.println("customer ");
            if (count > 0) {
                System.out.println("The customer has been updated!");
            } else {
                System.out.println("The customer does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println("Customer update failed. Please try again.");
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
            }else{
                System.out.println("The customer does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println("Customer deletion failed. Please try again.");
        }
	}
}