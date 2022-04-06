package cinema;

import java.util.Scanner;

public class Cinema {

    final static int firstClassTicket = 10;
    final static int secondClassTicket = 8;
    static int row;
    static int seats;
    static int selectedRow, selectedSeat;
    static String[][] cinema;
    static int purchasedTickets;
    static int currentIncome;
    static int totalIncome;
    public static void main(String[] args) {
        int row;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of rows:");
            row = scanner.nextInt();
        } while (row > 9);
        do {
            System.out.println("Enter the number of seats in each row:");
            seats = scanner.nextInt();
        } while (seats > 9);

        cinema = createArrayCinema(row, seats);

        int x = showMenu();

        while(x != 0){
            if(x == 1){
                showArray(cinema);
                x = showMenu();
            }else if (x == 2){
                //selectPlaceRow();
                //selectPlaceSeat();
                buyTicket(cinema);
                x = showMenu();
            } else if (x == 3){
                showStatistics(row, seats);
                x = showMenu();
            } else{
                System.out.println("Choose correct menu option!");
                x = showMenu();
            }
        }

    }
    //ASK ABOUT PLACE
    static int selectPlaceRow(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number: ");
        selectedRow = scanner.nextInt();
        if(selectedRow > cinema.length){
        }
        return selectedSeat;
    }static int selectPlaceSeat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a seat number in that row: ");
        selectedSeat = scanner.nextInt();
        return selectedSeat;
    }
    //SHOW MENU
    static int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int x = scanner.nextInt();
        return x;
    }
    //CREATE ARRAY
    static String[][] createArrayCinema(int x, int y){
        String[][] cinema = new String[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++){
                cinema[i][j] = "S";
            }
        }
        if (y * x <= 60) {
            totalIncome = y * x * firstClassTicket;
        } else if (x % 2 == 1) {
            totalIncome = ((x / 2) * y * firstClassTicket) + ((x / 2) + 1) * y * secondClassTicket;
        } else {
            totalIncome = (x / 2) * y * firstClassTicket + (x /2 ) * y * secondClassTicket;
        }
        return cinema;
    }
    //SHOW ARRAY
    static void showArray(String[][] x) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < x.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }

    }
    //BUY A TICKET
    static void buyTicket( String[][] z){
        selectPlaceRow();
        selectPlaceSeat();
        System.out.println(selectedRow);
        while(selectedRow > z.length || selectedSeat > z.length){
            System.out.println("Wrong input!");
            System.out.println();
            selectPlaceRow();
            selectPlaceSeat();
        }
        while( z[selectedRow - 1][selectedSeat - 1] == "B" ){
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            selectPlaceRow();
            selectPlaceSeat();
            while(selectedRow > z.length || selectedSeat > z.length){
                System.out.println("Wrong input!");
                System.out.println();
                selectPlaceRow();
                selectPlaceSeat();
            }
        }
        if((z.length * z.length) <= 60){
            System.out.println("Ticket price: " + "$" + firstClassTicket);
            currentIncome += firstClassTicket;
        } else if (selectedRow <= z.length / 2 ){
            System.out.println("Ticket price: " + "$" + firstClassTicket);
            currentIncome += firstClassTicket;
        } else {
            System.out.println("Ticket price: " + "$" + secondClassTicket);
            currentIncome += secondClassTicket;
        }
        z[selectedRow - 1][selectedSeat - 1] = "B";


        purchasedTickets++;

        System.out.println();

    }
    //SHOW STATISTICS
    static void showStatistics(int x, int y) {
        int numberOfPurchasedTickets = purchasedTickets;
        char percentageChar = '%';
        System.out.println();
        System.out.printf("Number of purchased tickets: %d%n", numberOfPurchasedTickets);
        float percentage = (float)numberOfPurchasedTickets / ((float)x * (float)y) * 100;
        System.out.printf("Percentage: %.2f%c", percentage, percentageChar);
        System.out.println();
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }
}
