import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HotelOperation {
    public static void enterHotel() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("Hotel address: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String address = input;

        while (true) {
            System.out.print("Hotel phone number: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("ID of hotel manager: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int idOfManager = Integer.valueOf(input);

        while (true) {
            System.out.print("City: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String city = input;

        String sql = "insert into hotel(hotel_name, hotel_address, hotel_phone_number, ID_of_hotel_manager, city) values(?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, address);
            ptmt.setString(3, phone_number);
            ptmt.setInt(4, idOfManager);
            ptmt.setString(5, city);
            ptmt.execute();
            System.out.println("A new hotel has been entered!");
        } catch (SQLException e) {
            System.out.println("The ID of the hotel manager does not exist. Creation failed.");
        }
    }

    public static void updateHotel() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("Hotel name: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("Hotel address: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String address = input;

        while (true) {
            System.out.print("Hotel phone number: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("ID of hotel manager: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int idOfManager = Integer.valueOf(input);

        while (true) {
            System.out.print("City: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String city = input;

        String sql = "update hotel set hotel_name=?, hotel_address=?, hotel_phone_number=?, ID_of_hotel_manager=?, city=? where hotel_ID = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, address);
            ptmt.setString(3, phone_number);
            ptmt.setInt(4, idOfManager);
            ptmt.setString(5, city);
            ptmt.setInt(6, hotelID);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The hotel has been updated!");
            } else {
                System.out.println("The hotel does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println("The ID of the hotel manager does not exist. Update failed.");
        }
    }

    public static void deleteHotel() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        String sql = "delete from hotel where hotel_ID = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The hotel has been deleted!");
            }else{
                System.out.println("The hotel does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println("Deletion failed.");
        }

    }
}
