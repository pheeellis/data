import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class DayView extends JPanel implements ChangeListener
{
	private MyCalendar c;
	private int d;
	private int m;
	private JLabel dayviewString;
	private JTextArea daySched;
	private EventModel em;
	
	public DayView(MyCalendar c, EventModel e)
	{
		this.c = c;
		this.em = e;
		
		DAYS[] days = DAYS.values();
		
		
		String dayString = days[c.getToday().get(Calendar.DAY_OF_WEEK)-1].toString();
		d = c.getToday().get(Calendar.DAY_OF_MONTH);
		System.out.println("d " + d);
		m = c.getToday().get(Calendar.MONTH)+1 ;
		System.out.println("m " + m);
		dayviewString = new JLabel(dayString + " " + m + "/" + d);
		dayviewString.setHorizontalAlignment(JLabel.CENTER);
		
		 daySched = new JTextArea(15,15);
		
		
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		 //, BoxLayout.PAGE_AXIS);
		add(dayviewString);
		add(daySched);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
//		d = c.getToday().get(Calendar.DAY_OF_MONTH);
//		System.out.println("dchanged" + d);
//		m = c.getToday().get(Calendar.MONTH)+1 ;
//		System.out.println("mchanged" + m);
//		System.out.println("repaint");
//		
		
		
//		String events = "";
//		
//	//	System.out.println(em.getEvents().get(c.getToday()).size());
//		System.out.println(c.getToday().get(Calendar.DAY_OF_MONTH));
//		System.out.println(em.getEvent(c.getToday()));
//		for (Event event: em.getEvent(c.getToday()))
//		{
//			events = events += event.toString() + "/n";
//			daySched.setText(events);
//		}
		
		
		
		System.out.println(em.getEvent(c.getToday()));
	}
	
	
}
