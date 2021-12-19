package service;

import entity.Hotel;
import service.impl.FunctionImpl;

import java.util.Scanner;

public interface Step {
    /**
     * change total
     */
    Hotel step1(Scanner scanner, Integer total, Hotel hotel);

    /**
     * add booking
     */
    void step2(Scanner scanner, String guestName, String date, Integer roomId, Hotel hotel, FunctionImpl function);

    /**
     * query rooms by date
     */
    void step3(Scanner scanner, String date, Hotel hotel, FunctionImpl function);

    /**
     * query bookings by guest
     */
    void step4(Scanner scanner, String guestName, Hotel hotel, FunctionImpl function);
}
