import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException; 
import java.math.*;

public class ReportOperation{

	public static void getNumberOfRoom() {
		Scanner sc = new Scanner(System.in);
		String input;
		String choice;
		String pattern = "[0-9]+";
		String patternForCategory = "[1-3]+";
		String patternForDate = "\\d{4}-\\d{2}-\\d{2}";

		while (true) {
			
			printRoomMethodMenu();
			System.out.print("Operation No:");

			input = sc.next();
			switch (input) {
				case "1":
					
					System.out.print("Please input hotel ID:");
					choice = sc.next();
					if (Pattern.matches(pattern, choice)){
						System.out.println(getNumberOfRoomByHotelID(choice));
						break;
					}
					else {
						System.out.println("Your input is illegal");
						break;
					}
					
				case "2":
					
					printRoomType();
					System.out.print("Please input room type:");
					
					choice = sc.next();
					if (Pattern.matches(patternForCategory, choice)){
						System.out.println(getNumberOfRoom(getCategory(Integer.valueOf(choice))));
						break;
					}
					else {
						System.out.println("Your input is illegal");
						break;
					}
					
				case "3":
				    
			    	System.out.print("Please input start date:");
			    	choice = sc.next();
			    	if (Pattern.matches(patternForDate, choice)){
			    		String start_date = choice;
			    		System.out.println("Please input end date:");
			    		choice = sc.next();
			    		if (Pattern.matches(patternForDate, choice)){
			    			System.out.println(getNumberOfRoom(start_date, choice));
			    			break;
			    		}
			    		else{
			    			System.out.println("Your input is illegal.");
			    			break;
			    		}
			    	}else {
			    		System.out.println("Your input is illegal.");
			    		break;
			    	}
				    
				default:
				    System.out.println("Your input is illegal.");
				    break;
			}
		}
	}

	public static void getNumberOfAvailableRoom(){
		return;
	}

	private static void printRoomType(){
		System.out.println("=====================================");
        System.out.println("1. Single");
        System.out.println("2. Deluxe");
        System.out.println("3. Presidential");
        System.out.println("====================================="); 
	}

	private static String getCategory(int i) {
		switch (i) { 
			case 1:
			    return "Single";
			case 2:
			    return "Deluxe";
			case 3:
			    return "Presidential";
			default:
			    System.out.println("illegal input.");
			    return " ";
	    }
	}

	private static void printRoomMethodMenu() {
		System.out.println("=====================================");
        System.out.println("1. Select number of room in a hotel");
        System.out.println("2. Select number of room of certain type");
        System.out.println("3. Select number of room in a period");
        System.out.println("====================================="); 
	}


	public static int getNumberOfRoomByHotelID(String id){
		String sql = "select count(*) as total from room where hotel_ID = ?";
		Connection conn = DBconnection.getConnection();
		try {
			System.out.println("int try segment");
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, id);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("get ResultSet");
            System.out.println(rs.getInt("total"));
            return rs.getInt("total");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return -1;
	}

	public static int getNumberOfAvailableRoom(int id){
		return 1;
	}

	public static int getNumberOfAvailableRoom(String str){
		return 2;
	}

	public static int getNumberOfRoom(String str){
		return 2;
	}

	// public static int getNumberOfAvailableRoom(String str)

	public static int getNumberOfAvailableRoom(String begin, String end){
		return 3;
	}

	public static int getNumberOfRoom(String begin, String end){
		return 3;
	}
}