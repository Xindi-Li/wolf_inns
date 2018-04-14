import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StaffOperation {
    public static void enterStaff() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Staff Name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("Staff Age: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int ageOfstaff = Integer.valueOf(input);

        while (true) {
            System.out.print("Staff Job Title: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String jobTitle = input;

        while (true) {
            System.out.print("Department: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String department = input;

        while (true) {
            System.out.print("Staff phone number: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("Staff Address: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String address = input;

        while (true) {
            System.out.print("ID of hotel currently serving: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int idOfHotel = Integer.valueOf(input);


        String sql = "insert into staff(name, age, job_title, phone_number, address, hotel_ID_currently_serving, department) values(?,?,?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setInt(2, ageOfstaff);
            ptmt.setString(3, jobTitle);
            ptmt.setString(4, phone_number);
            ptmt.setString(5, address);
            ptmt.setInt(6, idOfHotel);
            ptmt.setString(7, department);
            ptmt.execute();
            System.out.println("A new staff has been entered!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void updateStaff() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Staff ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int staffID = Integer.valueOf(input);

        while (true) {
            System.out.print("Staff Name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("Staff Age: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int ageOfstaff = Integer.valueOf(input);

        while (true) {
            System.out.print("Staff Job Title: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String jobTitle = input;

        while (true) {
            System.out.print("Staff phone number: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("Staff Address: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String address = input;

        while (true) {
            System.out.print("ID of hotel currently serving: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int idOfHotel = Integer.valueOf(input);

        String sql = "update staff set name=?, age=?, job_title=?, phone_number=?, address=?, hotel_ID_currently_serving=? where staff_ID = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setInt(2, ageOfstaff);
            ptmt.setString(3, jobTitle);
            ptmt.setString(4, phone_number);
            ptmt.setString(5, address);
            ptmt.setInt(6, idOfHotel);
            ptmt.setInt(7, staffID);
            ptmt.execute();
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The staff has been updated!");
            } else {
                System.out.println("The staff does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void deleteStaff() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Staff ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int staffID = Integer.valueOf(input);

        String sql = "delete from staff where staff_ID = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, staffID);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The staff has been deleted!");
            } else {
                System.out.println("The staff does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
