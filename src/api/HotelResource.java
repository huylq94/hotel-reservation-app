package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public static Customer getCustomer(String email) {

        return CustomerService.getInstance().getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService.getInstance().addCustomer(email,firstName,lastName);
    }

    public static IRoom getRoom(String roomNumber) {

        return ReservationService.getInstance().getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {

        return ReservationService.getInstance().reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail) {

        return ReservationService.getInstance().getCustomersReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {

        return ReservationService.getInstance().findRooms(checkIn,checkOut);
    }
}
