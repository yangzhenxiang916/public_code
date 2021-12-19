import entity.Hotel;
import service.impl.FunctionImpl;
import service.impl.StepImpl;

import java.util.Scanner;

public class start {

    /**
     * begin my hotel booking system on Console
     *
     * @param args
     */
    public static void main(String[] args) {

        FunctionImpl function = new FunctionImpl();
        StepImpl step = new StepImpl();
        String guestName = null;
        int roomId = 0;
        String date = null;
        Integer total = 0;
        Scanner scanner = new Scanner(System.in);

        //initialize the amount of rooms
        Hotel hotel = function.initRoomSize(scanner);

        while (true) {
            System.out.println("【1】Change the total room number 【2】Booking 【3】Query rooms by date" +
                    " 【4】Query bookings by guest 【5】Press to quit");
            Integer str = null;
            try {
                str = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("===PLease enter num===");
                continue;
            }
            switch (str) {
                case 1:
                    hotel = step.step1(scanner, total, hotel);
                    break;
                case 2:
                    step.step2(scanner, null, null, roomId, hotel, function);
                    break;
                case 3:
                    step.step3(scanner, null, hotel, function);
                    break;
                case 4:
                    step.step4(scanner, null, hotel, function);
                    break;
                case 5:
                    System.out.println("===Thank you for using===");
                    return;
                default:
                    System.out.println("===Please choosing the existing function===");
            }
        }
    }
}