package menu;


import api.HotelResource;
import model.IRoom;
import model.Reservation;
import utils.InputValidation;

import java.text.ParseException;
import java.util.*;

public class MainMenu {

    private static final List<String> menus = Arrays.asList(
            "1. Find a reservation a room",
            "2. See my reservations",
            "3. Create an account",
            "4. Admin",
            "5. Exit"
    );

    private static final HotelResource hotelResource = HotelResource.getInstance();

    public static void execute() throws ParseException {
        final Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nWelcome to the Hotel Reservation Application");
            System.out.println("------------------------------------------------");
            menus.forEach(System.out::println);
            System.out.println("------------------------------------------------");
            System.out.println("Please select a number for the menu option");
            choice = InputValidation.inputNumber("Please input correct number(0-9)");

            switch (choice) {
                case 1 -> findAReservationARoom();
                case 2 -> getReservation();
                case 3 -> {
                    //Create account
                    System.out.println("Enter Email format: name@domain.com");
                    String customerEmail = InputValidation.inputString("^(.+)@(.+).com$", "Please enter email format: name@domain.com");
                    while (Objects.nonNull(hotelResource.getCustomer(customerEmail))) {
                        System.out.println("Email is exist! Please try again.");
                        customerEmail = InputValidation.inputString("^(.+)@(.+).com$", "Please enter email format: name@domain.com");
                    }
                    System.out.println("First Name");
                    String firstName = String.valueOf(sc.nextLine());
                    System.out.println("Last Name");
                    String lastName = String.valueOf(sc.nextLine());
                    hotelResource.createACustomer(customerEmail, firstName, lastName);
                }
                case 4 -> AdminMenu.execute();
                case 5 -> System.out.println("Bye bye!!!");
                default -> System.out.println("Not supported");
            }

        } while (choice != 5);
        sc.close();
    }

    private static void getReservation() {
        System.out.println("Enter Email format: name@domain.com");
        String customerEmail = InputValidation.inputString("^(.+)@(.+).com$", "Please enter email format: name@domain.com");
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);
        if (Objects.isNull(reservations) || reservations.isEmpty()) {
            System.out.println("Reservations not found!!!");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private static void findAReservationARoom() throws ParseException {
        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
        final Date checkInDate = InputValidation.inputDate("Please input Date mm/dd/yyyy example 02/01/2020!!!");
        System.out.println("Enter CheckOut Date mm/dd/yyyy example 2/21/2020");
        final Date checkOutDate = InputValidation.inputDate("Please input Date mm/dd/yyyy example 2/21/2020");
        Collection<IRoom> aRoom = hotelResource.findARoom(checkInDate, checkOutDate);
        if (!aRoom.isEmpty()) {
            aRoom.forEach(System.out::println);
            reserveRoom(checkInDate,checkOutDate, aRoom);
        } else {
            //alternative room
            final Date alternativeCheckIn = plusSevenDay(checkInDate);
            final Date alternativeCheckOut = plusSevenDay(checkOutDate);

            Collection<IRoom> alternativeRoom = hotelResource.findARoom(alternativeCheckIn, alternativeCheckOut);
            if (alternativeRoom.isEmpty()) {
                System.out.println("Rooms not found");
            } else {
                System.out.printf("We've only found room on alter native dates:\nCheckIn Date: %s\nCheckOut Date: %s\n",alternativeCheckIn,alternativeCheckOut);
                alternativeRoom.forEach(System.out::println);
                reserveRoom(alternativeCheckIn,alternativeCheckOut, alternativeRoom);
            }
        }
    }

    private static void reserveRoom(Date checkInDate, Date checkOutDate, Collection<IRoom> rooms) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to book a room? y/n");
        final String isChoiceBookRoom = InputValidation.inputString("y|Y|n|N", "Please enter Y/N");
        if (isChoiceBookRoom.equalsIgnoreCase("Y")) {
            System.out.println("Do you have an account with us? y/n");
            final String haveAccount = InputValidation.inputString("y|Y|n|N", "Please enter Y/N");
            if (haveAccount.equalsIgnoreCase("Y")) {
                System.out.println("Enter Email format: name@domain.com");

                final String customerEmail = InputValidation.inputString("^(.+)@(.+).com$", "Please enter email format: name@domain.com");
                if (Objects.isNull(hotelResource.getCustomer(customerEmail))) {
                    System.out.println("Customer not found");
                } else {
                    System.out.println("What room number would you like to reserve?");
                    final String roomNumber = String.valueOf(sc.nextLine());
                    if (rooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomNumber))) {
                        IRoom room = rooms.stream().filter(room1 -> room1.getRoomNumber().equals(roomNumber)).findFirst().orElse(null);
                        Reservation reservation = hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);
                        System.out.println("Reservation created successfully!");
                        System.out.println(reservation);
                    } else {
                        System.out.println("Error: Room number not available");
                    }
                }
            } else {
                System.out.println("Please, create an Account");
            }
        }
    }

    private static Date plusSevenDay(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);

        return calendar.getTime();
    }
}
