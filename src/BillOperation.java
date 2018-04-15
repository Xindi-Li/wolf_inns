import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;


public class BillOperation {
    public static boolean generate(String... ssn) throws SQLException {
        boolean result = false;
        Connection conn = DBconnection.getConnection();
        try {
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);
            String input;
            String pattern = "[0-9]+";
            String patternForDecimal = "[0-9]+.[0-9]+";
            String patternForCategory = "[1-3]+";
            String patternForSSN = "[0-9]+";
            Scanner sc = new Scanner(System.in);
            int billID = 0;
            int checkinID = 0;
            String SSN;
            if (ssn.length == 0) {
                while (true) {
                    System.out.print("SSN of customer: ");
                    input = sc.nextLine();
                    if (Pattern.matches(patternForSSN, input)) break;
                    else System.out.println("Your input is illegal");
                }
                SSN = input;
            } else {
                SSN = ssn[0];
            }


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
            switch (input) {
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
            if (input.equals("1") || input.equals("3")) {
                while (true) {
                    System.out.print("card number: ");
                    input = sc.nextLine();
                    if (Pattern.matches(pattern, input)) break;
                    else System.out.println("Your input is illegal");
                }
                cardnumber = input;
            }

            String sql4 = "select checkin_ID from checkin where customer_SSN = ? order by checkin_ID DESC limit 1";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql4);
                ptmt.setString(1, SSN);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    checkinID = rs.getInt("checkin_ID");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startdate = null;
            Date enddate = null;
            String sql5 = "select start_date, end_date from checkin where checkin_ID = ?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql5);
                ptmt.setInt(1, checkinID);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    startdate = rs.getDate("start_date");
                    enddate = rs.getDate("end_date");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            float price = 0.0f;
            String sql1 = "select night_rate from room join checkin On room.room_number = checkin.room_number and room.hotel_ID = checkin.hotel_ID where checkin.checkin_ID  = ?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql1);
                ptmt.setInt(1, checkinID);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    price = rs.getFloat("night_rate");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            try {

                long daydiff = (enddate.getTime() - formatter.parse(formatter.format(startdate)).getTime()) / (24 * 60 * 60 * 1000);
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
                    if (rs.next()) {
                        billID = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String sql3 = "insert into billing_checkin(bill_ID,checkin_ID) values(?,?)";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql3);
                ptmt.setInt(1, billID);
                ptmt.setInt(2, checkinID);
                ptmt.execute();
                System.out.println("A billing_checkin information has been generated!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            result = true;
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            return result;
        }
    }

    public static void update() {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForCategory = "[1-3]+";
        String patternForSSN = "^[0-9]{9}$";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("SSN of customer: ");
            input = sc.nextLine();
            if (Pattern.matches(patternForSSN, input)) break;
            else System.out.println("Your input is illegal");
        }
        String SSN = input;

        while (true) {
            System.out.print("The bill ID: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input)) break;
            else System.out.println("Your input is illegal");
        }
        int billID = Integer.valueOf(input);

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
        switch (input) {
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
        if (input.equals("1") || input.equals("3")) {
            while (true) {
                System.out.print("card number: ");
                input = sc.nextLine();
                if (Pattern.matches(pattern, input)) break;
                else System.out.println("Your input is illegal");
            }
            cardnumber = input;
        }
        while (true) {
            System.out.print("Price: ");
            input = sc.nextLine();
            if (Pattern.matches(pattern, input) || Pattern.matches(patternForDecimal, input)) break;
            else System.out.println("Your input is illegal");
        }
        float price = Float.valueOf(input);

        String sql = "UPDATE billing SET SSN_of_the_person_responsible_for_the_payment =?, payment_method=?, card_number=?, price=? WHERE bill_ID=?";
        Connection conn = DBconnection.getConnection();
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, SSN);
            ptmt.setString(2, output);
            ptmt.setString(3, cardnumber);
            ptmt.setFloat(4, price);
            ptmt.setInt(5, billID);
            int count = ptmt.executeUpdate();
            if (count > 0) {
                System.out.println("The bill information has been updated!");
            } else {
                System.out.println("The bill information does not exist. Update failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void show() throws SQLException {
        String input;
        String pattern = "[0-9]+";
        String patternForDecimal = "[0-9]+.[0-9]+";
        String patternForCategory = "[1-3]+";
        String patternForSSN = "^[0-9]{9}$";
        Scanner sc = new Scanner(System.in);
        int checkinID = 0;
        int billID = 0;
        float price = 0.0f;
        float total = 0.0f;
        float total_price = 0.0f;

        Connection conn = DBconnection.getConnection();
        conn.setAutoCommit(false);
        try{
            while (true) {
                System.out.print("The customer's SSN: ");
                input = sc.nextLine();
                if (Pattern.matches(patternForSSN, input)) break;
                else System.out.println("Your input is illegal");
            }
            String SSN = input;
    
            String sql = "select checkin_ID from checkin where customer_SSN = ? order by checkin_ID DESC limit 1";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql);
                ptmt.setString(1, SSN);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    checkinID = rs.getInt("checkin_ID");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            String sql1 = "select bill_ID from billing_checkin where checkin_ID = ? order by bill_ID DESC limit 1";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql1);
                ptmt.setInt(1, checkinID);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    billID = rs.getInt("bill_ID");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            String sql2 = "select price from billing where bill_ID =?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql2);
                ptmt.setInt(1, billID);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    price = rs.getInt("price");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("--------------------------------");
            System.out.println("The customer's SSN number:");
            System.out.println(SSN);
            System.out.println("--------------------------------");
            System.out.println("The total amount of Accommodation fee:");
            System.out.println(price);
            System.out.println("--------------------------------");
            System.out.println("Service_Name         price");
    
            String sql3 = "select service_name, price from service where checkin_ID = ?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql3);
                ptmt.setInt(1, checkinID);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    String service = rs.getString("service_name");
                    float unit_price = rs.getFloat("price");
                    System.out.printf("%s\t\t   %f\n", service, unit_price);
                    total += unit_price;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            total_price = total + price;
            System.out.println("--------------------------------");
            System.out.println("The total amount the customer need to pay is:");
            System.out.println(total_price);
    
            
            String sql4 = "update billing set price =? where bill_ID =?";
            try{
                PreparedStatement ptmt = conn.prepareStatement(sql4);
                ptmt.setFloat(1, total_price);
                ptmt.setInt(2, billID);
                ptmt.execute();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            Date checkout = null;
            String sql5 = "select end_date from checkin where checkin_ID =?";
            try{
                PreparedStatement ptmt = conn.prepareStatement(sql5);
                ptmt.setInt(1,checkinID);
                ResultSet rs = ptmt.executeQuery();
                while(rs.next()){
                    checkout = rs.getDate("end_date");
                 }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    
            Date checkouttime = null;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // set date format
            String endtime = df.format(checkout);
            try{
                checkouttime = df.parse(endtime);
            }catch(ParseException e){
            e.printStackTrace();
            }
            String sql6 = "update checkin set checkout_time=? where checkin_ID =?";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql6);
                ptmt.setTimestamp(1, new Timestamp(checkouttime.getTime()));
                ptmt.setInt(2, checkinID);
                ptmt.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
        }finally{
            conn.setAutoCommit(true);
        }
        
    }
}  