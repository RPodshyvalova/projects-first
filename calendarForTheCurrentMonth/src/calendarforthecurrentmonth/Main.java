package calendarforthecurrentmonth;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *  must do:
 *  - 1 - print the calendar on the current month on console
 *  - 2 - distinguish weekends and a current day
 *  - 3 - optional: move on the calendar back and forward by month
 *  - 4 - optional: write the tests
 */

public class Main {
    private static LocalDate currentDate = LocalDate.now();
    
    public static void main(String[] args) {
        ModelForMonth model;
        Scanner scanner = new Scanner(System.in);
        model = new ModelForMonth(currentDate);
        model.printModel();
        System.out.println("Print exit for exit...\nPrint prev or next for move on the calendar...\n ");
        while (true) {
            String userCommand = scanner.next();
            if (!"exit".equals(userCommand)) {
                switch (userCommand) {
                    case "next":
                        currentDate = currentDate.plusMonths(1);
                        model = new ModelForMonth(currentDate);
                        model.printModel();
                        break;
                    case "prev":
                        currentDate = currentDate.minusMonths(1);
                        model = new ModelForMonth(currentDate);
                        model.printModel();
                        break;
                }
            } else {
                scanner.close();
                System.exit(0);
            }
        }

    }
}
