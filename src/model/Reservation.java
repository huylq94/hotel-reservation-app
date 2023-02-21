package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public record Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {

    @Override
    public String toString() {
        String roomType = room.getRoomType().equals(RoomType.SINGLE) ? "Single": "Double";
        DateFormat df = new SimpleDateFormat("EEE MMM dd");
        return String.format("Reservation: %s\nRoom: %s - %s bed\nPrice: $%.1f price per night\nCheckin Date: %s\nCheckout Date: %s"
                , this.customer.email(),this.room.getRoomNumber(), roomType, this.room.getRoomPrice(),df.format(this.checkInDate),df.format(this.checkOutDate));
    }
}
