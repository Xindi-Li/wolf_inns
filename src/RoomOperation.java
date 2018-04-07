import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RoomOperation {
    public static void enterRoom() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForBoolean = "[0-1]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.next();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("Room number: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        while (true) {
            System.out.print("Room Category: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String roomCategory = input;

        while (true) {
            System.out.print("Max allowed occupancy: ");
            input = sc.next();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int maxAllowedOccupancy = Integer.valueOf(input);

        while (true) {
            System.out.print("Night rate: ");
            input = sc.next();
            if (Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float nightRate = Float.valueOf(input);

        while (true) {
            System.out.print("Availability: ");
            input = sc.next();
            if (Pattern.matches(patternForBoolean, input)) break;
            else System.out.println("Your input is illegal");
        }
        int availability = Integer.valueOf(input);

        String sql = "insert into room(hotel_ID, room_number, room_category, max_allowed_occupancy, night_rate, availability) values(?,?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            ptmt.setString(2, roomNumber);
            ptmt.setString(3, roomCategory);
            ptmt.setInt(4, maxAllowedOccupancy);
            ptmt.setFloat(5, nightRate);
            ptmt.setInt(6, availability);
            ptmt.execute();
            System.out.println("A new room has been entered!");
        } catch (SQLException e) {
            System.out.println("The ID of the hotel does not exist. Creation failed.");
        }
    }
    public static void deleteRoom() {
        String input;
        String pattern = "[0-9]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.next();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("Room number: ");
            input = sc.next();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        String sql = "delete from room where hotel_ID = ? and room_number = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            ptmt.setString(2, roomNumber);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The room has been deleted!");
            }else{
                System.out.println("The room does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println("The ID of the hotel manager does not exist. Deletion failed.");
        }

    }

}
