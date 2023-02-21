package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminResource {

    private static AdminResource instance;

    private CustomerService customerService = CustomerService.getInstance();

    private ReservationService reservationService = ReservationService.getInstance();

    public static AdminResource getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AdminResource();
        }

        return instance;
    }

    public Customer getCustomer(String email) {

        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {

        rooms.forEach(room -> reservationService.addRoom(room));
    }

    public Collection<IRoom> getAllRooms() {

        return reservationService.getRooms();
    }

    public Collection<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
