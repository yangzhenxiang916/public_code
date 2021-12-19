package entity;

/**
 * entity to store the basic information of a single booking
 */
public class Booking {

    private String guestName;
    private int roomId;
    private String date;

    public Booking() {
    }

    public Booking(String guestName, int roomId, String date) {
        this.guestName = guestName;
        this.roomId = roomId;
        this.date = date;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "guestName='" + guestName + '\'' +
                ", roomId=" + roomId +
                ", date='" + date + '\'' +
                '}';
    }
}
