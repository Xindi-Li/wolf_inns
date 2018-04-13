import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;



public class RoomOperation {
    public static void enterRoom() {
        String input;
        String pattern = "[0-9]+";// for int pattern
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForBoolean = "[0-1]+";
        String patternForCategory = "[1-3]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("Room number: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        while (true) {
            System.out.println("Room Category: ");
            System.out.println("1. Single ");
            System.out.println("2. Deluxe ");
            System.out.println("3. Presidential ");
            input = sc.nextLine();
            if (Pattern.matches(patternForCategory, input)) break;
            else System.out.println("Your input is illegal");
        }
        String roomCategory = "";
        switch (input) {
            case "1":
                roomCategory = "Single";
                break;
            case "2":
                roomCategory = "Deluxe";
                break;
            case "3":
                roomCategory = "Presidential";
                break;
        }

        while (true) {
            System.out.print("Max allowed occupancy: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int maxAllowedOccupancy = Integer.valueOf(input);

        while (true) {
            System.out.print("Night rate: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern,input)||Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float nightRate = Float.valueOf(input);

        while (true) {
            System.out.println("Availability: ");
            System.out.println("1. Available. ");
            System.out.println("2. Not available. ");
            input = sc.nextLine();
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
            System.out.println(e.getMessage());
        }
    }
    public static void updateRoom() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForBoolean = "[0-1]+";
        String patternForCategory = "[1-3]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("Room number: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        while (true) {
            System.out.println("Room Category: ");
            System.out.println("1. Single ");
            System.out.println("2. Deluxe ");
            System.out.println("3. Presidential ");
            input = sc.nextLine();
            if (Pattern.matches(patternForCategory, input)) break;
            else System.out.println("Your input is illegal");
        }
        String roomCategory = "";
        switch (input) {
            case "1":
                roomCategory = "Single";
                break;
            case "2":
                roomCategory = "Deluxe";
                break;
            case "3":
                roomCategory = "Presidential";
                break;
        }

        while (true) {
            System.out.print("Max allowed occupancy: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int maxAllowedOccupancy = Integer.valueOf(input);

        while (true) {
            System.out.print("Night rate: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern,input)||Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float nightRate = Float.valueOf(input);

        while (true) {
            System.out.println("Availability: ");
            System.out.println("1. Available. ");
            System.out.println("2. Not available. ");
            input = sc.nextLine();
            if (Pattern.matches(patternForBoolean, input)) break;
            else System.out.println("Your input is illegal");
        }
        int availability = Integer.valueOf(input);

        String sql = "UPDATE room SET room_category = ?, max_allowed_occupancy = ?, night_rate = ?, availability = ? WHERE hotel_ID = ? AND room_number = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, roomCategory);
            ptmt.setInt(2, maxAllowedOccupancy);
            ptmt.setFloat(3, nightRate);
            ptmt.setInt(4, availability);
            ptmt.setInt(5, hotelID);
            ptmt.setString(6, roomNumber);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The room has been updated!");
            } else {
                System.out.println("The room does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteRoom() {
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
            System.out.print("Room number: ");
            input = sc.nextLine();
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
            System.out.println(e.getMessage());
        }
    }
    public static void checkRoomIsAvailableWithRoomNumber() {
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
            System.out.print("Room number: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        String sql = "SELECT availability FROM room WHERE hotel_ID = ? AND room_number = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            ptmt.setString(2, roomNumber);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                boolean availability = rs.getBoolean("availability");
                if(availability == true) System.out.println("availability: yes");
                else System.out.println("availability: no");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void checkRoomIsAvailableWithRoomCategory() {
        String input;
        String pattern = "[0-9]+";
        String patternForCategory = "[1-3]+";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.println("Room Category: ");
            System.out.println("1. Single ");
            System.out.println("2. Deluxe ");
            System.out.println("3. Presidential ");
            input = sc.nextLine();
            if (Pattern.matches(patternForCategory, input)) break;
            else System.out.println("Your input is illegal");
        }
        String roomCategory = "";
        switch (input) {
            case "1":
                roomCategory = "Single";
                break;
            case "2":
                roomCategory = "Deluxe";
                break;
            case "3":
                roomCategory = "Presidential";
                break;
        }

        String sql = "SELECT hotel_ID, room_number, availability FROM room WHERE hotel_ID = ? AND room_category = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            ptmt.setString(2, roomCategory);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                int hotel_ID = rs.getInt("hotel_ID");
                String room_number = rs.getString("room_number");
                boolean availability = rs.getBoolean("availability");
                System.out.println("=====================================");
                System.out.println("hotel_ID: " + hotel_ID);
                System.out.println("room_number: " + room_number);
                if(availability == true) System.out.println("availability: yes");
                else System.out.println("availability: no");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void releaseRoom() {
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
            System.out.print("Room number: ");
            input = sc.nextLine();
            if (!input.trim().equals("")) break;
        }
        String roomNumber = input;

        String sql = "UPDATE room SET availability = 1 WHERE hotel_ID = ? AND room_number = ?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotelID);
            ptmt.setString(2, roomNumber);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The room has been released!");
            }else{
                System.out.println("The room does not exist. Deletion failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void assignRooms() throws SQLException{
        Connection conn = DBconnection.getConnection();
        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);// set the transaction
            conn.setAutoCommit(false);
            String input;
            String pattern = "[0-9]+";
            String patternForDecimal = "[0-9]+.[0-9]+";
            String patternForCategory = "[1-3]+";
            String patternForDate = "\\d{4}-\\d{2}-\\d{2}";
            String patternForSSN = "^[0-9]{9}$";
            Scanner sc = new Scanner(System.in);

            // select room for the requests
            while (true) {
                System.out.println("Room Category: ");
                System.out.println("1. Single ");
                System.out.println("2. Deluxe ");
                System.out.println("3. Presidential ");
                input = sc.nextLine();
                if (Pattern.matches(patternForCategory, input)) break;
                else System.out.println("Your input is illegal");
            }
            String roomCategory = "";
            switch (input) {
                case "1":
                    roomCategory = "Single";
                    break;
                case "2":
                    roomCategory = "Deluxe";
                    break;
                case "3":
                    roomCategory = "Presidential";
                    break;
            }
            while (true) {
                System.out.print("Number of guests: ");
                input = sc.nextLine();
                if (Pattern.matches(pattern, input)) break;
                else System.out.println("Your input is illegal");
            }
            int numberOfGuests = Integer.valueOf(input);

            while (true) {
                System.out.print("Maximum night rate: ");
                input = sc.nextLine();
                if (Pattern.matches(patternForDecimal, input)) break;
                else System.out.println("Your input is illegal");
            }
            float nightRate = Float.valueOf(input);

            String sql = "SELECT hotel_ID, room_number, room_category, max_allowed_occupancy, night_rate  FROM room WHERE availability = 1 AND room_category = ? AND max_allowed_occupancy >= ? AND night_rate <= ?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, roomCategory);
                ptmt.setInt(2, numberOfGuests);
                ptmt.setFloat(3, nightRate);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    int hotel_ID = rs.getInt("hotel_ID");
                    String room_number = rs.getString("room_number");
                    String room_category = rs.getString("room_category");
                    int max_allowed_occupancy = rs.getInt("max_allowed_occupancy");
                    float night_rate = rs.getFloat("night_rate");
                    System.out.println("=====================================");
                    System.out.println("hotel_ID: " + hotel_ID);
                    System.out.println("room_number: " + room_number);
                    System.out.println("roomCategory: " + room_category);
                    System.out.println("maxAllowedOccupancy: " + max_allowed_occupancy);
                    System.out.println("nightRate: " + night_rate);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //insert checkin table
            while (true) {
                System.out.print("customer SSN: ");
                input = sc.nextLine();
                if (Pattern.matches(patternForSSN, input)) break;
                else System.out.println("Your input is illegal");
            }
            String customer_ssn = input;

            while (true) {
                System.out.print("Hotel ID: ");
                input = sc.nextLine();
                if (Pattern.matches(pattern, input)) break;
                else System.out.println("Your input is illegal");
            }
            int hotelID = Integer.valueOf(input);

            while (true) {
                System.out.print("Room number: ");
                input = sc.nextLine();
                if (!input.trim().equals("")) break;
            }
            String roomNumber = input;

            Calendar c = Calendar.getInstance();
            c.setTime(new Date()); // Now use today date.
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // set date format
            while (true) {
                System.out.print("How many days staying: ");
                input = sc.nextLine();
                if (Pattern.matches(pattern, input)) {
                    c.add(Calendar.DATE, Integer.parseInt(input)); // Adds input days
                    break;
                } else System.out.println("Your input is illegal");
            }
            String checkOutDate = df.format(c.getTime()); // transform to checkout date to string

            java.util.Date date = new Date();// for checkin time, use today's date

            String sql_2 = "INSERT INTO checkin(customer_SSN, hotel_ID, room_number, number_of_guests, start_date, end_date, checkin_time) values(?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql_2);
                ptmt.setString(1, customer_ssn);
                ptmt.setInt(2, hotelID);
                ptmt.setString(3, roomNumber);
                ptmt.setInt(4, numberOfGuests);
                ptmt.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
                ptmt.setString(6, checkOutDate);
                ptmt.setTimestamp(7, new java.sql.Timestamp(date.getTime()));
                ptmt.execute();
                System.out.println("Check-in table has been entered!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //assign dedicated_service_staff if it category is presidential
            if(roomCategory == "Presidential") {
                while (true) {
                    System.out.print("Dedicated service staff: ");
                    input = sc.nextLine();
                    if (Pattern.matches(pattern, input)) break;
                    else System.out.println("Your input is illegal");
                }
                int dedicated_service_staff_ID = Integer.valueOf(input);

                String sql_3 = "INSERT INTO dedicated_service_staff_serve(service_staff_ID, room_number, hotel_ID) values(?, ?, ?)";
                try {
                    PreparedStatement ptmt = conn.prepareStatement(sql_3);
                    ptmt.setInt(1, dedicated_service_staff_ID);
                    ptmt.setString(2, roomNumber);
                    ptmt.setInt(3, hotelID);
                    ptmt.execute();
                    System.out.println("Dedicated service staff has been assigned!");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            //update availability
            String sql_4 = "UPDATE room SET availability = 0 WHERE hotel_ID = ? AND room_number = ?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql_4);
                ptmt.setInt(1, hotelID);
                ptmt.setString(2, roomNumber);
                int count = ptmt.executeUpdate();
                if (count > 0) {
                    System.out.println("The availability of the room has been updated!");
                } else {
                    System.out.println("The room does not exist. Update failed");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            //doing bill generate, check the return value, if false, rollback
            if(!BillOperation.generate(customer_ssn)) {
                System.out.println("Bill generate failed! Start rolling back!");
                conn.rollback();
            }
            else {
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
