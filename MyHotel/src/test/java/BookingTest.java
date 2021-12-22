import entity.Booking;
import entity.Hotel;
import org.junit.Test;
import service.impl.FunctionImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BookingTest {

    /**
     * test for single booking
     */
    @Test
    public void booking() {
        FunctionImpl function = new FunctionImpl();
        Random random = new Random();
        int roomTotal = random.nextInt(1) + 1;
        Hotel hotel = new Hotel(roomTotal);
        int roomId = function.getRoomId("20211222", hotel);
        Booking booking = new Booking("ZhenXiang Yang", roomId, "20211222");
        function.saveBooking(booking, hotel);
    }

    /**
     * test for multiple booking for a certain date
     */
    @Test
    public void bookingsForOneDate() {
        FunctionImpl function = new FunctionImpl();
        Random random = new Random();
        int roomTotal = random.nextInt(10) + 1;
        Hotel hotel = new Hotel(roomTotal);
        String date = "20211222";
        int roomId = 1;
        Booking booking;
        do {
            roomId = function.getRoomId(date, hotel);
            if (0 == roomId) {
                System.err.println("no rooms for " + date);
                break;
            }
            booking = new Booking("ZhenXiang Yang", roomId, date);
        } while (1 == function.saveBooking(booking, hotel));
    }

    /**
     * test for multiple booking for multiple date
     */
    @Test
    public void bookingsForMultiDate() {
        FunctionImpl function = new FunctionImpl();
        Random random = new Random();
        int roomTotal = 3;
        Hotel hotel = new Hotel(roomTotal);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        int roomId = 1;
        Booking booking;
        int maxBookingDays = 2;
        int succeed = 0;
        int booked = 0;
        do {
            String date = simpleDateFormat.format(
                    new Date(now.getTime() + random.nextInt(1000 * 60 * 60 * 24 * maxBookingDays)));
            roomId = function.getRoomId(date, hotel);
            if(0 == roomId) {
                System.err.println("no rooms for " + date);
                continue;
            }
            if(booked == (maxBookingDays+1)*roomTotal) {
                break;
            }
            booking = new Booking("ZhenXiang Yang", roomId, date);
            succeed = function.saveBooking(booking, hotel);
            if(1 == succeed) {
                booked ++;
            }
        } while (booked < (maxBookingDays+1)*roomTotal);
        System.err.println("all rooms booked");
    }
}
