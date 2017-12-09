package sprint;

import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class WinScreen extends JFrame{
  AudioStream audioStream;
  JButton newGame;
  JButton highScores;
  JButton btnQuitGame;
  private JButton btnNewTimedGame;
  private JTextField entry;
  public WinScreen() throws IOException {
    getContentPane().setBackground(Color.BLACK);
    setTitle("Winner!");
    setSize(582, 369);
    setAlwaysOnTop(true);
    getContentPane().setLayout(null);
    

      // create an audiostream from the inputstream
    audioStream = new AudioStream(getClass().getResourceAsStream("/sounds/gameover.wav"));

      // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
    
    JLabel gameoverlabel = new JLabel("You win!");
    gameoverlabel.setForeground(Color.YELLOW);
    gameoverlabel.setHorizontalAlignment(SwingConstants.CENTER);
    gameoverlabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
    gameoverlabel.setBounds(99, 21, 374, 57);
    getContentPane().add(gameoverlabel);
    
    newGame = new JButton("New Normal Game");
    newGame.setBackground(new Color(178, 34, 34));
    newGame.setForeground(Color.YELLOW);
    newGame.setBounds(43, 208, 171, 40);
    getContentPane().add(newGame);
    newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    newGame.addActionListener(new GameOverListener());
    
    highScores = new JButton("High Scores");
    highScores.setBackground(new Color(178, 34, 34));
    highScores.setForeground(Color.YELLOW);
    highScores.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    highScores.setBounds(43, 272, 171, 33);
    highScores.addActionListener(new GameOverListener());
    
    btnNewTimedGame = new JButton("New Timed Game");
    btnNewTimedGame.setBackground(new Color(178, 34, 34));
    btnNewTimedGame.setForeground(Color.YELLOW);
    btnNewTimedGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    btnNewTimedGame.setBounds(332, 208, 185, 40);
    btnNewTimedGame.addActionListener(new GameOverListener());
    getContentPane().add(btnNewTimedGame);
    getContentPane().add(highScores);
    
    btnQuitGame = new JButton("Quit Game");
    btnQuitGame.setBackground(new Color(178, 34, 34));
    btnQuitGame.setForeground(Color.YELLOW);
    btnQuitGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    btnQuitGame.setBounds(333, 272, 185, 33);
    btnQuitGame.addActionListener(new GameOverListener());
    getContentPane().add(btnQuitGame);
        
    JButton saveButton = new JButton("Save Score!");
    saveButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
    saveButton.setForeground(new Color(255, 255, 0));
    saveButton.setBackground(new Color(178, 34, 34));
    saveButton.setBounds(174, 148, 217, 49);
    getContentPane().add(saveButton);
    
    entry = new JTextField();
    entry.setForeground(new Color(255, 255, 0));
    entry.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
    entry.setHorizontalAlignment(SwingConstants.CENTER);
    entry.setBackground(new Color(178, 34, 34));
    entry.setText("You reached the top 10 scores! Enter your name here!");
    entry.setBounds(43, 89, 474, 33);
    getContentPane().add(entry);
    entry.setColumns(10);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 566, 330);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setBounds(0, 0, 566, 330);
    lblNewLabel.setIcon(new ImageIcon(WinScreen.class.getResource("/images/starsmall.png")));
    panel.add(lblNewLabel);
    setVisible(true);
  }
  
  private class GameOverListener implements ActionListener {

      public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGame){
          P8NormalGameScreen newGame = new P8NormalGameScreen();
          newGame.gameDriver.newGame();
          setVisible(false);
        }
        if(e.getSource() == btnNewTimedGame){
          TimedGameScreen time = new TimedGameScreen();
          time.gameDriver.newGame();
          setVisible(false);
        }
        if(e.getSource() == highScores){
          Top10Scores top10 = new Top10Scores();
          setVisible(false);
        }
        if(e.getSource() == btnQuitGame){
          System.exit(0);
          
        }
        
      
    }
  }
}
