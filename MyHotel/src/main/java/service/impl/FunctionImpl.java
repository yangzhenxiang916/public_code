package service.impl;

import entity.Booking;
import entity.Hotel;
import service.Function;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class FunctionImpl implements Function {

    public static final Logger logger = Logger.getLogger(FunctionImpl.class.toString());

    /**
     * method to save a single booking record
     *
     * @param booking
     * @return
     */
    public synchronized void saveBooking(Booking booking, Hotel hotel) {
        //check if could book
        if (checkBooked(booking, hotel)) {
            //add booking record
            hotel.getBookings().add(booking);
            //add rooms record for certain date
            if (Util.isEmpty(hotel.getRoomsForDate().get(booking.getDate()))) {
                List<Integer> rooms = new ArrayList<Integer>();
                rooms.add(booking.getRoomId());
                hotel.getRoomsForDate().put(booking.getDate(), rooms);
            } else {
                hotel.getRoomsForDate().get(booking.getDate()).add(booking.getRoomId());
            }
            //add booking record for certain guest
            if (Util.isEmpty(hotel.getBookingForGuest().get(booking.getGuestName()))) {
                List<Booking> bookings = new ArrayList<Booking>();
                bookings.add(booking);
                hotel.getBookingForGuest().put(booking.getGuestName(), bookings);
            } else {
                hotel.getBookingForGuest().get(booking.getGuestName()).add(booking);
            }
        }
    }

    /**
     * find available rooms for certain date
     *
     * @param date
     * @param hotel
     * @return
     */
    public List<Integer> findAvailableRoomsByDate(String date, Hotel hotel) {
        List<Integer> availableRooms = this.getFullRoomList(hotel);
        if (!Util.isEmpty(hotel.getRoomsForDate().get(date))) {
            availableRooms.removeAll(hotel.getRoomsForDate().get(date));
        }
        return availableRooms;
    }

    /**
     * find all bookings by guestName
     *
     * @param guestName
     * @param hotel
     * @return
     */
    public List<Booking> findBookingsByGuest(String guestName, Hotel hotel) {
        return hotel.getBookingForGuest().get(guestName);
    }

    /**
     * initialize the num of total rooms
     */
    public Hotel initRoomSize(Scanner scanner) {
        System.out.println("===Please initialize the num of total rooms===");
        int total = 0;
        try {
            total = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.err.println("===Please enter a num===");
            initRoomSize(scanner);
        }
        System.out.println("Room size initialized as " + total);
        return new Hotel(total);
    }

    /**
     * get an available roomId
     *
     * @param hotel
     * @return
     */
    public int getRoomId(String date, Hotel hotel) {
        List<Integer> availableRooms = this.getFullRoomList(hotel);
        if (!Util.isEmpty(hotel.getRoomsForDate().get(date))) {
            availableRooms.removeAll(hotel.getRoomsForDate().get(date));
        }
        Random random = new Random();
        if (availableRooms.size() == 1) {
            return (Integer) availableRooms.toArray()[0];
        }
        if (availableRooms.size() < 1) {
            return 0;
        }
        return (Integer) availableRooms.toArray()[random.nextInt(availableRooms.size() - 1)];
    }

    /**
     * check if the booking could be done
     *
     * @param booking
     * @param hotel
     * @return
     */
    private boolean checkBooked(Booking booking, Hotel hotel) {
        if (booking.getRoomId() > hotel.getRoomTotal()) {
            logger.warning("roomId out of total");
            return false;
        }
        if (!Util.isEmpty(hotel.getRoomsForDate().get(booking.getDate()))
                && hotel.getRoomsForDate().get(booking.getDate()).contains(booking.getRoomId())) {
            logger.warning("room is already booked for aim date");
            return false;
        }
        return true;
    }

    private List<Integer> getFullRoomList(Hotel hotel) {
        List<Integer> availableRooms = new ArrayList<Integer>();
        for (int i = 1; i <= hotel.getRoomTotal(); i++) {
            availableRooms.add(i);
        }
        return availableRooms;
    }
}
