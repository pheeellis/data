import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

public class EventModel
{
    private SortedMap<GregorianCalendar, ArrayList<Event>> events;
    private ArrayList<ChangeListener>                      listeners;

    public EventModel()
    {
        this.events = new TreeMap<GregorianCalendar, ArrayList<Event>>();
        this.listeners = new ArrayList<>();
    }

    //load
    //save

    public SortedMap<GregorianCalendar, ArrayList<Event>> getEvents()
    {
        return events;
    }

    public void setEvents(SortedMap<GregorianCalendar, ArrayList<Event>> events)
    {
        this.events = events;
    }

    public ArrayList<ChangeListener> getListeners()
    {
        return listeners;
    }

    public void setListeners(ArrayList<ChangeListener> listeners)
    {
        this.listeners = listeners;
    }

    public void addChangeListener(ChangeListener c)
    {
        listeners.add(c);
    }

    public ArrayList<Event> getEvent(GregorianCalendar c)
    {
        return events.get(c);
    }

    public String getAllEvents(GregorianCalendar c)
    {
        String eventString = "";
        for(Event e : events.get(c))
        {
            eventString += e.toString() + "\n";
        }
        return eventString;
    }

    public boolean noTimeConflict(Event e, ArrayList<Event> arr)
    {
        boolean value = true;

        for(Event ev : arr)
        {
            if(e.getStartTimeG().before(ev.getStartTimeG()) && (e.getEndTimeG().before(ev.getStartTimeG()) || e.getEndTimeG().equals(ev.getStartTimeG())))
            {
                value = true;
            }
            else if((e.getStartTimeG().after(ev.getEndTimeG()) || e.getStartTimeG().equals(ev.getEndTimeG())) && e.getEndTimeG().after(ev.getEndTimeG()))
            {
                value = true;
            }
            else
            {
                return false;
            }
        }
        return value;

    }

    public boolean addEvent(Event e)
    {
        boolean noConflict = false;
        GregorianCalendar realDate = e.getDate();
        System.out.println("eventmonth: " + realDate.get(Calendar.MONTH));
        ArrayList<Event> eventsOfDay = null;
        if(!events.containsKey(realDate))
        {
            eventsOfDay = new ArrayList<>();
            eventsOfDay.add(e);
            events.put(realDate, eventsOfDay);
            System.out.println("arraylist: " + eventsOfDay);
            System.out.println(events.get(realDate));

        }
        else
        {
            eventsOfDay = events.get(realDate);
            if(noTimeConflict(e, eventsOfDay))
            {

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
            else
            {
                noConflict = true;

            }
        }

        for(ChangeListener l : listeners)
        {
            l.stateChanged(new ChangeEvent(this));
        }

        return noConflict;
    }
}


