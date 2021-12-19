package service.impl;

import entity.Booking;
import entity.Hotel;
import service.Step;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class StepImpl implements Step {

    /**
     * change total
     */
    public Hotel step1(Scanner scanner, Integer total, Hotel hotel) {
        System.out.println("Please enter total num you wanna change into:");
        total = Integer.parseInt(scanner.nextLine());
        if (total >= hotel.getRoomTotal()) {
            hotel.setRoomTotal(total);
            System.out.println("Total room num is now " + total);
        } else {
            System.err.println("===Total num is smaller than the existing amount, " +
                    "changing will reload your booking info, sure to do it? (y/n)");
            String sure = scanner.nextLine();
            if ("y".equals(sure)) {
                hotel = new Hotel(total);
            } else {
                System.out.println("Change quit");
            }
        }
        return hotel;
    }

    /**
     * add booking
     */
    public void step2(Scanner scanner, String guestName, String date, Integer roomId, Hotel hotel, FunctionImpl function) {
        System.out.println("===Booking start===");
        System.out.println("Please enter guest name:");
        guestName = scanner.nextLine();
        System.out.println("Please enter date like (20211219):");
        date = scanner.nextLine();
        roomId = function.getRoomId(date, hotel);
        if (roomId == 0) {
            System.err.println("Sorry, room is full for date " + date);
        }
        System.out.println("Your roomId is " + roomId);
        Booking booking = new Booking(guestName, roomId, date);
        function.saveBooking(booking, hotel);
        System.out.println("===Booking end===");
    }

    /**
     * query rooms by date
     */
    public void step3(Scanner scanner, String date, Hotel hotel, FunctionImpl function) {
        System.out.println("Please enter the date your wanna check :");
        date = scanner.nextLine();
        List<Integer> availableRoomsByDate = function.findAvailableRoomsByDate(date, hotel);
        System.out.println("Date " + date + " has " + availableRoomsByDate.size() + " rooms left");

    }

    /**
     * query bookings by guest
     */
    public void step4(Scanner scanner, String guestName, Hotel hotel, FunctionImpl function) {
        System.out.println("Please enter the guest you wanna check:");
        guestName = scanner.nextLine();
        List<Booking> bookings = function.findBookingsByGuest(guestName, hotel);
        if (Util.isEmpty(bookings)) {
            System.out.println(guestName + " has no booking information");
        } else {
            System.out.println(guestName + " has booking information as below " + "\n" + bookings);
        }
    }
}
