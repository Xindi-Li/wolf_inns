import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReportOperation {

    public static void getOccupancyByHotel() {
        String sql = "select hotel_ID, count(*) as occupancy from room WHERE availability = 0 GROUP BY hotel_ID";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("=====================================");
            while (rs.next()) {
                int id = rs.getInt(1);
                int occupancy = rs.getInt(2);
                System.out.println("Hotel ID: " + id + ", # of occupied rooms: " + occupancy);
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getOccupancyByRoomType() {
        String sql = "select room_category, count(*) as occupancy from room where availability = 0 GROUP BY room_category";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("=====================================");
            while (rs.next()) {
                String category = rs.getString(1);
                int occupancy = rs.getInt(2);
                System.out.println("Room category: " + category + ", # of occupied rooms: " + occupancy);
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getOccupancyByDateRange() {
        Scanner sc = new Scanner(System.in);
        String choice;
        String patternForDate = "\\d{4}-\\d{2}-\\d{2}";

        while (true) {
            System.out.print("Please input start date(yyyy-mm-dd):");
            choice = sc.nextLine();
            if (Pattern.matches(patternForDate, choice)) break;
        }
        String startDate = choice;

        while (true) {
            System.out.print("Please input end date(yyyy-mm-dd):");
            choice = sc.nextLine();
            if (Pattern.matches(patternForDate, choice)) break;
        }
        String endDate = choice;

        String sql = "select count(*) from checkin where start_date>=? and end_date<=?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, startDate);
            ptmt.setString(2, endDate);
            ResultSet rs = ptmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Record does not exist");
            } else {
                System.out.println(rs.getInt(1) + " rooms are occupied.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getOccupancyByCity() {
        String sql = "select city, count(*) from room, hotel where room.hotel_ID = hotel.hotel_ID and availability=0 group by city;";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("=====================================");
            while (rs.next()) {
                String city = rs.getString(1);
                int occupancy = rs.getInt(2);
                System.out.println("City: " + city + ", # of occupied rooms: " + occupancy);
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getTotalOccupancyAndPercentage() {
        String sql1 = "select count(*) from room";
        String sql2 = "select count(*) from room where availability=0";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt;
            ResultSet rs;
            ptmt = conn.prepareStatement(sql1);
            rs = ptmt.executeQuery();
            rs.next();
            Integer total = rs.getInt(1); //get total number of rooms
            ptmt = conn.prepareStatement(sql2);
            rs = ptmt.executeQuery();
            rs.next();
            Integer occupied = rs.getInt(1); //get the number of occupied rooms
            System.out.println("Total occupied room: " + occupied);
            System.out.println("Percentage: " + 1.0 * occupied / total * 100 + "%");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getStaffsByRole() {
        String sql = "select * from staff order by job_title";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("=====================================");
            System.out.println("staff_ID  |  name  |  age  |         job_title        |   phone_number  |               address               |  hotel_ID_currently_serving  |  department");
            while (rs.next()) {
                int id = rs.getInt("staff_ID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String job_title = rs.getString("job_title");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                int hotel_ID_currently_serving = rs.getInt("hotel_ID_currently_serving");
                String department = rs.getString("department");

                System.out.printf("%-10d| %-7s| %-6d| %-25s| %-16s| %-36s| %-29d| %-11s\n", id, name, age, job_title, phone_number, address, hotel_ID_currently_serving, department);
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getServingStaffs() {
        Scanner sc = new Scanner(System.in);
        String input;
        String patternForSSN = "[0-9]+";
        while (true) {
            System.out.print("SSN of customer: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForSSN, input)) break;
            else System.out.println("Your input is illegal");
        }
        String SSN = input;
        String sql = "select * from staff where staff_ID in (select staff_ID from service where checkin_ID = (select checkin_ID from checkin where customer_SSN = ? order by checkin_ID DESC limit 1))";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, SSN);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("=====================================");
            System.out.println("staff_ID  |  name  |  age  |         job_title        |   phone_number  |               address               |  hotel_ID_currently_serving  |  department");
            while (rs.next()) {
                int id = rs.getInt("staff_ID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String job_title = rs.getString("job_title");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                int hotel_ID_currently_serving = rs.getInt("hotel_ID_currently_serving");
                String department = rs.getString("department");

                System.out.printf("%-10d| %-7s| %-6d| %-25s| %-16s| %-36s| %-29d| %-11s\n", id, name, age, job_title, phone_number, address, hotel_ID_currently_serving, department);
            }
            System.out.println("=====================================");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getRevenue() {
        Scanner sc = new Scanner(System.in);
        String input;
        String pattern = "[0-9]+";
        String patternForDate = "\\d{4}-\\d{2}-\\d{2}";
        while (true) {
            System.out.print("Please input hotel_ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotel_ID = Integer.valueOf(input);

        while (true) {
            System.out.print("Please input start date(yyyy-mm-dd):");
            input = sc.nextLine();
            if (Pattern.matches(patternForDate, input)) break;
        }
        String startDate = input;

        while (true) {
            System.out.print("Please input end date(yyyy-mm-dd):");
            input = sc.nextLine();
            if (Pattern.matches(patternForDate, input)) break;
        }
        String endDate = input;

        String sql = "select SUM(price) from (select * from billing_checkin as a natural join (select bill_ID, price from billing) as b natural join (select checkin_ID from checkin where start_date>=? and end_date<=? and hotel_ID = ?) as c) as d";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, startDate);
            ptmt.setString(2, endDate);
            ptmt.setInt(3, hotel_ID);
            ResultSet rs = ptmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Record does not exist");
            } else {
                System.out.println("Revenue: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}