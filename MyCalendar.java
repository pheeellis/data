/**
 * HW#2 Solution: Calendar simulation
 *
 * @author phyllislau
 * @version 1.0
 * @since 10/10/15
 */

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

/**
 * A class the simulates a Calendar with multiple events
 */
public class MyCalendar
{

    private static GregorianCalendar today;

    private final MONTHS[] months = MONTHS.values();
    private ArrayList<ChangeListener> listeners;

    public MyCalendar()
    {
        today = new GregorianCalendar();

        listeners = new ArrayList<>();
    }


    /**
     * Gets the calendar that corresponds to today
     *
     * @return the calendar that corresponds to today
     */
    public GregorianCalendar getToday()
    {
        return today;
    }


    /**
     * Sets the calendar that corresponds to today to a specific calendar
     *
     * @param today the calendar to set to
     * @precondition today != null
     * @postcondition the calendar that corresponds to today is set to today
     */
    public void setToday(GregorianCalendar today)
    {
        MyCalendar.today = today;
        for(ChangeListener l : listeners)
        {
            ChangeEvent e = new ChangeEvent(this);
            l.stateChanged(e);
        }

    }




    /**
     * Resets the calendar after it has been modified
     *
     * @postcondition the calendar returns back to its initial today date
     */
    public void resetCal()
    {
        setToday(new GregorianCalendar());
    }


    public MONTHS[] getMonths()
    {
        return months;
    }

    public void addChangeListener(ChangeListener c)
    {
        listeners.add(c);
    }


    public void previous()
    {
        today.add(Calendar.DAY_OF_MONTH, -1);
        setToday(today);
        for(ChangeListener l : listeners)
        {
            ChangeEvent e = new ChangeEvent(this);
            l.stateChanged(e);
        }
    }

    public void next()
    {
        today.add(Calendar.DAY_OF_MONTH, 1);
        setToday(today);
        for(ChangeListener l : listeners)
        {
            ChangeEvent e = new ChangeEvent(this);
            l.stateChanged(e);
        }
    }

    public enum MONTHS
    {
        Jan("January"),
        Feb("February"),
        Mar("March"),
        Apr("April"),
        May("May"),
        June("June"),
        July("July"),
        Aug("August"),
        Sep("September"),
        Oct("October"),
        Nov("November"),
        Dec("December");

        private String fullMonth;


        MONTHS(String month)
        {
            fullMonth = month;

        }

        public String toString()
        {
            return fullMonth;

        }

    }

    public enum DAYS
    {
        Sun("Sunday"),
        Mon("Monday"),
        Tue("Tuesday"),
        Wed("Wednesday"),
        Thur("Thursday"),
        Fri("Friday"),
        Sat("Saturday");

        private String fullDay;

        DAYS(String day)
        {
            fullDay = day;
        }

        public String toString()
        {
            return fullDay;
        }
    }
}


