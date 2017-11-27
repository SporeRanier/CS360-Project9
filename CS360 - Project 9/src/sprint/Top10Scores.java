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
	public Top10Scores() {
		setTitle("High Scores!");
		getContentPane().setBackground(new Color(178, 34, 34));
		setSize(800, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel highScorePanel = new JPanel();
		highScorePanel.setBackground(new Color(178, 34, 34));
		getContentPane().add(highScorePanel, BorderLayout.CENTER);
		highScorePanel.setLayout(new GridLayout(10, 1, 0, 0));
		
		JLabel label0 = new JLabel();
		label0.setText("Player 1 500");
		label0.setHorizontalAlignment(SwingConstants.CENTER);
		label0.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label0);
		
		JLabel label1 = new JLabel("Gil 100");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label1);
		
		
		JLabel label2 = new JLabel("Francesca 94");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label2);
		
		JLabel label3 = new JLabel("Olivia 86");
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label3);
		
		JLabel label4 = new JLabel("Evan 80");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label4);
		
		JLabel label5 = new JLabel("Daniel 67");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label5);
		
		JLabel label6 = new JLabel("Alex 55");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label6);
		
		JLabel label7 = new JLabel("Julie 47");
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		label7.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label7);
		
		JLabel label8 = new JLabel("Cindy 41");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label8);
		
		JLabel label9 = new JLabel("Brittney 23");
		label9.setHorizontalAlignment(SwingConstants.CENTER);
		label9.setFont(new Font("Showcard Gothic", Font.PLAIN, 28));
		highScorePanel.add(label9);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(178, 34, 34));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel highScoreLabel = new JLabel("High Scores");
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
