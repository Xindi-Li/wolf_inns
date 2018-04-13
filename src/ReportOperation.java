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
		String pattern = "[0-9]+";
		String patternForCategory = "[1-3]+";
		String patternForDate = "\\d{4}-\\d{2}-\\d{2}";

		while (true) {
			System.out.println("Operation No:");
			printRoomMethodMenu();

			input = sc.next();
			switch (input) {
				case "1":
					while (true) {
						System.out.println("Please input hotel ID:");
						input = sc.next();
						if (Pattern.matches(pattern, input)){
							System.out.println(getNumberOfRoom(Integer.valueOf(input)));
							break;
						}
						else System.out.println("Your input is illegal");
					}
				case "2":
					while (true) {
						System.out.println("Please input room type:");
						printRoomType();
						input = sc.next();
						if (Pattern.matches(patternForCategory, input)){
							System.out.println(getNumberOfRoom(getCategory(Integer.valueOf(input))));
							break;
						}
						else System.out.println("Your input is illegal");
					}
				case "3":
				    while (true) {
				    	System.out.println("Please input start date:");
				    	input = sc.next();
				    	if (Pattern.matches(patternForDate, input)){
				    		String start_date = input;
				    		System.out.println("Please input end date:");
				    		input = sc.next();
				    		if (Pattern.matches(patternForDate, input)){
				    			System.out.println(getNumberOfRoom(start_date, input));
				    			break;
				    		}
				    		else{
				    			System.out.println("Your input is illegal.");
				    		}
				    	}else {
				    		System.out.println("Your input is illegal.");
				    	}
				    }
				default:
				    System.out.println("Your input is illegal.");
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


	public static int getNumberOfAvailableRoom(int id){
		return 1;
	}

	public static int getNumberOfRoom(int id){
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