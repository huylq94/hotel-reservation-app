package model;

public class Room implements IRoom{

    private String roomNumber;

    private Double price;

    private RoomType enumration;

    public Room() {
    }

    public Room(String roomNumber, Double price, RoomType enumration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumration = enumration;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setEnumration(RoomType enumration) {
        this.enumration = enumration;
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
        return this.enumration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        String roomType = enumration.equals(RoomType.SINGLE) ? "Single" : "Double";

        return String.format("Room Number: %s %s bed Room Price: $%.1f", roomNumber,roomType,price);
    }
}
