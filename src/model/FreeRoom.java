package model;

public class FreeRoom extends Room{

    public FreeRoom() {
        setPrice(0.0);
    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", price=" + getRoomPrice() +
                ", enumration=" + getRoomType() +
                '}';
    }
}
