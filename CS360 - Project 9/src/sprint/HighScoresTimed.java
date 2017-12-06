package sprint;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class HighScoresTimed {
  private String[] names; //Contains the names of the people who have high timed scores.
  private int[] scores;
  private int[] times; //Contains the top 10 times for completing the game.
  private String[] dates; //Contains the dates when these high timed scores were achieved.
  /**
   * Constructor for HighScoresTimed which initializes all the data fields.
   */
  public HighScoresTimed() {
    names = new String[10];
    scores = new int[10];
    times = new int[10];
    dates = new String[10];
  }
  /**
   * The getter method for the names field.
   * 
   * @return An array of strings containing the names of the people who are in the top ten for high
   *         scores.
   */
  public String[] getNames() {
    return names;
  }
  /**
   * Another getter method for the individual elements of the names array.
   * 
   * @param index
   *          The index of the element in the array that needs to be returned.
   * @return The player name at the given index in the names data field.
   */
  public String getName(int index) {
    return names[index];
  }
  /**
   * Getter method for the scores data field.
   * 
   * @return The entire array containing the top 10 high scores.
   */
  public int[] getScores() {
    return scores;
  }
  /**
   * Another getter method for the scores field which returns individual scores from the scores
   * array.
   * 
   * @param index
   *          The index of the element from the scores array that needs to be returned.
   * @return The score value from the scores array at the given index.
   */
  public int getScore(int index) {
    return scores[index];
  }
  /**
   * A getter method for the times data field.
   * 
   * @return The entire array of the best times for the timed game mode.
   */
  public int[] getTimes() {
    return times;
  }
  /**
   * Another getter method that returns individual time values from within the times array.
   * 
   * @param index
   *          The index of the element that needs to be returned.
   * @return The element in the times array at the given index.
   */
  public int getTime(int index) {
    return times[index];
  }
  /**
   * A getter method for the dates data field.
   * 
   * @return The entire array of the dates during which each of the top 10 scores were achieved.
   */
  public String[] getDates() {
    return dates;
  }
  /**
   * Another getter method for the dates field which returns individual elements of the dates array.
   * 
   * @param index
   *          The index of the element that needs to be returned.
   * @return The element of the dates array at the given index.
   */
  public String getDate(int index) {
    return dates[index];
  }
  /**
   * This method checks to see if the score of the most recently completed timed games is a high
   * score and if so returns the index of the position where it will be inserted into the scores
   * array. If more than one score is the same, then times are compared and whoever completed the
   * game faster will be the highest on the High Scores board.
   * 
   * @return The index where the new score will be inserted if it is a high score or a -1 to
   *         indicate that it is not a new high score.
   */
  public int newHighScore() {
    return -1;
  }
  /**
   * This method inserts a new high score into the scores array as well as the corresponding name,
   * time, and date values into their respective parallel arrays. Lower scores are then pushed down
   * one position which will result in the values in the 10th being removed completely.
   * 
   * @param position
   *          The position where the values will be inserted.
   * @param name
   *          The new name to be inserted into the names field.
   * @param score
   *          The new score to be inserted into the scores field.
   * @param time
   *          The new time to be inserted into the scores field.
   * @param date
   *          The new date to be inserted into the dates field.
   */
  public void insertScore(int position, String name, int score, int time, String date) {
    int temp = 0;
    String tempName = "";
    int tempTime = 0;
    String tempDate = "";
    for (int i = scores.length - 1; i > position; i--) {
      temp = scores[i];
      scores[i] = scores[i - 1];
      tempName = names[i];
      names[i] = names[i - 1];
      tempTime = times[i];
      times[i] = times[i - 1];
      tempDate = dates[i];
      dates[i] = dates[i - 1];
    }
    scores[position] = score;
    names[position] = name;
    times[position] = time;
    dates[position] = date;
  }
  /**
   * This method prompts the user to enter a name into an input box and returns the string that they
   * entered.
   * 
   * @return The string that was inputed into the input box.
   */
  public String enterName() {
    String input = JOptionPane.showInputDialog("Please enter your name");
    return input;
  }
  /**
   * Fills the data fields with their corresponding values by reading the high score information
   * from a text file.
   * 
   * @throws IOException
   *           If the program has trouble connecting with the file.
   */
  public void fill() throws IOException {
    String filename = "HighScoresTimed.txt";
    File file = new File(filename);
    Scanner inputFile = new Scanner(file);
    String data = "";
    int i = 0;
    while (inputFile.hasNextLine()) {
      data = inputFile.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(data, " ");
      names[i] = tokenizer.nextToken();
      scores[i] = Integer.parseInt(tokenizer.nextToken());
      times[i] = (int) tokenizer.nextToken();
      dates[i] = tokenizer.nextToken();
      i++;
    }
    for (i = 0; i < scores.length; i++) {
      System.out.println(names[i] + " " + scores[i] + " " + times[i]);
    }
    inputFile.close();
  }
  /**
   * This method writes new high score related values, including name, score, time, and date, to a
   * text file.
   * 
   * @throws IOException
   *           In case the program has trouble connecting to the text file.
   */
  public void writeToFile() throws IOException {
    FileWriter fwriter = new FileWriter("HighScoresTimed.txt", false);
    PrintWriter outputFile = new PrintWriter(fwriter);
    for (int i = 0; i < scores.length; i++) {
      outputFile.println(names[i] + " " + scores[i] + " " + times[i] + " " + dates[i]);
    }
    outputFile.close();
  }
}