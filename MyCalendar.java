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

enum MONTHS
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


    private MONTHS(String month)
    {
        fullMonth = month;

    }

    public String toString()
    {
        return fullMonth;

    }

}

enum DAYS
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

/**
 * A class the simulates a Calendar with multiple events
 */
public class MyCalendar
{

    private static GregorianCalendar                              today;
    private static SortedMap<GregorianCalendar, ArrayList<Event>> events;
    private final MONTHS[] months = MONTHS.values();
    //private CalendarPrinter p;
    private ArrayList<ChangeListener> listeners;

    public MyCalendar()
    {
        today = new GregorianCalendar();
        //	p = new CalendarPrinter();
        setEvents(new TreeMap<GregorianCalendar, ArrayList<Event>>());
        listeners = new ArrayList<ChangeListener>();
    }


    /**
     * Prints today's calendar
     * @param c the calendar that has information on today
     * @precondition c != null
     */
    public void print(Calendar c)
    {
//		new CalendarPrinter().printToday(c);
    }


    /**
     * Gets the calendar that corresponds to today
     * @return the calendar that corresponds to today
     */
    public GregorianCalendar getToday()
    {
        return today;
    }


    /**
     * Sets the calendar that corresponds to today to a specific calendar
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
     * Gets all the events that the calendar includes
     * @return a sortedmap containing dates with their corresponds events
     */
    public static SortedMap<GregorianCalendar, ArrayList<Event>> getEvents()
    {
        return MyCalendar.events;
    }

    /**
     * Adds an event to the calendar and sorts by date and time
     * @param e the event to add to
     * @precondition e != null
     * @postcondition the event is added to the calendar
     */
    public void addEvent(Event e)
    {
        GregorianCalendar realDate = e.getDate();
        ArrayList<Event> eventsOfDay = null;
        if(!events.containsKey(realDate))
        {
            eventsOfDay = new ArrayList<>();
            eventsOfDay.add(e);
            events.put(realDate, eventsOfDay);
        }
        else
        {
            eventsOfDay = events.get(realDate);
            eventsOfDay.add(e);

            int i = eventsOfDay.size() - 2;
            int inputHour = Integer.parseInt(e.getStartTime().substring(0, 2));
            int inputMinute = Integer.parseInt(e.getStartTime().substring(3, 5));
            int existingHour = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(0, 2));
            int existingMinute = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(3, 5));

            while(inputHour < existingHour && i > 0)
            {
                i--;
                existingHour = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(0, 2));
            }
            Event p = eventsOfDay.get(eventsOfDay.size() - 1);
            eventsOfDay.remove(eventsOfDay.size() - 1);
            if(inputHour < existingHour)
            {

                eventsOfDay.add(0, p);

            }
            else if(inputHour == existingHour)
            {
                if(inputMinute < existingMinute)
                {
                    events.get(realDate).add(i, p);
                }
                else
                {
                    events.get(realDate).add(i + 1, p);
                }
            }
            else if(inputHour > existingHour)
            {
                events.get(realDate).add(i + 1, p);
            }

        }

    }

    /**
     * Sets the events maps to a specific map
     * @param events the event maps to set to
     * @precondition events != null
     * @postcondition the map containing events is set to events
     */
    public void setEvents(SortedMap<GregorianCalendar, ArrayList<Event>> events)
    {
        MyCalendar.events = events;
    }


    /**
     * Gets the CalendarPrinter associated with this calendar
     * @return a CalendarPrinter
     */
//	public CalendarPrinter getP() {
//		return p;
//	}

    /**
     * Sets the CalendarPrinter associated with this calendar
     * @param p the CalendarPrinter object to set to
     * @precondition p != null
     * @postcondition the CalendarPrinter associated with this calendar is set to p
     */
//	public void setP(CalendarPrinter p) {
//		this.p = p;
//	}

    /**
     * Resets the calendar after it has been modified
     *@postcondition the calendar returns back to its initial today date
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
}


