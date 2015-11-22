/**
 * HW#2 Solution: Calendar simulation 
 * @author phyllislau
 * @since 10/10/15
 * @version 1.0
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A class the simulates an event with a title, date, and time
 */
public class Event 
{
	private String title;
	private GregorianCalendar date;
	private String startTime;
	private String endTime;
	private String[] months = {"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	private DAYS[] days = DAYS.values();
	private final String[] shortMonths = {"Jan", "Feb", "March", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
			
	

	public Event(String title, String date, String startTime, String endTime)
	{
		this.title = title;
		int month = Integer.parseInt(date.substring(0, 2))-1;
		int actualDate = Integer.parseInt(date.substring(3,5));
		int year = Integer.parseInt(date.substring(6,date.length()));
		this.date = new GregorianCalendar(year, month, actualDate);
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Gets the title of the Event
	 * @return the title of the event
	 */
	public String getTitle() {
		
		return title;
	}

	/**
	 * Sets the title of the event
	 * @param title the title of the event to set to
	 * @precondition title.length() > 1
	 * @postcondition title of event is now set to title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get Date of Event
	 * @return the date of the Event
	 */
	public GregorianCalendar getDate() {
		return date;
	}

	/**
	 * Sets the date of the event
	 * @param date the date to set the event to
	 * @precondition date != null
	 * @postcondition date of event is now set to date
	 */
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}


	/**
	 * Gets the start time of the event
	 * @return the start time of the event
	 */
	public String getStartTime() {
		return startTime;
	}


	/**
	 * Set the start time of the event
	 * @param startTime the time to set the event to start at
	 * @precondition startTime.length() > 0
	 * @postcondition the start time of the event is now set to startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time of the event
	 * @return the end time of the event
	 */
	public String getEndTime() {
		return endTime;
	}
	
	/**
	 * Sets the end time of the event
	 * @param endTime the end time of the event to set to
	 * @precondition endTime.length() > 1
	 * @postcondition the end time of the event is now set to endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Returns a string containing information about the event
	 * @param a string containing information about the specific event
	 */
	public String toString()
	{
		if (endTime == null)
		{
			return "  " + days[date.get(Calendar.DAY_OF_WEEK)-1] + " " + shortMonths[date.get(Calendar.MONTH)] + " " + date.get(Calendar.DAY_OF_MONTH) + " "  +
					this.startTime + " " + this.title;
		}
		else
		{
			return "  " + days[date.get(Calendar.DAY_OF_WEEK)-1] + " " + shortMonths[date.get(Calendar.MONTH)] + " " + date.get(Calendar.DAY_OF_MONTH) + " "  +
							this.startTime + " - " + this.endTime + " " + this.title;
		}
 	}
	
	/**
	 * Gets the day of the Event
	 * @return a string containing the day of the event
	 */
	public String getDay()
	{
		return "" + days[date.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	/**
	 * Gets the name of the month of the event
	 * @return a string containing the month of the event
	 */
	public String getMonthName()
	{
		return "" + months[date.get(Calendar.MONTH)];
	}
	
	/**
	 * Gets the date of the event
	 * @return a string containing the exact numeric date of the event
	 */
	public String getDateNumber()
	{
		return "" + date.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Gets the year of the event
	 * @return a string containing the year of which the event takes place
	 */
	public String getYear()
	{
		return "" + date.get(Calendar.YEAR);
	}
	
	/**
	 * Prints in a specific format for the file events.txt
	 * @return a string containing information about the event formatted in a specific way
	 */
	public String printForFile()
	{
		String datee = format(date);
		if (endTime == null)
		{
		return String.format(datee +"%n" + this.startTime + "%n" + this.title);
		}
			return String.format(datee +"%n" + this.startTime + "-" + this.endTime + "%n" + this.title);
		}
				

	
	/**
	 * Formats the date into MM/dd/yyyy for the date of the event
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
