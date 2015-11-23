import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SimpleCalendar
{
    public static void main(String[] args)
    {

        MyCalendar.DAYS[] days = MyCalendar.DAYS.values();
        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        JButton create = new JButton("CREATE");
        JButton previous = new JButton("<");
        JButton next = new JButton(">");
        JButton quit = new JButton("QUIT");
        JPanel buttons = new JPanel();
        buttons.add(create);
        buttons.add(previous);
        buttons.add(next);
        buttons.add(quit);
        EventModel m = new EventModel();
        MyCalendar c = new MyCalendar();

        MyCalendar cal = new MyCalendar();
        CalTable calTable = new CalTable(cal);
        frame.add(calTable, BorderLayout.WEST);


        c.addChangeListener(calTable);


        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel(days[c.getToday().get(Calendar.DAY_OF_WEEK) - 1] + " " + (c.getToday().get(Calendar.MONTH) + 1) + "/"
                                  + c.getToday().get(Calendar.DAY_OF_MONTH));
        label.setHorizontalAlignment(JLabel.CENTER);
        JTextArea sched = new JTextArea(15, 20);

        ChangeListener l = (event) -> {

        };

        p.add(label); //, BorderLayout.NORTH);
        p.add(sched); //, BorderLayout.CENTER);
        m.addChangeListener(l);
        c.addChangeListener(l);


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
                        if(m.addEvent(event))
                        {
                            JFrame error = new JFrame();
                            error.setSize(100, 50);
                            JLabel message = new JLabel("Please enter an event without time conflict");
                            error.add(message, BorderLayout.NORTH);
                            JButton okay = new JButton("CLOSE");
                            okay.addActionListener(new ActionListener()
                            {
                                public void actionPerformed(ActionEvent e)
                                {
                                    error.dispose();
                                    createEvent.setVisible(true);
                                    eventName.setText("Event Name");
                                    startTime.setText("Start Time");
                                    endTime.setText("End Time");

                                }

                            });
                            error.add(okay, BorderLayout.CENTER);
                            error.pack();
                            error.setVisible(true);

                        }
                        System.out.println("eventdate: " + event.getDate().get(Calendar.MONTH));
                        createEvent.dispose();

                    }
                });


                //Event e = new Event(eventName.getText(), startTime.getText(), );

                createEvent.add(save, BorderLayout.SOUTH);
                //System.out.println(m.getEvent(c.getToday()));


                createEvent.setVisible(true);

            }

        });

        frame.add(buttons, BorderLayout.NORTH);
        frame.add(p, BorderLayout.CENTER);


        //frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
