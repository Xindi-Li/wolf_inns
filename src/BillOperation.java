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


public class BillOperation{
    public static void generate() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForCategory = "[1-3]+";
        Scanner sc = new Scanner(System.in);
        int billID = 0;
        int checkinID = 0;
        while (true) {
            System.out.print("SSN of customer: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String SSN = input;
        
        
       
        while (true) {
            System.out.print("payment method: \n");
            System.out.println("1. credit card");
            System.out.println("2. cash");
            System.out.println("3. hotel credit card");
            input = sc.nextLine();
            if (Pattern.matches(patternForCategory, input)) break;
            else System.out.println("Your input is illegal"); 
        }
            String output = "";
            switch(input){
                case "1":
                    output = "credit card";
                    break;
                case "2":
                    output = "cash";
                    break;
                case "3":
                    output = "hotel credit card";
                    break;
                default:
                    System.out.println("your input is illegal");   
                    break;        
            }
       
    String cardnumber = "";    
    if(input.equals("1")||input.equals("3")){
        while (true) {
            System.out.print("card number: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        cardnumber = input;
    }

        while (true) {
            System.out.print("Hotel ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int hotelID = Integer.valueOf(input);

        while (true) {
            System.out.print("The room number is: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        String roomNo = input;    

        while (true) {
            System.out.print("number of guests: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int guestNo = Integer.valueOf(input);  
        
        
        while (true) {
            System.out.print("The end date is: \n");
            System.out.print("The format should be: yyyy-MM-dd\n");
            input = sc.nextLine();
            break;
          }  
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
            Date enddate = null;
            try{
                enddate = formatter.parse(input);
            }catch(ParseException e){
                e.printStackTrace();
            }
        
        
       
        
        String sql = "insert into checkin(customer_SSN, hotel_ID, room_number, number_of_guests, start_date, end_date, checkin_time, checkout_time) values(?,?,?,?,?,?,?,?)";
        Connection conn = DBconnection.getConnection();
        try {
            
            Date startdate = new Date();
            java.util.Date date = new Date();
            PreparedStatement ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, SSN);
            ptmt.setInt(2, hotelID);
            ptmt.setString(3, roomNo);
            ptmt.setInt(4, guestNo);
            ptmt.setDate(5, new java.sql.Date(formatter.parse(formatter.format(startdate)).getTime()));
            ptmt.setDate(6, new java.sql.Date(enddate.getTime()));
            ptmt.setTimestamp(7, new java.sql.Timestamp(date.getTime()));
            ptmt.setNull(8, java.sql.Types.TIMESTAMP);
            ptmt.execute();
            System.out.println("A checkin information has been generated!");
            ResultSet rs = ptmt.getGeneratedKeys(); 
            if(rs.next()){
                checkinID = rs.getInt(1);
            }
        } catch (SQLException e1 ) {
            System.out.println(e1.getMessage());
        } catch(ParseException e2){
                e2.printStackTrace();
        }

        float price =  0.0f;
        String sql1 = "select night_rate from room join checkin On room.room_number = checkin.room_number and room.hotel_ID = checkin.hotel_ID where checkin.checkin_ID  = ?";
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql1);
            Statement ptmt1 = conn.createStatement();
            ptmt.setInt(1,checkinID);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
               price = rs.getFloat("night_rate");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    try{
        Date startdate = new Date();
        long daydiff = (enddate.getTime()-formatter.parse(formatter.format(startdate)).getTime())/(24*60*60*1000);
        float total = price * daydiff;
        String sql2 = "insert into billing(SSN_of_the_person_responsible_for_the_payment, payment_method, card_number, price) values(?,?,?,?)";
        try {
            
            PreparedStatement ptmt = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, SSN);
            ptmt.setString(2, output);
            ptmt.setString(3, cardnumber);
            ptmt.setFloat(4, total);
            ptmt.execute();
            System.out.println("A billing information has been generated!");
            ResultSet rs = ptmt.getGeneratedKeys(); 
            if(rs.next()){
                billID = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }catch(ParseException e){
            e.printStackTrace();
        }
        String sql3 = "insert into billing_checkin(bill_ID,checkin_ID) values(?,?)";
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql3);
            ptmt.setInt(1,billID);
            ptmt.setInt(2,checkinID);
            System.out.println("A billing_checkin information has been generated!");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    }

}  