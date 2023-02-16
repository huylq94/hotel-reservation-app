package menu;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final List<String> adminMenus = Arrays.asList(
            "1. See all Customers",
            "2. See all Rooms",
            "3. See all Reservations",
            "4. Add a Room",
            "5. Back to Main Menu"
    );


    public static void excute() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("------------------------------------------------");
            adminMenus.forEach(System.out::println);
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    AdminResource.getInstance().getAllCustomers().forEach(System.out::println);
                    break;
                case 2:
                    AdminResource.getInstance().getAllRooms().forEach(System.out::println);
                    break;
                case 3:
                    AdminResource.getInstance().displayAllReservations();
                    break;
                case 4:
                    //Add a Room
                    String result;
                    List<IRoom> rooms = new ArrayList<>();
                    do {
                        System.out.println("Enter room number");
                        String roomNumber = String.valueOf(sc.nextLine());
                        System.out.println("Enter price per night");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        int roomType = Integer.parseInt(sc.nextLine());
                        rooms.add(new Room(roomNumber,price, roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE));
                        System.out.println("Would you like to add another room y/n");
                        result = String.valueOf(sc.nextLine());
                        while (!result.toUpperCase().matches("Y|N")) {
                            System.out.println("Please enter Y (Yes) or N (No)");
                            result = String.valueOf(sc.nextLine());
                        }
                    } while ("Y".equalsIgnoreCase(result));
                    AdminResource.getInstance().addRoom(rooms);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Not supported");
                    break;
            }

        } while (choice != 5);
        sc.close();
    }
}
