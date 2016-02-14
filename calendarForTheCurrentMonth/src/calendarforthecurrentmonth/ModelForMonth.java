package calendarforthecurrentmonth;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class ModelForMonth {
    public static final String ANSI_BLACK = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
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
            System.out.print(String.format("%14s |", day));
        }
        System.out.println();
        for (int i = 0; i < model.length; i++) {
            for (int j = 0; j < model[i].length; j++) {
                if (model[i][j] != null && String.valueOf(currentDay).equals(model[i][j].toString())) {
                    model[i][j] = ANSI_GREEN + model[i][j] + ANSI_BLACK;
                } 
                if (j <  model[i].length - 2) {
                    model[i][j] =  model[i][j] != null ? model[i][j] : ""; 
                }else {
                    model[i][j] =  model[i][j] != null ? ANSI_RED  + model[i][j] + ANSI_BLACK : "";
 
                }
                System.out.print(String.format("%14s |", model[i][j]));
            }
            System.out.println();
        }
  
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
