import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
public class MancalaBoard implements ChangeListener {
	
	//Number of pits for board game
	private int ALL_PITS = 14;
	private BoardStyle beez;
	//number of pits per player
	private int NUM_PITS = 6;
	private Model m;
	public int[] testArray = {0, 1, 2, 3, 4, 5, 6,   7, 8, 9, 10, 11, 12, 13};
	public JFrame frame;
	public JPanel pits;
	public JPanel topPanel;		
	public JPanel gBoard ;
	public JPanel undoPanel;
	
	/* frame constructor for mancala board frame
	 * 
	 * @param b the boardstyle to implement for the mancala board game 
	 * @param model the data object for the mancala board game 
	 */
	public MancalaBoard(BoardStyle b, Model model)
	{	
		//STYLE
		this.m = model;
		this.beez = b; 
		this.m.addChangeListener(this);
		
		//frame for game board
		frame = new JFrame();
		frame.setSize(400,500);
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.lightGray);
		makeFrame(b);
	}
	
	
	/* adds to the main frame of the mancala board game 
	 * 
	 * @param b the boardstyle of the mancala booard game 
	 */
	public void makeFrame(BoardStyle b)
	{
		//add labels for the pits to the frame for players A and B
		//make the game board and add it to the frame
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(makePlayerLabel("<----- Player B"), BorderLayout.NORTH);
		topPanel.add(makeGameBoard(b), BorderLayout.SOUTH);
		frame.add(topPanel, BorderLayout.NORTH);
		
		undoPanel = new JPanel();
		undoPanel.setLayout(new BorderLayout());	
		undoPanel.add(makePlayerLabel("Player A ----->"), BorderLayout.CENTER);
		undoPanel.add(makeUndoBar(), BorderLayout.SOUTH);
		frame.add(undoPanel, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	/* makes the panel that is the mancala board game 
	 * 
	 * @param b the boardstyle of the mancala board 
	 * @return gBoard JPanel a mancala game board with pits and mancala pits
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
	
	/* make labels panel
	 * 
	 * @param player the player who the labels will belong too
	 * @return labels JPanel the label for the given players pits 
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
	
	/* make the player pits for the gameBoard
	 * 
	 * @return pits JPanel a JPanel containing pits for both players
	 */
	public JPanel makePits(BoardStyle b)
	{
		int stones;
		int in;
		boolean p;
		
		pits = new JPanel();
		pits.setLayout(new GridLayout(2, 6));
		
		for(int j = 0; j < 12; j ++)
		{
			String id;
			final JButton pit;
			final PitIcon pitter;
			if (j < 6)
			{
				id = "B" + "" + String.valueOf(String.valueOf((11-j)%6+1));
				stones = m.get(12-j).getStonesFromPit();
				in = 12-j;
				p = false;
				
				pitter = new PitIcon(stones, in, p, b, 75);
				pit = new JButton(pitter);
				
				pit.setText(id);
				pit.setHorizontalTextPosition(JButton.CENTER);
				pit.setVerticalTextPosition(JButton.NORTH);
			}
			else
			{
				id =  "A" + "" + String.valueOf((j-6) +1);
				stones = m.get(j-6).getStonesFromPit();
				in = j-6;
				p = true;
				
				pitter = new PitIcon(stones, in, p, b, 75);
				pit = new JButton(pitter);
				
				pit.setText(id);
				pit.setHorizontalTextPosition(JButton.CENTER);
				pit.setVerticalTextPosition(JButton.NORTH);
			}
			
			pit.addActionListener(new ActionListener()	{
				@Override
				public void actionPerformed(ActionEvent e) {
					int sInPit = pitter.getStonesFromPit();
					int indexOfP = pitter.getIndexOfPit();
					//System.out.println("#stonesInPit: " + sInPit + ", pitsIndexInArray: " + indexOfP);
	
					m.moveStones(indexOfP);
				}
			});
			pits.add(pit);
		}
		return pits;
	}
	
	/* makes a mancala pit for the given player  
	 * 
	 * @param b the boardstyle to implement the moncala pit by
	 * @param p the player whom the mancala pit will belong too
	 * @return mancalaPit a JPanel with a mancalaPit in it 
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
		return mancalaPit;
	}
	
	/* make player label
	 * 
	 * @param s the string that defines player A or player B
	 * @return label a JLabel with the given player name
	 */
	public JLabel makePlayerLabel(String s)
	{
		JLabel label = new JLabel(s, SwingConstants.CENTER);
		return label;
	}
	
	/* makes a JPanel that houses the undo button as states players turn and 
	 * how many undo's are left for the current player 
	 * 
	 * @return undoBar a JPanel with an undo button and information about the current state of the game 
	 */
	public JPanel makeUndoBar()
	{
		JPanel undoBar = new JPanel();
		undoBar.setLayout(new BorderLayout());
		
		Boolean cPlayer = m.whichPlayer();
		String pFiller;
		int undoC = m.getUndoCount();
		String spacer = "          ";
		
		if(cPlayer)	{
			pFiller = "  Player A's Turn" + spacer;
		}
		else	{
			pFiller = "  Player B's Turn" + spacer;
		}
		
		JButton undo = new JButton("Undo Last Move");
		undo.addActionListener(new ActionListener()	{
			@Override
			public void actionPerformed(ActionEvent e) {
				m.undo();
			}		});
		
		String undoLabel =  spacer + "# of undo's left: " + String.valueOf(3 - undoC);
		
		undoBar.add(undo, BorderLayout.EAST);
		undoBar.add(new JLabel(pFiller), BorderLayout.WEST);
		undoBar.add(new JLabel(undoLabel), BorderLayout.CENTER);
		return undoBar;
	}

	/*
	 *  {@inheritDoc}
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		m.getPits();
		topPanel.setVisible(false);
		undoPanel.setVisible(false);
		makeFrame(beez);
	}
}
