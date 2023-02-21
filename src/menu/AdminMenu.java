package menu;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import utils.InputValidation;

import java.util.*;

public class AdminMenu {

    private static final List<String> adminMenus = Arrays.asList(
            "1. See all Customers",
            "2. See all Rooms",
            "3. See all Reservations",
            "4. Add a Room",
            "5. Back to Main Menu"
    );

    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void execute() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nAdmin Menu");
            System.out.println("------------------------------------------------");
            adminMenus.forEach(System.out::println);
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = InputValidation.inputNumber("Please input correct number(0-9)");
            switch (choice) {
                case 1:
                    Collection<Customer> customers = adminResource.getAllCustomers();
                    if (customers.isEmpty()) {
                        System.out.println("Not found!!!");
                    } else {
                        customers.forEach(System.out::println);
                    }
                    break;
                case 2:
                    Collection<IRoom> iRooms = adminResource.getAllRooms();
                    if (iRooms.isEmpty()) {
                        System.out.println("Rooms not found!!!");
                    } else {
                        iRooms.forEach(System.out::println);
                    }
                    break;
                case 3:
                    adminResource.displayAllReservations();
                    break;
                case 4:
                    //Add a Room
                    String result;
                    List<IRoom> rooms = new ArrayList<>();
                    do {
                        System.out.println("Enter room number");
                        String roomNumber = String.valueOf(sc.nextLine());

                        while (isRoomExist(roomNumber)) {
                            System.out.println("Room number is exist! Please try again.");
                            roomNumber = String.valueOf(sc.nextLine());
                        }
                        System.out.println("Enter price per night");
                        String price = InputValidation.inputString("^\\d+(.\\d{1})?","Please input price with format (^\\d+(.\\d{1})?)");
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        String roomType = InputValidation.inputString("1|2", "Please input room type with number is 1 or 2!!!");
                        rooms.add(new Room(roomNumber,Double.parseDouble(price), Integer.parseInt(roomType) == 1 ? RoomType.SINGLE : RoomType.DOUBLE));
                        System.out.println("Would you like to add another room y/n");
                        result = String.valueOf(sc.nextLine());
                        while (!result.toUpperCase().matches("Y|N")) {
                            System.out.println("Please enter Y (Yes) or N (No)");
                            result = String.valueOf(sc.nextLine());
                        }
                    } while ("Y".equalsIgnoreCase(result));

                    adminResource.addRoom(rooms);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Not supported");
                    break;
            }

        } while (choice != 5);
    }

    private static boolean isRoomExist(final String roomNumber) {

        return adminResource.getAllRooms()
                .stream()
                .anyMatch(room -> room.getRoomNumber().equals(roomNumber));
    }
}
