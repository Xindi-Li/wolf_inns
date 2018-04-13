import java.util.Scanner;

public class MenuItem {
    public static void infoProcessing() {
        String input;
        Scanner sc = new Scanner(System.in);
        printInfoMenu();
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    HotelOperation.enterHotel();
                    printInfoMenu();
                    break;
                case "2":
                    HotelOperation.updateHotel();
                    printInfoMenu();
                    break;
                case "3":
                    HotelOperation.deleteHotel();
                    printInfoMenu();
                    break;
                case "4":
                    RoomOperation.enterRoom();
                    printInfoMenu();
                    break;
                case "5":
                    RoomOperation.updateRoom();
                    printInfoMenu();
                    break;
                case "6":
                    RoomOperation.deleteRoom();
                    printInfoMenu();
                    break;
                case "7":
                    StaffOperation.enterStaff();
                    printInfoMenu();
                    break;
                case "8":
                    StaffOperation.updateStaff();
                    printInfoMenu();
                    break;
                case "9":
                    StaffOperation.deleteStaff();
                    printInfoMenu();
                    break;
                case "10":
                    CustomerOperation.enterCustomer();
                    printInfoMenu();
                    break;
                case "11":
                    CustomerOperation.updateCustomer();
                    printInfoMenu();
                    break;
                case "12":
                    CustomerOperation.deleteCustomer();
                    break;
                case "13":
                    RoomOperation.releaseRoom();
                    printInfoMenu();
                    break;
                case "14":
                    RoomOperation.checkRoomIsAvailableWithRoomNumber();
                    printInfoMenu();
                    break;
                case "15":
                    RoomOperation.checkRoomIsAvailableWithRoomCategory();
                    printInfoMenu();
                    break;
                case "16":
                    RoomOperation.assignRooms();
                    printInfoMenu();
                    break;
                case "999":
                    MainMenu.printItem();
                    return;
                default:
                    System.out.println("Your input is illegal.");
            }
        }
    }

    private static void printInfoMenu() {
        System.out.println("=====================================");
        System.out.println("1. Enter a new hotel");
        System.out.println("2. Update a hotel");
        System.out.println("3. Delete a hotel");
        System.out.println("4. Enter a new room");
        System.out.println("5. Update a room");
        System.out.println("6. Delete a room");
        System.out.println("7. Enter a new staff");
        System.out.println("8. Update a staff");
        System.out.println("9. Delete a staff");
        System.out.println("10. Enter a new customer");
        System.out.println("11. Update a customer");
        System.out.println("12. Delete a customer");
        System.out.println("13. Release a room");
        System.out.println("14. Check if room is available with room number");
        System.out.println("15. Check if room is available with room category");
        System.out.println("16. Assign rooms to customers according to their requests and availability");

        System.out.println("999. Go to the main menu");
        System.out.println("=====================================");
    }

    public static void maintainSerRecord() {
        String input;
        Scanner sc = new Scanner(System.in);
        printServiceMenu();
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    ServiceOperration.enterService();
                    printServiceMenu();
                    break;
                case "2":
                    ServiceOperration.updateService();
                    printServiceMenu();
                    break;
                case "3":
                    MainMenu.printItem();
                    return;
                default:
                    System.out.println("Your input is illegal.");
            }
        }
    }

    private static void printServiceMenu() {
        System.out.println("=====================================");
        System.out.println("1. Enter a new service");
        System.out.println("2. Update a service");
        System.out.println("3. Go to the main menu");
        System.out.println("=====================================");
    }
      
    public static void maintainBill(){
        String input;
        Scanner sc = new Scanner(System.in);
        printBillMenu();
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    try{
                        BillOperation.generate();
                        printBillMenu();
                        break;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                case "2":
                    BillOperation.update();
                    printBillMenu();
                    break;
                case "3":
                    BillOperation.show();
                    printBillMenu();
                    break;       
                default:
                    System.out.println("Your input is illegal.");
            }
        }
    }

    public static void printBillMenu() {
        System.out.println("=====================================");
        System.out.println("1. Generate  bill information");
        System.out.println("2. Update bill information");
        System.out.println("3. Show bill information with details");
        System.out.println("=====================================");    
    }

    public static void report() {

    }
}
