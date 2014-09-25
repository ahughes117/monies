package util;

import entities.Entity;
import entities.Expense;
import java.util.ArrayList;
import org.joda.time.DateMidnight;
import org.joda.time.Days;

/**
 * The Calc utility class contains helper functions for calculating sums and
 * averages
 *
 * @author ahughes
 */
public class Calc {

    /**
     * Calculates the sum for a list of expenses
     *
     * @param anEntityL
     * @return
     */
    public static double calculateSum(ArrayList<Entity> anEntityL) {
        double sum = 0;

        //iterating and summing
        for (Entity e : anEntityL) {
            if (e instanceof Expense) {
                sum += ((Expense) e).getAmount();
            }
        }
        return sum;
    }

    /**
     * Calculates averages. The average for a single day is the average of all
     * expenses. The average for a week for instance, is the average amount of
     * money per day
     *
     * @param anEntityL
     * @param isDayExpenses
     * @return
     */
    public static double calculateAverage(ArrayList<Entity> anEntityL, boolean isDayExpenses) {
        double avg = 0;
        double sum = 0;
        int dayDiff = 1;

        for (Entity e : anEntityL) {
            if (e instanceof Expense) {
                sum += ((Expense) e).getAmount();
            }
        }

        //to avoid performing a division by zero
        if (!anEntityL.isEmpty()) {
            //the average of a day is the average of the expense, instead of the average amount per day
            if (isDayExpenses) {
                avg = sum / anEntityL.size();
            } else {
                dayDiff = calculateDayDiff(anEntityL);

                //preventing bad things from happening
                if (dayDiff == 0) {
                    dayDiff = 1;
                }
                
                avg = sum / dayDiff;
            }
        }
        return avg;
    }

    /**
     * Calculates the CALENDAR day difference between two dates
     *
     * @param anEntityL
     * @return
     */
    private static int calculateDayDiff(ArrayList<Entity> anEntityL) {
        int dayDiff = 1;
        java.util.Date firstDate, lastDate;
        Expense exp;

        //quick-fetching the first expense and assign it as the first and the last at the same time
        exp = (Expense) anEntityL.get(0);

        firstDate = exp.getDate();
        lastDate = exp.getDate();

        //iterating and setting the real min and max timestamp
        for (Entity e : anEntityL) {
            exp = (Expense) e;

            if (exp.getDate().getTime() < firstDate.getTime()) {
                firstDate = exp.getDate();
            }

            if (exp.getDate().getTime() > lastDate.getTime()) {
                lastDate = exp.getDate();
            }
        }
        dayDiff = Days.daysBetween(new DateMidnight(firstDate), new DateMidnight(lastDate)).getDays();

        return dayDiff;
    }

}
