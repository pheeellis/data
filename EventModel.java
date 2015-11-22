import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class EventModel 
{
	private SortedMap<GregorianCalendar, ArrayList<Event>> events;
	private ArrayList<ChangeListener> listeners;
	public EventModel()
	{
		setEvents(new TreeMap<GregorianCalendar, ArrayList<Event>>());
		setListeners(new ArrayList<ChangeListener>());
	}
	public SortedMap<GregorianCalendar, ArrayList<Event>> getEvents() {
		return events;
	}
	public void setEvents(SortedMap<GregorianCalendar, ArrayList<Event>> events) {
		this.events = events;
	}
	public ArrayList<ChangeListener> getListeners() {
		return listeners;
	}
	public void setListeners(ArrayList<ChangeListener> listeners) {
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
	
	
	public void addEvent(Event e)
	{
		GregorianCalendar realDate = e.getDate();
		ArrayList<Event> eventsOfDay = null;
		if (!events.containsKey(realDate))
		{
			eventsOfDay = new ArrayList<>();
			eventsOfDay.add(e);
			events.put(realDate, eventsOfDay);
			System.out.println("arraylist: " + eventsOfDay);
			System.out.println(events.get(realDate));
		}
//		else
//		{
//			eventsOfDay = events.get(realDate);
//			eventsOfDay.add(e);
//			
//			int i = eventsOfDay.size()-2;
//			int inputHour = Integer.parseInt(e.getStartTime().substring(0, 2));
//			int inputMinute = Integer.parseInt(e.getStartTime().substring(3,5));
//			int existingHour = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(0,2));
//			int existingMinute = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(3,5));
//			
//			while (inputHour < existingHour && i > 0)
//			{
//				i--;
//				existingHour = Integer.parseInt(eventsOfDay.get(i).getStartTime().substring(0,2));
//			}
//			Event p = eventsOfDay.get(eventsOfDay.size()-1);
//			eventsOfDay.remove(eventsOfDay.size()-1);
//			if (inputHour < existingHour) 
//			{
//				
//				eventsOfDay.add(0, p);
//				
//			}
//			else if (inputHour == existingHour)
//			{
//				if (inputMinute < existingMinute)
//				{
//					events.get(realDate).add(i,p);
//				}
//				else
//				{
//					events.get(realDate).add(i+1, p);
//				}
//			}
//			else if (inputHour > existingHour)
//			{
//				events.get(realDate).add(i+1, p);
//			}
//
//		}
		for (ChangeListener l: listeners)
		{
			l.stateChanged(new ChangeEvent(this));
		}
	
	}
	
}
