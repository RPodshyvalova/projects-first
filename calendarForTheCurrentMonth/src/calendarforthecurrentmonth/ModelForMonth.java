package calendarforthecurrentmonth;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 *  colorize days in calendar of month:
 *  - RED - for weekends 
 *  - GREEN - for today
 *  - GRAY - other days
 */

public class ModelForMonth {
    public static final String ANSI_CLEAR = "\033[0m";
    public static final String ANSI_RED = "\033[31m";
    public static final String ANSI_GREEN = "\033[42m";
    public static final String ANSI_GRAY = "\033[30m";

    private Object model[][] = new Object[6][7];
    private LocalDate currentDate;
    
    ModelForMonth() {
        this.currentDate = LocalDate.now();
    }
    
    ModelForMonth(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
    
    public Object[][] getModel(){
        return model;
    }
    
    public void printModel() {
        fillModel();
        String daysOfWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int currentDay = currentDate.getDayOfMonth();
        System.out.println(currentDate.getMonth() + " " + currentDate.getYear() + " year");
        for (String  day : daysOfWeek) {
            System.out.printf("%19s |",  ANSI_GRAY + day + ANSI_CLEAR);
        }
        System.out.println();
        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (model[i][j] != null && String.valueOf(currentDay).equals(model[i][j].toString())) {
                    colorizeAndPrintValue(model[i][j].toString(), ANSI_GREEN);
                } else if (j <  model[i].length - 2) {
                    if (model[i][j] != null) { 
                        colorizeAndPrintValue(model[i][j].toString(), ANSI_GRAY); 
                    } else {
                        colorizeAndPrintValue("", ANSI_GRAY);     
                    }
                } else if (model[i][j] != null) {
                    colorizeAndPrintValue(model[i][j].toString(), ANSI_RED);
                } 
            }
            System.out.println();
        }
    }
    
    private void colorizeAndPrintValue(String value, String color) {
        System.out.printf("%19s |", color + value + ANSI_CLEAR);
    }
    
    public void fillModel() {
        int shift = currentDate.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
        shift--;
        int countDaysInMonth = currentDate.lengthOfMonth();
        int numOfDayInMonth = 1;
        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (i == 0 && j >= shift || i > 0 && numOfDayInMonth <= countDaysInMonth) { 
                    model[i][j] = numOfDayInMonth; 
                    numOfDayInMonth++;
                }
            }
        }
    }
}
