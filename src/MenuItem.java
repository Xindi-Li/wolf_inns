import java.util.Scanner;

public class MenuItem {
    public static void infoProcessing() {
        String input;
        Scanner sc = new Scanner(System.in);
        printinfoMenu();
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "1":
                    HotelOperation.enterHotel();
                    printinfoMenu();
                    break;
                case "2":
                    HotelOperation.updateHotel();
                    printinfoMenu();
                    break;
                case "3":
                    HotelOperation.deleteHotel();
                    printinfoMenu();
                    break;
                case "5":
                    MainMenu.printItem();
                    return;
            }
        }
    }

    private static void printinfoMenu() {
        System.out.println("=====================================");
        System.out.println("1. Enter a new hotel");
        System.out.println("2. Update a hotel");
        System.out.println("3. Delete a hotel");
        System.out.println("4. Enter a new room");
        System.out.println("5. Go to the main menu");
        System.out.println("=====================================");
    }

    public static void maintainSerRecord() {

    }

    public static void maintainBill() {

    }

    public static void report() {

    }
}
