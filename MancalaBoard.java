import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * defines a mancala board frame object
 */
public class MancalaBoard extends JFrame implements ChangeListener {
	
	//numeber of pits for board game
	private int ALL_PITS = 14;
	private BoardStyle beez;
	//number of pits per player
	private int NUM_PITS = 6;
	private Model m;
	public int[] testArray = {0, 1, 2, 3, 4, 5, 6,   7, 8, 9, 10, 11, 12, 13};
	public JPanel pits;
	public JPanel topPanel;		
	public JPanel gBoard ;
	
	/*
	 * frame constructor
	 */
	public MancalaBoard(BoardStyle b, Model model)
	{	
		//STYLE
		this.m = model;
		this.beez = b; 
		this.m.addChangeListener(this);
		setSize(400,500);
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);
		makeFrame(b);
	}
	
	public void makeFrame(BoardStyle b)
	{
		//frame for game board
		//JFrame frame = new JFrame();
		
				
		//add labels for the pits to the frame for players A and B
		//make the game board and add it to the frame
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(makePlayerLabel("<----- Player B"), BorderLayout.NORTH);
		//topPanel.add(makeLabels("B"), BorderLayout.CENTER);
		topPanel.add(makeGameBoard(b), BorderLayout.SOUTH);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel undoPanel = new JPanel();
		undoPanel.setLayout(new BorderLayout());	
		//undoPanel.add(makeLabels("A"), BorderLayout.NORTH);
		undoPanel.add(makePlayerLabel("Player A ----->"), BorderLayout.CENTER);
		undoPanel.add(makeUndoBar(), BorderLayout.SOUTH);
		add(undoPanel, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * make the game board panel 
	 * @return gBoard JPanel a moncala game board with pits and moncala pits
	 */
	public JPanel makeGameBoard(BoardStyle b)
	{
		//game board panel
		gBoard = new JPanel();
		gBoard.setPreferredSize(new Dimension(700, 225));
		gBoard.setLayout(new BorderLayout());
		gBoard.setBackground(Color.lightGray);
						
		//add player pits + mancala pits to the game board JPanel
		gBoard.add(makeMancalaPit(b, false), BorderLayout.EAST);
		gBoard.add(makePits(b), BorderLayout.CENTER);
		gBoard.add(makeMancalaPit(b, true), BorderLayout.WEST);
		return gBoard;
	}
	
	/*
	 * make labels panel
	 * @player the player who the labels will belong too
	 * @return labels JPanel the labels panel for the given player
	 */
	public JPanel makeLabels(String player)
	{
		JPanel labels = new JPanel();
		labels.setLayout(new FlowLayout());
		
		labels.add(new JLabel("                                    "));
		for(int i = NUM_PITS; i > 0; i--)
		{
			String spacer = "                           ";
			String topPits = " " + player + String.valueOf(i) + spacer;
			labels.add(new JLabel(topPits));
		}
		labels.add(new JLabel("          "));
		return labels;
	}
	
	/*
	 * make the player pits for the game board
	 * @return pits JPanel for the game board
	 */
	public JPanel makePits(BoardStyle b)
	{
		pits = new JPanel();
		pits.setLayout(new GridLayout(2, 6));
		
		for(int j = 0; j < 12; j ++)
		{
			String id;
			final JButton pit;
			final Pit myP; 
			final PitIcon pitter;
			if (j < 6)
			{
				id = "B" + "" + String.valueOf(String.valueOf((11-j)%6+1));
				myP = new Pit(b, id, 75, m.get(12-j).getStonesFromPit(), 12-j, false);
				pitter = new PitIcon(myP);
				pit = new JButton(pitter);
				
				pit.setText(id);
				pit.setHorizontalTextPosition(JButton.CENTER);
				pit.setVerticalTextPosition(JButton.NORTH);
				   
				//pit.setName(String.valueOf(11-j));
			}
			else
			{
				id =  "A" + "" + String.valueOf((j-6) +1);
				myP = new Pit(b, id, 75, m.get(j-6).getStonesFromPit(), j-6, true);
				pitter = new PitIcon(myP);
				pit = new JButton(pitter);
				
				pit.setText(id);
				pit.setHorizontalTextPosition(JButton.CENTER);
				pit.setVerticalTextPosition(JButton.NORTH);
				
			}
			
			pit.addActionListener(new ActionListener()	{
				@Override
				public void actionPerformed(ActionEvent e) {
					String pName = myP.getPitId();
					int sInPit = myP.getStonesFromPit();
					int indexOfP = myP.getIndexOfPit();
					
					System.out.println("pitName: " + pName + ", #stonesInPit: " + sInPit + ", pitsIndexInArray: " + indexOfP);
					
					//myP.stonesPickedUp();  // sets current num of stones to 0, simulates stones picked up fromm pit when clicked on 
					m.moveStones(indexOfP);
					//m.setIndex(myP.getIndexOfPit(), myP.getStonesFromPit());
					pitter.update();
					
					pit.setIcon(new PitIcon(myP));
				}
			});
			pits.add(pit);
		}
		return pits;
	}
	
	/*
	 * make mancala pit 
	 * @return a mancala pit JPanel
	 */
	public JLabel makeMancalaPit(BoardStyle b, Boolean p)
	{
		JLabel mancalaPit;
		if(p == false)	{
			mancalaPit = new JLabel(new MancalaPit(b, 170, m.get(6).stonesInPit));
		}
		else	{
			mancalaPit = new JLabel(new MancalaPit(b, 170, m.get(13).stonesInPit));
		}
		
		//
		
		//mancalaPit = new JLabel(new MPit(null, null, 0, 0, i, false));
				//new MancalaPit(b , 170, 0));
		return mancalaPit;
	}
	
	/*
	 * make player label
	 * @param s the string that defines one of the players
	 * @return label a JLabel of player name
	 */
	public JLabel makePlayerLabel(String s)
	{
		JLabel label = new JLabel(s, SwingConstants.CENTER);
		return label;
	}
	
	public JPanel makeUndoBar()
	{
		JPanel undoBar = new JPanel();
		undoBar.setLayout(new BorderLayout());
		
		JButton undo = new JButton("Undo Last Move");
		undoBar.add(undo, BorderLayout.EAST);
		undoBar.add(new JLabel("         player turn         "), BorderLayout.WEST);
		undoBar.add(new JLabel("         # of undo's used/left         "), BorderLayout.CENTER);
		
		return undoBar;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		//setVisible(false);
		m.getPits();
		topPanel.setVisible(false);
		//refreshPits(beez);
		makeFrame(beez);
		//revalidate();
		//repaint();
		//makePits(beez);
		//pits.repaint();
	}
}
