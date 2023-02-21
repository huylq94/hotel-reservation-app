package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private static ReservationService instance;

    private final Map<String, Collection<Reservation>> reservations = new HashMap<>();

    private final Map<String, IRoom> rooms = new HashMap<>();

    public static ReservationService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ReservationService();
        }

        return instance;
    }

    public void addRoom(IRoom room){
        this.rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {

        return this.rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customersReservation = getCustomersReservation(customer);

        if (Objects.isNull(customersReservation)) {
            customersReservation = new ArrayList<>();
        }
        customersReservation.add(reservation);
        this.reservations.put(customer.getEmail(), customersReservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        final Collection<IRoom> roomIsNotAvaible = new ArrayList<>();
        for (Collection<Reservation> reservations : reservations.values()) {
            IRoom room = reservations.stream()
                    .filter(reservation -> !isDateAvaibleToReservation(reservation, checkInDate, checkOutDate))
                    .map(Reservation::getRoom)
                    .findFirst()
                    .orElse(null);
            if (Objects.nonNull(room)) {
                roomIsNotAvaible.add(room);
            }
        }
        return rooms.values().stream()
                .filter(room -> roomIsNotAvaible.stream().noneMatch(s -> s.equals(room)))
                .collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {

        return this.reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        final Collection<Reservation> reservations = new ArrayList<>();

        for (Collection<Reservation> reservation: this.reservations.values()) {
            reservations.addAll(reservation);
        }

        if (!reservations.isEmpty()) {
            reservations.forEach(System.out::println);
        } else {
            System.out.println("Not found!!!");
        }
    }

    public Collection<IRoom> getRooms() {

        return rooms.values();
    }

    private boolean isDateAvaibleToReservation(final Reservation reservation, final Date checkInDate, final Date checkOutDate) {
        return (checkInDate.before(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate()))
                || (checkInDate.after(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate()));
    }
}
