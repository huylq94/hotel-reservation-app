package model;

import java.util.Objects;

public class Room implements IRoom{

    private String roomNumber;

    private Double price;

    private RoomType enumeration;

    public Room() {
    }

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return this.price != null && this.price.equals(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        String roomType = enumeration.equals(RoomType.SINGLE) ? "Single" : "Double";

        return String.format("Room Number: %s %s bed Room Price: $%.1f", roomNumber,roomType,price);
    }
}
