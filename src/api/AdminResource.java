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

    public static AdminResource getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AdminResource();
        }

        return instance;
    }

    public static Customer getCustomer(String email) {

        return CustomerService.getInstance().getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms) {

        rooms.forEach(room -> ReservationService.getInstance().addRoom(room));
    }

    public static Collection<IRoom> getAllRooms() {

        return ReservationService.getInstance().getRooms();
    }

    public static Collection<Customer> getAllCustomers() {

        return CustomerService.getInstance().getAllCustomers();
    }

    public static void displayAllReservations() {
        ReservationService.getInstance().printAllReservation();
    }
}
