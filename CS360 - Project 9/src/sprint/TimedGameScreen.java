package sprint;
/**
* GUI file for the timed format of the game.
* @author  Michael Wolgast
* @version 1.0
* @since   2017-13-18 
*/
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import javax.swing.GroupLayout.Alignment;


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



public class TimedGameScreen extends JFrame implements Observer{
	private JButton[][] tiles;
	private JButton offButton;
	private JButton backGround1;
	private JButton backGround2;
	private JButton backGround3;
	private JButton quitButton;
	private JButton resetButton;
	private JButton hintButton;
	private JButton btnClear;
	private JComboBox comboBox;
	private AudioStream music1;
  private AudioStream music2;
  private AudioStream music3;
	private AudioClip sound1;
	private AudioClip sound2;
	private AudioClip sound3;
	TimedGame gameDriver;
	JPanel panelC;
	JPanel panelN;
	JPanel panelS;
	JPanel panelB;
	JPanel panelW;
	JPanel comboPanel;
	JLabel timeLabel;
	JLabel queueN;
	JLabel[] queueT;
	int moveScore;
	int hintNum;
	JLabel scoreLabel;
	JLabel movesLabel;
	JLabel msLabel;
	JLabel hints;
	JLabel hintLabel;
	private JButton debugButton;
	
	public TimedGameScreen() {
		setTitle("Sum Fun 0.98");
		moveScore = 0;
		gameDriver = TimedGame.getTimedGame();
		gameDriver.addObserver(this);
		tiles = new JButton[9][9];
		
		setSize(1024, 768);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelC = new JPanel();
		panelC.setBackground(new Color(178, 34, 34));
		getContentPane().add(panelC, BorderLayout.CENTER);
		
		panelN = new JPanel();
		panelN.setBackground(Color.BLACK);
		getContentPane().add(panelN, BorderLayout.NORTH);
		panelN.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		backGround1 = new JButton("Music 1");
		backGround1.setForeground(new Color(255, 255, 0));
		backGround1.setBackground(new Color(178, 34, 34));
		backGround1.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		backGround1.setToolTipText("Starts playing song 1");
		panelN.add(backGround1);
		
		backGround2 = new JButton("Music 2");
		backGround2.setBackground(new Color(178, 34, 34));
		backGround2.setForeground(Color.YELLOW);
		backGround2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		backGround2.setToolTipText("Starts playing song 2");
		panelN.add(backGround2);
		
		backGround3 = new JButton("Music 3");
		backGround3.setBackground(new Color(178, 34, 34));
		backGround3.setForeground(Color.YELLOW);
		backGround3.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		backGround3.setToolTipText("Starts playing song 2");
		panelN.add(backGround3);
		
		offButton = new JButton("Mute");
		offButton.setBackground(new Color(178, 34, 34));
		offButton.setForeground(Color.YELLOW);
		offButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		panelN.add(offButton);
		backGround1.addActionListener(new ButtonListener());
		backGround2.addActionListener(new ButtonListener());
		backGround3.addActionListener(new ButtonListener());
		offButton.addActionListener(new ButtonListener());
		quitButton = new JButton("Quit");
		quitButton.setForeground(Color.YELLOW);
		quitButton.setBackground(new Color(178, 34, 34));
		quitButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		quitButton.addActionListener(new ButtonListener());
		panelN.add(quitButton);
		
		debugButton = new JButton("Debug");
		debugButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		debugButton.setBackground(new Color(178, 34, 34));
		debugButton.setForeground(Color.YELLOW);
		debugButton.addActionListener(new ButtonListener());
		panelN.add(debugButton);
		
		JLabel label = new JLabel("                                                                                                                                                           ");
		panelN.add(label);
		
		JLabel queueDesc = new JLabel("Queue");
		queueDesc.setForeground(Color.YELLOW);
		queueDesc.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panelN.add(queueDesc);
		
		panelS = new JPanel();
		getContentPane().add(panelS, BorderLayout.SOUTH);
		
		panelB = new JPanel();
		panelB.setBackground(Color.BLACK);
		getContentPane().add(panelB, BorderLayout.EAST);
		queueN = new JLabel("Queue");
		
		buildPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createBoardGui();
		createQueueGui();
		gameDriver.newGame();
		
		setVisible(true);
		
		
	}
	public void buildPanel(){
	  try {
      music1 = new AudioStream(getClass().getResourceAsStream("/sounds/katyusha.wav"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      music2 = new AudioStream(getClass().getResourceAsStream("/sounds/rasputin.wav"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      music3 = new AudioStream(getClass().getResourceAsStream("/sounds/slav.wav"));
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
  }
		
	//Creates the queue and side GUI Elements.
	private void createQueueGui(){
		panelB.setLayout(null);
		GridBagLayout gblPanelB = new GridBagLayout();
		gblPanelB.columnWidths = new int[]{202, 0};
		gblPanelB.rowHeights = new int[] {202, 0, 180, 70, 202};
		gblPanelB.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gblPanelB.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panelB.setLayout(gblPanelB);
		
		JPanel panelE = new JPanel();
		panelE.setBounds(0, 0, 202, 137);
		panelE.setForeground(Color.YELLOW);
		panelE.setBackground(Color.BLACK);
		GridBagConstraints gbcPanelE = new GridBagConstraints();
		gbcPanelE.fill = GridBagConstraints.BOTH;
		gbcPanelE.insets = new Insets(0, 0, 5, 0);
		gbcPanelE.gridx = 0;
		gbcPanelE.gridy = 0;
		panelB.add(panelE, gbcPanelE);
		panelE.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 494, 202, 54);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(0, 0, 5, 0);
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		panelB.add(panel, gbcPanel);
		
		hintButton = new JButton("Hint?");
    hintButton.setForeground(Color.YELLOW);
    hintButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    hintButton.setBackground(new Color(178, 34, 34));
    hintButton.addActionListener(new ButtonListener());
    panel.add(hintButton);
		
		resetButton = new JButton("Reset Queue!");
		panel.add(resetButton);
		resetButton.setForeground(Color.YELLOW);
		resetButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		resetButton.setBackground(new Color(178, 34, 34));
		resetButton.addActionListener(new ButtonListener());
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 137, 202, 137);
		panel2.setBackground(Color.BLACK);
		GridBagConstraints gridBagcon = new GridBagConstraints();
		gridBagcon.fill = GridBagConstraints.BOTH;
		gridBagcon.insets = new Insets(0, 0, 5, 0);
		gridBagcon.gridx = 0;
		gridBagcon.gridy = 2;
		panelB.add(panel2, gridBagcon);
		panel2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("Time Left:   ");
		label.setBackground(Color.BLACK);
		label.setForeground(Color.YELLOW);
		label.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		panel2.add(label);
		
		msLabel = new JLabel("3:00");
		msLabel.setForeground(Color.YELLOW);
		msLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panel2.add(msLabel);
		
		JLabel label_2 = new JLabel("Score:");
		label_2.setForeground(Color.YELLOW);
		label_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		panel2.add(label_2);
		
		scoreLabel = new JLabel("0");
		scoreLabel.setForeground(Color.YELLOW);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panel2.add(scoreLabel);
		//MoveLabel
		JLabel moves_1 = new JLabel("Move Score:");
		moves_1.setForeground(Color.YELLOW);
		moves_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		panel2.add(moves_1);
		//Move Score
		movesLabel = new JLabel("0");
		movesLabel.setForeground(Color.YELLOW);
		movesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		movesLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		panel2.add(movesLabel);
		//ClearLabel
		JLabel reset = new JLabel("Clears:");
		reset.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		reset.setForeground(Color.YELLOW);
		panel2.add(reset);
		//Clear Button
		JLabel resetLabel = new JLabel("1");
		resetLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
		resetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resetLabel.setForeground(Color.YELLOW);
		panel2.add(resetLabel);
		
		hints = new JLabel("Hints:");
    hints.setForeground(Color.YELLOW);
    hints.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    panel2.add(hints);
    
    hintLabel = new JLabel("3");
    hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
    hintLabel.setForeground(Color.YELLOW);
    hintLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
    panel2.add(hintLabel);
		
		comboPanel = new JPanel();
    comboPanel.setBackground(Color.BLACK);
    GridBagConstraints gbc_panel_1 = new GridBagConstraints();
    gbc_panel_1.insets = new Insets(0, 0, 5, 0);
    gbc_panel_1.fill = GridBagConstraints.BOTH;
    gbc_panel_1.gridx = 0;
    gbc_panel_1.gridy = 3;
    panelB.add(comboPanel, gbc_panel_1);
    
    comboBox = new JComboBox();
    comboBox.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    comboBox.setBackground(new Color(178, 34, 34));
    comboBox.setForeground(Color.YELLOW);
    comboBox.setToolTipText("Clear a number! Can be only done once.");
    comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
    comboBox.setSelectedIndex(0);
    comboPanel.add(comboBox);
    
    btnClear = new JButton("Clear!");
    btnClear.setForeground(Color.YELLOW);
    btnClear.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    btnClear.setBackground(new Color(178, 34, 34));
    btnClear.addActionListener(new ButtonListener());
    comboPanel.add(btnClear);
    
    JPanel lenin = new JPanel();
    lenin.setBounds(0, 274, 202, 202);
    lenin.setBackground(Color.BLACK);
    GridBagConstraints leninCon = new GridBagConstraints();
    leninCon.fill = GridBagConstraints.BOTH;
    leninCon.gridx = 0;
    leninCon.gridy = 4;
    panelB.add(lenin, leninCon);
    
		
		JPanel panelSU = new JPanel();
		panelSU.setBounds(0, 274, 202, 202);
		panelSU.setBackground(Color.BLACK);
		GridBagConstraints gbc_panelSU = new GridBagConstraints();
		gbc_panelSU.fill = GridBagConstraints.BOTH;
		gbc_panelSU.gridx = 0;
		gbc_panelSU.gridy = 4;
		panelB.add(panelSU, gbc_panelSU);
		
		JLabel stalin = new JLabel("");
		stalin.setBackground(Color.BLACK);
		stalin.setIcon(new ImageIcon(TimedGameScreen.class.getResource("/images/nid8.gif")));
		panelSU.add(stalin);
		
		queueT = new JLabel[5];
		int queueI[] = gameDriver.viewQueue();
		for (int x = 0; x <= 4; x++){
			queueT[x] = new JLabel(String.format("%d            ", queueI[x]));
			queueT[x].setFont(new Font("Showcard Gothic", Font.PLAIN, 17));
			queueT[x].setForeground(Color.YELLOW);
			queueT[x].setHorizontalAlignment(SwingConstants.RIGHT);
			panelE.add(queueT[x]);
		}
		
	}
	private void createBoardGui(){
		//Creates the board
		panelC.setLayout(new GridLayout(9,9));
		panelC.setSize(500, 500);
		tiles = new JButton[9][9];
		
		int[][] intBoard = new int[9][9];
				
				intBoard = gameDriver.viewBoard();
		for (int x = 0; x <= 8; x++){
			for (int y = 0; y <= 8; y++){
				if (intBoard[x][y] != 11){
					tiles[x][y] = new JButton(String.format("%d", intBoard[x][y]));
					tiles[x][y].setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
					tiles[x][y].putClientProperty("row", x);
					tiles[x][y].putClientProperty("column", y);
					tiles[x][y].addActionListener(new SpaceListener());
					tiles[x][y].setForeground(Color.YELLOW);
					tiles[x][y].setBackground(new Color(178, 34, 34));
					panelC.add(tiles[x][y]);
				}
				else {
					tiles[x][y] = new JButton(String.format(""));
					tiles[x][y].setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
					tiles[x][y].putClientProperty("row", x);
					tiles[x][y].putClientProperty("column", y);
					tiles[x][y].addActionListener(new SpaceListener());
					tiles[x][y].setForeground(Color.YELLOW);
					tiles[x][y].setBackground(new Color(178, 34, 34));
					panelC.add(tiles[x][y]);
				}
			}
		}
	}
	private void updateTime()
	{
		String time = String.format("%d:%02d", gameDriver.getMinutes(), gameDriver.getSeconds());
		msLabel.setText(time);
		if (gameDriver.getRawTime() == 0)
		{
			//TODO:System.out.println("Game Over");
		}
	}
	
	
	private void updateQueue(int newQueue[])
	//Updates queue
	{
		for (int x = 0; x <= 4; x++){
			queueT[x].setText(String.format("%d            ", newQueue[x]));
		}
	}
	
	private void updateBoard(int newBoard[][])
	//Updates board
	{
		for (int x = 0; x <= 8; x++){
			for (int y = 0; y <= 8; y++){
				if (newBoard[x][y] != 11){
					tiles[x][y].setText(String.format("%d", newBoard[x][y]));
					tiles[x][y].setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
					tiles[x][y].setForeground(Color.YELLOW);
					tiles[x][y].setBackground(new Color(178, 34, 34));
				}
				else {
					tiles[x][y].setText(String.format(""));
					tiles[x][y].setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
					tiles[x][y].setForeground(Color.YELLOW);
					tiles[x][y].setBackground(new Color(178, 34, 34));
				}
			}
		}
	}
	// Operates Music
	private class ButtonListener implements ActionListener {

		
				public void actionPerformed(ActionEvent actionRca) {
				  if(actionRca.getSource() == backGround1){
            AudioPlayer.player.stop(music2);
            AudioPlayer.player.start(music1);
            AudioPlayer.player.stop(music3);
          }
          if(actionRca.getSource() == backGround2){
            AudioPlayer.player.start(music2);
            AudioPlayer.player.stop(music1);
            AudioPlayer.player.stop(music3);
            
          }
          if(actionRca.getSource() == backGround3){
            AudioPlayer.player.stop(music2);
            AudioPlayer.player.stop(music1);
            AudioPlayer.player.start(music3);
            
          }
          if(actionRca.getSource() == offButton){
            AudioPlayer.player.stop(music2);
            AudioPlayer.player.stop(music1);
            AudioPlayer.player.stop(music3);
          }
          if(actionRca.getSource() == quitButton){
            sound1.stop();
            sound2.stop();
            sound3.stop();
						try {
							TimedGameOverScreen gameoverquit = new TimedGameOverScreen(gameDriver);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						setVisible(false);
						
					}
					
          if(actionRca.getSource() == hintButton){
            
            ArrayList<int[]> hints = gameDriver.getHint();
            int i = 0;
            int[] j;
            while(i < hints.size()){
              j = hints.get(i);
              tiles[j[0]][j[1]].setBackground(Color.RED);
              i++;
            }
            hintNum--;
            
          }
					
					if(actionRca.getSource() == resetButton){
						gameDriver.refreshQueue();						
					}
					if(actionRca.getSource() == btnClear){
            int i = 0;
            i = comboBox.getSelectedIndex();
            gameDriver.removeTiles(i);
            btnClear.setEnabled(false);
            
          }
					if(actionRca.getSource() == debugButton){
						gameDriver.debugGame();
					}
					
				}
				
			}
	private class SpaceListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			//Listener
			//Get the pressed button
			JButton pressed = (JButton) e.getSource();
			//change the text of the space with the top value from the queue
						
			//send the new value to the GameBoard for processing, which returns a score			
			moveScore = gameDriver.placeTile((int) pressed.getClientProperty("row"), (int) pressed.getClientProperty("column"));
			if(gameDriver.getRawTime() == 0){
			  AudioPlayer.player.stop(music2);
        AudioPlayer.player.stop(music1);
        AudioPlayer.player.stop(music3);
				try {
					TimedGameOverScreen gameoverquit = new TimedGameOverScreen(gameDriver);
					setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else if((gameDriver.getBoardStatus() == 84)){
			  AudioPlayer.player.stop(music2);
        AudioPlayer.player.stop(music1);
        AudioPlayer.player.stop(music3);
        try {
          TimedGameOverScreen gameoverquit = new TimedGameOverScreen(gameDriver);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        setVisible(false);
      }
      
      else if(gameDriver.getBoardStatus() == 0){
        AudioPlayer.player.stop(music2);
        AudioPlayer.player.stop(music1);
        AudioPlayer.player.stop(music3);
        /*try {
          WinScreen win = new WinScreen(0, gameDriver);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        */
        setVisible(false);
      }
		}
	}
	public void update(Observable o, Object arg) {
		if (gameDriver.getMoveScore() != -1){
			try {
				scoreLabel.setText(String.format("%d", gameDriver.getScore()));
			}catch(NullPointerException e) {
				System.out.println("An ellusive NullPointer Error Occurred.");
				System.out.printf("Score Is: %d", gameDriver.getScore());
			}
			//msLabel.setText(String.format("%d", gameDriver.getMoveScore()));
      try{
          movesLabel.setText(String.format("%d", gameDriver.getMoveScore()));
      }catch(NullPointerException e) {
          System.out.println("An ellusive NullPointer Error Occurred.");
          System.out.printf("Score Is: %d\n", gameDriver.getScore());
      } 
			
		}
		if(gameDriver.refreshLeft()){
			resetButton.setEnabled(true); //Null Pointer was thrown here
		}else{
			resetButton.setEnabled(false);
		}
		
		updateBoard(gameDriver.viewBoard());
		updateQueue(gameDriver.viewQueue());
		updateTime();

	}
	
}
