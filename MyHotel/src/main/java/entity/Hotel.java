package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * entity to store all the booking information and the total number of room
 */
public class Hotel {

    private int roomTotal;
    private List<Booking> bookings;
    private Map<String, List<Integer>> roomsForDate;
    private Map<String, List<Booking>> bookingForGuest;

    public Hotel(int roomTotal) {
        this.roomTotal = roomTotal;
        bookings = new ArrayList<Booking>();
        this.roomsForDate = new HashMap<String, List<Integer>>();
        this.bookingForGuest = new HashMap<String, List<Booking>>();
    }

    public int getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(int roomTotal) {
        this.roomTotal = roomTotal;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Map<String, List<Integer>> getRoomsForDate() {
        return roomsForDate;
    }

    public void setRoomsForDate(Map<String, List<Integer>> roomsForDate) {
        this.roomsForDate = roomsForDate;
    }

    public Map<String, List<Booking>> getBookingForGuest() {
        return bookingForGuest;
    }

    public void setBookingForGuest(Map<String, List<Booking>> bookingForGuest) {
        this.bookingForGuest = bookingForGuest;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "roomTotal=" + roomTotal +
                ", bookings=" + bookings +
                ", roomsForDate=" + roomsForDate +
                ", bookingForGuest=" + bookingForGuest +
                '}';
    }
}
