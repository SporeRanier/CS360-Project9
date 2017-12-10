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
    setSize(800, 600);
    setAlwaysOnTop(true);
    getContentPane().setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // create an audiostream from the inputstream
    audioStream = new AudioStream(getClass().getResourceAsStream("/sounds/soviet.wav"));

      // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
    
    JLabel hamsic = new JLabel(" ");
    hamsic.setIcon(new ImageIcon(WinScreen.class.getResource("/images/hamsic.gif")));
    hamsic.setBounds(585, 0, 189, 168);
    getContentPane().add(hamsic);
    
    JLabel hamsic2 = new JLabel(" ");
    hamsic2.setIcon(new ImageIcon(WinScreen.class.getResource("/images/hamsic.gif")));
    hamsic2.setBounds(0, 0, 189, 168);
    getContentPane().add(hamsic2);
    
    JLabel gameoverlabel = new JLabel("You win!");
    gameoverlabel.setForeground(Color.YELLOW);
    gameoverlabel.setHorizontalAlignment(SwingConstants.CENTER);
    gameoverlabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 76));
    gameoverlabel.setBounds(99, 21, 580, 124);
    getContentPane().add(gameoverlabel);
    
    newGame = new JButton("New Normal Game");
    newGame.setBackground(new Color(178, 34, 34));
    newGame.setForeground(Color.YELLOW);
    newGame.setBounds(43, 341, 283, 92);
    getContentPane().add(newGame);
    newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
    newGame.addActionListener(new GameOverListener());
    
    highScores = new JButton("High Scores");
    highScores.setBackground(new Color(178, 34, 34));
    highScores.setForeground(Color.YELLOW);
    highScores.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
    highScores.setBounds(43, 458, 283, 92);
    highScores.addActionListener(new GameOverListener());
    
    btnNewTimedGame = new JButton("New Timed Game");
    btnNewTimedGame.setBackground(new Color(178, 34, 34));
    btnNewTimedGame.setForeground(Color.YELLOW);
    btnNewTimedGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
    btnNewTimedGame.setBounds(459, 341, 283, 92);
    btnNewTimedGame.addActionListener(new GameOverListener());
    getContentPane().add(btnNewTimedGame);
    getContentPane().add(highScores);
    
    btnQuitGame = new JButton("Quit Game");
    btnQuitGame.setBackground(new Color(178, 34, 34));
    btnQuitGame.setForeground(Color.YELLOW);
    btnQuitGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 26));
    btnQuitGame.setBounds(459, 458, 283, 92);
    btnQuitGame.addActionListener(new GameOverListener());
    getContentPane().add(btnQuitGame);
        
    JButton saveButton = new JButton("Save Score!");
    saveButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
    saveButton.setForeground(new Color(255, 255, 0));
    saveButton.setBackground(new Color(178, 34, 34));
    saveButton.setBounds(266, 252, 238, 78);
    getContentPane().add(saveButton);
    
    entry = new JTextField();
    entry.setForeground(new Color(255, 255, 0));
    entry.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
    entry.setHorizontalAlignment(SwingConstants.CENTER);
    entry.setBackground(new Color(178, 34, 34));
    entry.setText("You've reached the top 10 scores! Enter your name here!");
    entry.setBounds(43, 168, 699, 73);
    getContentPane().add(entry);
    entry.setColumns(10);
    
    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 784, 561);
    getContentPane().add(panel);
    panel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setBounds(0, 0, 784, 561);
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
