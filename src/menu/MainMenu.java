package menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final List<String> menus = Arrays.asList(
            "1. Find a reservation a room",
            "2. See my reservations",
            "3. Create an account",
            "4. Admin",
            "5. Exit"
    );

    public static void excute() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------------------------------------------");
            menus.forEach(System.out::println);
            System.out.println("------------------------------------------------");
            System.out.println("Please select a number for the menu option");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    AdminMenu.excute();
                    break;
                case 5:
                    System.out.println("Bye bye!!!");
                    break;
                default:
                    System.out.println("Not supported");
                    break;
            }

        } while (choice != 5);
        sc.close();
    }
}
