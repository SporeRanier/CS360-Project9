package sprint;

import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;



public class Top10Scores extends JFrame implements Observer{
    UntimedGame gameDriver;
    String[] scoreHolder;
	public Top10Scores(UntimedGame input) {
	  gameDriver = input;
		setTitle("High Scores!");
		getContentPane().setBackground(new Color(178, 34, 34));
		setSize(800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		scoreHolder = gameDriver.getScores();
		JPanel highScorePanel = new JPanel();
		highScorePanel.setBackground(new Color(178, 34, 34));
		getContentPane().add(highScorePanel, BorderLayout.CENTER);
		highScorePanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel label0 = new JLabel();
		
		label0.setText("    # 1  " + scoreHolder[0]);
		label0.setHorizontalAlignment(SwingConstants.LEFT);
		label0.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label0);
		
		JLabel label1 = new JLabel("    # 2  " + scoreHolder[1]);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label1);
		
		
		JLabel label2 = new JLabel("    # 3  " + scoreHolder[2]);
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label2);
		
		JLabel label3 = new JLabel("    # 4  " + scoreHolder[3]);
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label3);
		
		JLabel label4 = new JLabel("    # 5  " + scoreHolder[4]);
		label4.setHorizontalAlignment(SwingConstants.LEFT);
		label4.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label4);
		
		JLabel label5 = new JLabel("    # 6  " + scoreHolder[5]);
		label5.setHorizontalAlignment(SwingConstants.LEFT);
		label5.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label5);
		
		JLabel label6 = new JLabel("    # 7  " + scoreHolder[6]);
		label6.setHorizontalAlignment(SwingConstants.LEFT);
		label6.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label6);
		
		JLabel label7 = new JLabel("    # 8  " + scoreHolder[7]);
		label7.setHorizontalAlignment(SwingConstants.LEFT);
		label7.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label7);
		
		JLabel label8 = new JLabel("    # 9  " + scoreHolder[8]);
		label8.setHorizontalAlignment(SwingConstants.LEFT);
		label8.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label8);
		
		JLabel label9 = new JLabel("    # 10  " + scoreHolder[9]);
		label9.setHorizontalAlignment(SwingConstants.LEFT);
		label9.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label9);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel highScoreLabel = new JLabel("HEROES OF THE SOVIET UNION");
		highScoreLabel.setForeground(Color.YELLOW);
		highScoreLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 36));
		panel.add(highScoreLabel);
		
		JLabel label = new JLabel("", new ImageIcon("starsmall.png"), JLabel.CENTER);
		setVisible(true);
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
