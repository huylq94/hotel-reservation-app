package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReservationService {

    private static ReservationService instance;

    private static Collection<IRoom> rooms = new ArrayList<>();

    private static Collection<Reservation> reservations = new ArrayList<>();

    public static ReservationService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ReservationService();
        }

        return instance;
    }

    public static void addRoom(IRoom room){
        rooms.add(room);
    }

    public static IRoom getARoom(String roomId) {

        return rooms.stream()
                .filter(room -> room.getRoomNumber().equals(roomId))
                .findFirst()
                .orElse(null);
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);

        return reservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        return reservations.stream()
                .filter(reservation -> reservation.getCheckInDate().compareTo(checkInDate) == 0 && reservation.getCheckOutDate().compareTo(checkOutDate) == 0)
                .map(Reservation::getRoom)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer) {

        return reservations.stream()
                .filter(reservation -> reservation.getCustomer().equals(customer))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void printAllReservation() {
        reservations.forEach(System.out::println);
    }

    public static Collection<IRoom> getRooms() {
        return rooms;
    }
}
