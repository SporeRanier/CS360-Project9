package sprint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class HighScoresTimed {
  private String[] names; // Contains the names of the people who have high timed scores.
  private int[] times; // Contains the top 10 times for completing the game.
  private String[] dates; // Contains the dates when these high timed scores were achieved.
  private int newTime;
  private int position;
  private String currentDate;

  /**
   * TODO Constructor for HighScoresTimed which initializes all the data fields.
   */
  public HighScoresTimed(){
    names = new String[10];
    times = new int[10];
    dates = new String[10];
    fill();
  }

  /**
   * TODO The getter method for the names field.
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
  /**
   * A getter method for the times data field.
   * 
   * @return The entire array of the best times for the timed game mode.
   */
  public String[] getTimes() {
    String[] strTimes = new String[10];
    for (int x = 0; x < 10; x++) {
      strTimes[x] = String.valueOf(times[x]);
    }
    return strTimes;
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

  public void setPosition(int position) {
    this.position = position;
  }

  public void setNewTime(int newTime) {
    this.newTime = newTime;
  }

  public void setDate() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    this.currentDate = dateFormat.format(date);
  }

  /**
   * TODO This method checks to see if the score of the most recently completed timed games is a
   * high score and if so returns the index of the position where it will be inserted into the
   * scores array. If more than one score is the same, then times are compared and whoever completed
   * the game faster will be the highest on the High Scores board.
   * 
   * @return The index where the new score will be inserted if it is a high score or a -1 to
   *         indicate that it is not a new high score.
   */
  public int newHighScore(int time) {
    for (int i = 0; i < times.length; i++) {
      if (time > times[i]) {
        setPosition(i);
        setNewTime(time);
        setDate();
        return i;
      }
    }
    return -1;
  }

  /**
   * TODO This method inserts a new high score into the scores array as well as the corresponding
   * name, time, and date values into their respective parallel arrays. Lower scores are then pushed
   * down one position which will result in the values in the 10th being removed completely.
   * 
   * @param name
   *          The new name to be inserted into the names field.
   */
  public void insertScore(String name) {
    String tempName = "";
    int tempTime = 0;
    String tempDate = "";
    for (int i = times.length - 1; i > position; i--) {
      tempName = names[i];
      names[i] = names[i - 1];
      tempTime = times[i];
      times[i] = times[i - 1];
      tempDate = dates[i];
      dates[i] = dates[i - 1];
    }
    names[position] = name;
    times[position] = newTime;
    dates[position] = currentDate;
  }

  /**
   * TODO Fills the data fields with their corresponding values by reading the high score
   * information from a text file.
   * 
   * @throws IOException
   *           If the program has trouble connecting with the file.
   */
  private void fill(){
    String filename = "HighScoresTimed.txt";
    File file = new File(filename);
    Scanner inputFile;
    try {
      inputFile = new Scanner(file);
      String data = "";
      int i = 0;
      while (inputFile.hasNextLine()) {
        data = inputFile.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(data, " ");
        names[i] = tokenizer.nextToken();
        times[i] = Integer.parseInt(tokenizer.nextToken());
        dates[i] = tokenizer.nextToken();
        i++;
      }
      for (i = 0; i < times.length; i++) {
        System.out.println(names[i] + " " + times[i] + " " + times[i]);
      }
      inputFile.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * TODO This method writes new high score related values, including name, score, time, and date,
   * to a text file.
   * 
   * @throws IOException
   *           In case the program has trouble connecting to the text file.
   */
  public void writeToFile(){
    FileWriter fwriter;
    try {
      fwriter = new FileWriter("HighScoresTimed.txt", false);
      PrintWriter outputFile = new PrintWriter(fwriter);
      for (int i = 0; i < times.length; i++) {
        outputFile.println(names[i] + " " + times[i] + " " + dates[i]);
      }
      outputFile.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}