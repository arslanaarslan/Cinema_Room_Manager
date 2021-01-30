package cinema;

import java.util.*;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static int numberOfRows = 0;
    public static int numberOfSeatsInEachRow = 0;
    public static int numberOfSeat;

    // For statistics
    public static int numberOfPurchasedTickets = 0;
    public static float percentage = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;

    public static char[][] cinema;

    public static void main(String[] args) {
        // Write your code here


        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeatsInEachRow = scanner.nextInt();

        // Number Of Seat Calculation
        numberOfSeat = numberOfRows * numberOfSeatsInEachRow;


        cinema = new char[numberOfRows][numberOfSeatsInEachRow];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInEachRow; j++) {
                cinema[i][j] = 'S';
            }
        }

        while (true) {
            System.out.println("\n1. Show the seats" +
                               "\n2. Buy a ticket" +
                               "\n3. Statistics" +
                               "\n0. Exit");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    showSeats(numberOfRows, numberOfSeatsInEachRow, cinema);
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                default:
                    break;
            }

            if (selection == 0)
                break;

        }
    }

    private static void showSeats(int numberOfRows, int numberOfSeatsInEachRow, char[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i < (numberOfRows + 1); i++) {
            for (int j = 0; j < (numberOfSeatsInEachRow + 1); j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" ");
                } else if (i == 0) {
                    System.out.print(j);
                } else if (j == 0) {
                    System.out.print(i);
                } else {
                    System.out.print(cinema[i - 1][j - 1]);
                }

                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void buyTicket() {
        int toBeBoughtTicketRowNumber;
        int toBeBoughtTicketColumnNumber;

        do {
            System.out.println();

            System.out.println("Enter a row number:");

            toBeBoughtTicketRowNumber = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");

            toBeBoughtTicketColumnNumber = scanner.nextInt();

            System.out.println();

            if (toBeBoughtTicketRowNumber > numberOfRows
                    || toBeBoughtTicketColumnNumber > numberOfSeatsInEachRow) {
                System.out.println("Wrong input!");
            } else if (cinema[toBeBoughtTicketRowNumber - 1][toBeBoughtTicketColumnNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                break;
            }
        } while(true);

        int ticketPrice;

        if (numberOfSeat <= 60) {
            numberOfPurchasedTickets++;
            ticketPrice = 10;
        } else {
            int numberOfRowsInFrontHalf = (int) Math.floor((float)numberOfRows / 2);

            numberOfPurchasedTickets++;
            if (toBeBoughtTicketRowNumber <= numberOfRowsInFrontHalf) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        currentIncome = currentIncome + ticketPrice;

        System.out.println("Ticket price: $" + ticketPrice);

        cinema[toBeBoughtTicketRowNumber - 1][toBeBoughtTicketColumnNumber - 1] = 'B';
    }

    private static void statistics() {
        System.out.println();
        /*
        Number of Purchased Tickets Calculation
         */
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        /*
        Percentage Calculation
         */
        if (numberOfSeat == 0) {
            percentage = 0;
        } else {
            percentage = (float) numberOfPurchasedTickets / ((float) numberOfSeat) * 100;
        }

        System.out.println("Percentage: " + String.format("%.2f",percentage) + "%");
        /*
        Current Income Calculation
         */
        System.out.println("Current income: $" +  currentIncome);
        /*
        Total Income Calculation
         */
        if (numberOfSeat <= 60) {
            totalIncome = 10 * numberOfSeat;
        } else {
            int numberOfRowsInFrontHalf = (int) Math.ceil((float)numberOfRows / 2);
            int numberOfRowsInBackHalf = numberOfRows - numberOfRowsInFrontHalf;
            totalIncome = (numberOfRowsInFrontHalf * numberOfSeatsInEachRow * 8)
                        + (numberOfRowsInBackHalf * numberOfSeatsInEachRow * 10);
        }
        System.out.println("Total income: $" + totalIncome);
    }
}