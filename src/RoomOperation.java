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
        int hotel_ID = Integer.valueOf(input);

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
            if (Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float nightRate = Float.valueOf(input);

        while (true) {
            System.out.print("Availability: ");
            if (Pattern.matches(patternForBoolean, input)) break;
            else System.out.println("Your input is illegal");
        }
        int availability = Integer.valueOf(input);

        String sql = "insert into room(hotel_ID, room_number, room_category, max_allowed_occupancy, night_rate, availability) values(?,?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotel_ID);
            ptmt.setString(2, roomNumber);
            ptmt.setString(3, roomCategory);
            ptmt.setInt(4, maxAllowedOccupancy);
            ptmt.setFloat(5, nightRate);
            ptmt.setInt(6, availability);
            ptmt.execute();
            System.out.println("A new room has been entered!");
        } catch (SQLException e) {
            System.out.println("The ID of the hotel and room number do not exist. Creation failed.");
        }

    }

}
