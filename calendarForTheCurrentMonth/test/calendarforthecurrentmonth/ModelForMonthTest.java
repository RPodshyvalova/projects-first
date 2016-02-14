package calendarforthecurrentmonth;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModelForMonthTest {
    @Test
    public void testFillModel() {
        System.out.println("Test fillModel method");
        LocalDate date = LocalDate.of(2016, Month.FEBRUARY, 12);
        ModelForMonth instance = new ModelForMonth(date);
        instance.fillModel();
        Object [][] model = instance.getModel();
        int numFirstDayOfMonthInWeek = defineNumberDay(model);
        System.out.println(defeneWeekdayOfFirstDayOfMonth(date));
        assertEquals(1, numFirstDayOfMonthInWeek);
        assertEquals(29, defineCountDaysInMonth(model));
        
        date = LocalDate.of(2016, Month.APRIL, 12);
        instance = new ModelForMonth(date);
        instance.fillModel();
        model = instance.getModel();
        numFirstDayOfMonthInWeek = defineNumberDay(model);
        System.out.println(defeneWeekdayOfFirstDayOfMonth(date));
        assertEquals(5, numFirstDayOfMonthInWeek);
        assertEquals(30, defineCountDaysInMonth(model));
        
        
        date = LocalDate.of(2015, Month.DECEMBER, 12);
        instance = new ModelForMonth(date);
        instance.fillModel();
        model = instance.getModel();
        numFirstDayOfMonthInWeek = defineNumberDay(model);
        System.out.println(defeneWeekdayOfFirstDayOfMonth(date));
//        assertEquals(5, numFirstDayOfMonthInWeek);
        assertEquals(2, numFirstDayOfMonthInWeek);
        assertEquals(31, defineCountDaysInMonth(model));
    }
    
    int defineNumberDay(Object [][] model) {
        int j = 0;
        while (model[0][j] == null && j < model.length) {
            j++;
        }
        return j == 0 ? 1 : j + 1;
    }
    
    int defineCountDaysInMonth(Object [][] model) {
        int count = 0;
        for (Object[] row : model) {
            for (Object object : row) {
                if (object != null) {
                    count++;
                }
            }
        }
        return count;
    }
    
    int defeneWeekdayOfFirstDayOfMonth(LocalDate date){
        return date.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
    }
}
