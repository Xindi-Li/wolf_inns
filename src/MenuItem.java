import java.util.Scanner;

public class MenuItem {
    public static void infoProcessing(){
        System.out.println("1. Enter a new hotel");
        System.out.println("2. Update a hotel");
        System.out.println("3. Delete a hotel");
        System.out.println("4. Enter a new room");
        System.out.println("5. Go to the main menu");
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Operation No: ");
            input = sc.next();
            switch (input) {
                case "5":
                    MainMenu.printItem();
                    return;
            }
        }
    }
}
