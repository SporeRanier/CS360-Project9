package sprint;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class Top10Times extends JFrame implements Observer{
    TimedGame gameDriver;
    String[] scoreHolder;
    AudioStream audioStream;
    JButton newNormal;
    JButton newTimed;
    JButton exit;
	public Top10Times(TimedGame input) {
	  // create an audiostream from the inputstream
    try {
      audioStream = new AudioStream(getClass().getResourceAsStream("/sounds/win.wav"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

      // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
	  gameDriver = input;
		setTitle("High Scores!");
		getContentPane().setBackground(Color.RED);
		setSize(800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		scoreHolder = gameDriver.getTimedScores();
		JPanel highScorePanel = new JPanel();
		highScorePanel.setBackground(Color.RED);
		getContentPane().add(highScorePanel, BorderLayout.CENTER);
		highScorePanel.setLayout(new GridLayout(10, 1, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label0 = new JLabel();
		label0.setForeground(Color.YELLOW);
		
		label0.setText("    # 1  " + scoreHolder[0]);
		label0.setHorizontalAlignment(SwingConstants.LEFT);
		label0.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label0);
		
		JLabel label1 = new JLabel("    # 2  " + scoreHolder[1]);
		label1.setForeground(Color.YELLOW);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label1);
		
		
		JLabel label2 = new JLabel("    # 3  " + scoreHolder[2]);
		label2.setForeground(Color.YELLOW);
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label2);
		
		JLabel label3 = new JLabel("    # 4  " + scoreHolder[3]);
		label3.setForeground(Color.YELLOW);
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label3);
		
		JLabel label4 = new JLabel("    # 5  " + scoreHolder[4]);
		label4.setForeground(Color.YELLOW);
		label4.setHorizontalAlignment(SwingConstants.LEFT);
		label4.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label4);
		
		JLabel label5 = new JLabel("    # 6  " + scoreHolder[5]);
		label5.setForeground(Color.YELLOW);
		label5.setHorizontalAlignment(SwingConstants.LEFT);
		label5.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label5);
		
		JLabel label6 = new JLabel("    # 7  " + scoreHolder[6]);
		label6.setForeground(Color.YELLOW);
		label6.setHorizontalAlignment(SwingConstants.LEFT);
		label6.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label6);
		
		JLabel label7 = new JLabel("    # 8  " + scoreHolder[7]);
		label7.setForeground(Color.YELLOW);
		label7.setHorizontalAlignment(SwingConstants.LEFT);
		label7.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label7);
		
		JLabel label8 = new JLabel("    # 9  " + scoreHolder[8]);
		label8.setForeground(Color.YELLOW);
		label8.setHorizontalAlignment(SwingConstants.LEFT);
		label8.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label8);
		
		JLabel label9 = new JLabel("    # 10  " + scoreHolder[9]);
		label9.setForeground(Color.YELLOW);
		label9.setHorizontalAlignment(SwingConstants.LEFT);
		label9.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label9);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel highScoreLabel = new JLabel("SPEEDY HEROES OF THE SOVIET UNION");
		highScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		highScoreLabel.setForeground(Color.YELLOW);
		highScoreLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 42));
		panel.add(highScoreLabel);
		
		JLabel lblNewLabel = new JLabel("                Name          Time               Date");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 36));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.RED);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		newNormal = new JButton("New Normal Game");
		newNormal.setBackground(Color.RED);
		newNormal.setForeground(Color.YELLOW);
		newNormal.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		newNormal.addActionListener(new GameOverListener());
		panel_1.add(newNormal);
		
		newTimed = new JButton("New Timed Game");
		newTimed.setForeground(Color.YELLOW);
		newTimed.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		newTimed.setBackground(Color.RED);
		newTimed.addActionListener(new GameOverListener());
		panel_1.add(newTimed);
		
		exit = new JButton("Exit");
		exit.setForeground(Color.YELLOW);
		exit.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
		exit.setBackground(Color.RED);
		exit.addActionListener(new GameOverListener());
		panel_1.add(exit);
		
		JLabel label = new JLabel("", new ImageIcon("starsmall.png"), JLabel.CENTER);
		setVisible(true);
		
	}
	private class GameOverListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == newNormal){
        P8NormalGameScreen newGame = new P8NormalGameScreen();
        newGame.gameDriver.newGame();
        AudioPlayer.player.stop(audioStream);
        setVisible(false);
      }
      if(e.getSource() == newTimed){
        TimedGameScreen time = new TimedGameScreen();
        time.gameDriver.newGame();
        AudioPlayer.player.stop(audioStream);
        setVisible(false);
      }
      
      if(e.getSource() == exit){
        AudioPlayer.player.stop(audioStream);
        System.exit(0);
        
      }
      //something
    
  }
}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
