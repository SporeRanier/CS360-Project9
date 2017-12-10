package sprint;
//Small Window that determines game type
import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
import java.awt.Color;
/**
 * This is the Menu Screen Class.
 * 
 * @author Michael Wolgast
 * @version 1.0
 * @since 2017-13-18
 */

public class P8Menu extends JFrame{
	//Constructor
	private BufferedImage kremlin;
	AudioStream a;
	
	
	public P8Menu() throws IOException {
		setTitle("SumFun 0.97");
		setSize(480, 260);
		getContentPane().setLayout(null);
		ImageIcon image = new ImageIcon("kremlin.jpg");
		JLabel label = new JLabel("", new ImageIcon(P8Menu.class.getResource("/images/kremlin.png")), JLabel.CENTER);
		JButton btnNewButton = new JButton("Normal Game");
		btnNewButton.setForeground(Color.YELLOW);
		btnNewButton.setFont(new Font("Showcard Gothic", Font.BOLD, 14));
		btnNewButton.addActionListener(new NormalButtonListener());
		btnNewButton.setBounds(280, 11, 174, 34);
		btnNewButton.setBackground(new Color(178, 34, 34));
		getContentPane().add(btnNewButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btnStartAdvancedGame = new JButton("Advanced Game");
		btnStartAdvancedGame.setForeground(Color.YELLOW);
		btnStartAdvancedGame.setFont(new Font("Showcard Gothic", Font.BOLD, 14));
		btnStartAdvancedGame.addActionListener(new AdvancedButtonListener());
		
		btnStartAdvancedGame.setBounds(280, 46, 174, 32);
		btnStartAdvancedGame.setToolTipText("In Progress");
		getContentPane().add(btnStartAdvancedGame);
		btnStartAdvancedGame.setBackground(new Color(178, 34, 34));
		//JButton debug = new JButton("Debug");
		//debug.setBounds(0, 198, 63, 23);
		//debug.addActionListener(new DebugButtonListener());
		//getContentPane().add(debug);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.CENTER );
		panel.setBounds(0, 0, 464, 221);
		//InputStream in = new FileInputStream("/sounds/slav.wav");

		    // create an audiostream from the inputstream
		a = new AudioStream(getClass().getResourceAsStream("/sounds/slav.wav"));

		    // play the audio clip with the audioplayer class
	  //AudioPlayer.player.start(audioStream);
		try {
      AudioPlayer.player.start(a);
  } catch (Exception e) {
      e.printStackTrace();
  }
		
		getContentPane().add(panel);
		
		setVisible(true);
		
	}
	private class NormalButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			P8NormalGameScreen game1 = new P8NormalGameScreen();
			setVisible(false);
			AudioPlayer.player.stop(a);
			
		}
}

	private class AdvancedButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent f) {
			// TODO Auto-generated method stub
			TimedGameScreen game2 = new TimedGameScreen();
			setVisible(false);
			AudioPlayer.player.stop(a);
		}
}
/*	private class DebugButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent g) {
			TimedGameScreen game3 = new TimedGameScreen();
			setVisible(false);
			AudioPlayer.player.stop(audioStream);
		}
}
*/
}
