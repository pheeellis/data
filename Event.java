/**
 * HW#2 Solution: Calendar simulation
 *
 * @author phyllislau
 * @version 1.0
 * @since 10/10/15
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A class the simulates an event with a title, date, and time
 */
public class Event implements Serializable
{
    private String            title;
    private GregorianCalendar date;
    private String            startTime;
    private String            endTime;
    private GregorianCalendar startTimeG;
    private GregorianCalendar endTimeG;

    private String[]          months = {"January", "February", "March", "April", "May", "June", "July",
                                        "August", "September", "October", "November", "December"};
    private MyCalendar.DAYS[] days   = MyCalendar.DAYS.values();

    private final String[] shortMonths = {"Jan", "Feb", "March", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};


    public Event(String title, String date, String startTime, String endTime)
    {
        this.title = title;
        int month = Integer.parseInt(date.substring(0, 2)) - 1;
        int actualDate = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, date.length()));
        this.date = new GregorianCalendar(year, month, actualDate);
        this.startTime = startTime;
        this.endTime = endTime;

        int hour = Integer.parseInt(startTime.substring(0, 2));
        int min = Integer.parseInt(startTime.substring(3, startTime.length()));
        startTimeG = new GregorianCalendar(year, month, actualDate, hour, min);

        int hour1 = Integer.parseInt(endTime.substring(0, 2));
        int min1 = Integer.parseInt(endTime.substring(3, endTime.length()));
        endTimeG = new GregorianCalendar(year, month, actualDate, hour1, min1);
    }

    /**
     * Gets the title of the Event
     *
     * @return the title of the event
     */
    public String getTitle()
    {

        return title;
    }

    /**
     * Get Date of Event
     *
     * @return the date of the Event
     */
    public GregorianCalendar getDate()
    {
        return date;
    }

    /**
     * Gets the start time of the event
     *
     * @return the start time of the event
     */
    public String getStartTime()
    {
        return startTime;
    }


    /**
     * Gets the end time of the event
     *
     * @return the end time of the event
     */
    public String getEndTime()
    {
        return endTime;
    }

    /**
     * Returns a string containing information about the event
     */
    public String toString()
    {
        if(endTime == null)
        {
            return "  " + days[date.get(Calendar.DAY_OF_WEEK) - 1] + " " + shortMonths[date.get(Calendar.MONTH)] + " " + date.get(Calendar.DAY_OF_MONTH) + " " +
                   this.startTime + " " + this.title;
        }
        else
        {
            return "  " + days[date.get(Calendar.DAY_OF_WEEK) - 1] + " " + shortMonths[date.get(Calendar.MONTH)] + " " + date.get(Calendar.DAY_OF_MONTH) + " " +
                   this.startTime + " - " + this.endTime + " " + this.title;
        }
    }

    /**
     * Gets the day of the Event
     *
     * @return a string containing the day of the event
     */
    public String getDay()
    {
        return String.valueOf(days[date.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    /**
     * Gets the name of the month of the event
     *
     * @return a string containing the month of the event
     */
    public String getMonthName()
    {
        return String.valueOf(months[date.get(Calendar.MONTH)]);
    }

    /**
     * Gets the date of the event
     *
     * @return a string containing the exact numeric date of the event
     */
    public String getDateNumber()
    {
        return String.valueOf(date.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Gets the year of the event
     *
     * @return a string containing the year of which the event takes place
     */
    public String getYear()
    {
        return String.valueOf(date.get(Calendar.YEAR));
    }

    public GregorianCalendar getStartTimeG()
    {
        return startTimeG;
    }

    public GregorianCalendar getEndTimeG()
    {
        return endTimeG;
    }

    /**
     * Formats the date into MM/dd/yyyy for the date of the event
     *
     * @param calendar the calendar to set the dates in this format
     * @return a string containing the date in MM/dd/yyyy form
     * @precondition calendar != null
     * @postcondition the dates of the calendar are now formatted in MM/dd/yyyy
     */
    public String format(GregorianCalendar calendar)
    {
        SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
        f.setCalendar(calendar);
        String dateFormatted = f.format(calendar.getTime());
        return dateFormatted;
    }
}
