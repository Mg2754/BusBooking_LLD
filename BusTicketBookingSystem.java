import java.util.HashMap;
import java.util.Scanner;

public class BusTicketBookingSystem {
    private String busType;
    private String startPoint;
    private String endPoint;
    private String boardPoint;
    private String dropPoint;
    private HashMap<String, String> seats;

    public BusTicketBookingSystem(String busType, String startPoint, String endPoint, String boardPoint, String dropPoint) {
        this.busType = busType;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.boardPoint = boardPoint;
        this.dropPoint = dropPoint;
        seats = new HashMap<>();

        // Initialize seats
        String[] seatLabels = {
                "1A", "1B", "1C", "1D", "1E",
                "2A", "2B", "2C", "2D", "2E",
                "3A", "3B", "3C", "3D", "3E",
                "4A", "4B", "4C", "4D", "4E"
        };
        for (String seat : seatLabels) {
            seats.put(seat, null);
        }
    }

    public void displayInfo() {
        System.out.println("\nBus Type: " + busType);
        System.out.println("Bus Starting Point: " + startPoint);
        System.out.println("Bus Ending Point: " + endPoint);
        System.out.println("Boarding Point: " + boardPoint);
        System.out.println("Dropping Point: " + dropPoint);
    }

    public void displaySeats() {
        System.out.println("\nAvailable Seats:");
        for (String seat : seats.keySet()) {
            if (seats.get(seat) == null) {
                System.out.print(seat + " ");
            }
        }
        System.out.println();
    }

    public void bookTicket(Scanner sc) {
        displaySeats();
        System.out.print("Enter seat number to book (e.g., '1A'): ");
        String seatNumber = sc.next().trim().toUpperCase();

        if (!seats.containsKey(seatNumber)) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        if (seats.get(seatNumber) == null) {
            System.out.print("Enter passenger name: ");
            String passengerName = sc.next();
            seats.put(seatNumber, passengerName);
            System.out.println("Ticket booked successfully for seat " + seatNumber + " - " + passengerName);
        } else {
            System.out.println("Seat " + seatNumber + " is already booked.");
        }
    }

    public void cancelTicket(Scanner sc) {
        System.out.print("Enter seat number to cancel booking: ");
        String seatNumber = sc.next().trim().toUpperCase();

        if (!seats.containsKey(seatNumber)) {
            System.out.println("Invalid seat number. Please try again.");
            return;
        }

        if (seats.get(seatNumber) != null) {
            System.out.println("Cancelled booking for seat " + seatNumber + " - " + seats.get(seatNumber));
            seats.put(seatNumber, null);
        } else {
            System.out.println("Seat " + seatNumber + " is not booked.");
        }
    }

    public void printBill() {
        System.out.println("\nBooking Details:");
        displayInfo();
        for (String seat : seats.keySet()) {
            String passenger = seats.get(seat);
            if (passenger != null) {
                System.out.println("Seat " + seat + ": " + passenger);
            }
        }
        System.out.println("Thank you for booking with us!");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nBus Ticket Booking System");
            System.out.println("1. Display bus information");
            System.out.println("2. Display available seats");
            System.out.println("3. Book a ticket");
            System.out.println("4. Cancel a booking");
            System.out.println("5. Print booking details and exit");
            System.out.print("Enter your choice: ");
            String choice = sc.next().trim();

            switch (choice) {
                case "1":
                    displayInfo();
                    break;
                case "2":
                    displaySeats();
                    break;
                case "3":
                    bookTicket(sc);
                    break;
                case "4":
                    cancelTicket(sc);
                    break;
                case "5":
                    printBill();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bus Ticket Booking System");
        System.out.print("Enter bus type (AC or Non-AC): ");
        String busType = sc.next().trim().toUpperCase();

        String[] busRoutes = {"1. Salem - Erode", "2. Erode - Salem", "3. Coimbatore - Erode", "4. Erode - Coimbatore"};
        String[][] routePoints = {{"Salem", "Erode"}, {"Erode", "Salem"}, {"Coimbatore", "Erode"}, {"Erode", "Coimbatore"}};
        String[][] boardPoints = {{"Sankagiri", "Salem NB", "Avr Roundana"}, {"Erode BS", "Chithode", "Erode Bypass"}, {"Karumathampatti", "Kaniyur", "Gandhipuram"}, {"Perundurai", "KMCH", "Erode BS"}};
        String[][] dropPoints = {{"Erode BS", "Chithode", "Erode Bypass"}, {"Sankagiri", "Salem NB", "Avr Roundana"}, {"Perundurai", "KMCH", "Erode BS"}, {"Karumathampatti", "Kaniyur", "Gandhipuram"}};

        for (String route : busRoutes) {
            System.out.println(route);
        }

        System.out.print("Enter Route Choice: ");
        int route = sc.nextInt();
        String startPoint = routePoints[route - 1][0];
        String endPoint = routePoints[route - 1][1];

        System.out.println("Boarding Points:");
        for (int i = 0; i < boardPoints[route - 1].length; i++) {
            System.out.println((i + 1) + ". " + boardPoints[route - 1][i]);
        }

        System.out.print("Enter Your Board Point: ");
        int boardPointChoice = sc.nextInt();
        String boardPoint = boardPoints[route - 1][boardPointChoice - 1];

        System.out.println("Dropping Points:");
        for (int i = 0; i < dropPoints[route - 1].length; i++) {
            System.out.println((i + 1) + ". " + dropPoints[route - 1][i]);
        }

        System.out.print("Enter Your Drop Point: ");
        int dropPointChoice = sc.nextInt();
        String dropPoint = dropPoints[route - 1][dropPointChoice - 1];

        BusTicketBookingSystem busSystem = new BusTicketBookingSystem(busType, startPoint, endPoint, boardPoint, dropPoint);
        busSystem.run();
    }
}
