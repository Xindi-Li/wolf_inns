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
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String name = input;

        while (true) {
            System.out.print("Hotel address: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String address = input;

        while (true) {
            System.out.print("Hotel phone number: ");
            input = sc.next();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String phone_number = input;

        while (true) {
            System.out.print("ID of hotel manager: ");
            input = sc.next();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int idOfManager = Integer.valueOf(input);

        while (true) {
            System.out.print("City: ");
            input = sc.next();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
