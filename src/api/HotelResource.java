package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class HotelResource {

    private static HotelResource instance;

    private CustomerService customerService = CustomerService.getInstance();

    private ReservationService reservationService = ReservationService.getInstance();

    public static HotelResource getInstance() {
        if (Objects.isNull(instance)) {
            instance = new HotelResource();
        }

        return instance;
    }

    public Customer getCustomer(String email) {

        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email,firstName,lastName);
    }

    public IRoom getRoom(String roomNumber) {

        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {

        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {

        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {

        return reservationService.findRooms(checkIn,checkOut);
    }
}
