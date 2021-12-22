package service;

import entity.Booking;
import entity.Hotel;

import java.util.List;
import java.util.Scanner;

public interface Function {
    /**
     * method to save a single booking record
     *
     * @param booking
     * @return
     */
    int saveBooking(Booking booking, Hotel hotel);

    /**
     * find available rooms for certain date
     */
    List<Integer> findAvailableRoomsByDate(String date, Hotel hotel);

    /**
     * find all bookings by guestName
     */
    List<Booking> findBookingsByGuest(String guestName, Hotel hotel);

    /**
     * intialize the num of total rooms
     */
    Hotel initRoomSize(Scanner scanner);

    /**
     * get an available roomId
     *
     * @return
     */
    int getRoomId(String date, Hotel hotel);
}
