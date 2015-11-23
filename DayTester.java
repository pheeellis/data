import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class DayTester
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JButton create = new JButton("CREATE");
        EventModel m = new EventModel();
        MyCalendar c = new MyCalendar();

        DayView p = new DayView(c, m);

        m.addChangeListener(p);


        create.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame createEvent = new JFrame();
                createEvent.setSize(500, 120);


                int month = c.getToday().get(Calendar.MONTH) + 1;
                int day = c.getToday().get(Calendar.DAY_OF_MONTH);
                int year = c.getToday().get(Calendar.YEAR);
                String fulldate = month + "/" + day + "/" + year;
                JTextField dateString = new JTextField(fulldate);
                JTextField eventName = new JTextField("Event Name", 30);

                JPanel stuff = new JPanel();
                stuff.add(dateString, BorderLayout.NORTH);
                stuff.add(eventName, BorderLayout.CENTER);
                createEvent.add(stuff, BorderLayout.NORTH);
                JTextField startTime = new JTextField("Start Time", 15);
                createEvent.add(startTime, BorderLayout.WEST);
                JTextField endTime = new JTextField("End Time", 15);
                createEvent.add(endTime, BorderLayout.EAST);


                JButton save = new JButton("SAVE");
                save.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        Event event = new Event(eventName.getText(), fulldate, startTime.getText(), endTime.getText());
                        m.addEvent(event);

                        createEvent.dispose();

                    }
                });


                //Event e = new Event(eventName.getText(), startTime.getText(), );

                createEvent.add(save, BorderLayout.SOUTH);
                System.out.println(m.getEvent(c.getToday()));


                createEvent.setVisible(true);

            }

        });

        frame.add(create, BorderLayout.NORTH);
        frame.add(p, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
