package sprint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class HighScores {
  private String[] names;// Contains the names of the people that have high scores.
  private int[] scores; // Contains the scores of the top 10 high scores.
  private String[] times; // Contains the time when the high scores were achieved.

  /**
   * TODO Constructor for the HighScores class which initializes each data field.
   */
  public HighScores() {
    names = new String[10];
    scores = new int[10];
    times = new String[10];
  }

  /**
   * TODO Getter method for receiving the array of names that correspond with the top scores.
   * 
   * @return The names[] field.
   */
  public String[] getNames() {
    return names;
  }

  /**
   * Getter for individual elements within the names array.
   * 
   * @param index
   *          The index of the element that needs to be returned.
   * @return The array element at the given index.
   */
  public String getName(int index) {
    return names[index];
  }

  /**
   * Getter method for receiving the Scores array from an instance of this class.
   * 
   * @return scores[] the data field that will hold the top 10 high scores.
   */
  public String[] getScores() {
    String[] strScores = new String[10];
    for (int x=0; x<10; x++){
      strScores[x] = String.valueOf(scores[x]);
    }
    return strScores;
  }

  /**
   * Getter for individual elements within the scores array.
   * 
   * @param index
   *          The index of the element that needs to be returned.
   * @return The array element at the given index.
   */
  public int getScore(int index) {
    return scores[index];
  }

  /**
   * Getter for the times array
   * 
   * @return times array
   */
  public String[] getTimes() {
    return times;
  }

  /**
   * Getter for individual elements within the times array.
   * 
   * @param index
   *          The index of the element that needs to be returned.
   * @return
   */
  public String getTime(int index) {
    return times[index];
  }

  /**
   * TODO The add method tests to see if a new numerical value passed into the method will be able
   * to place in the top 10 scores. If it does make the list it is inserted in t's proper position
   * and shifts all lesser values down on the high scores list.
   * 
   * @param name
   *          A String value that corresponds to the numerical value passed into the method it shall
   *          be added with the numerical value if the numerical score beats one of the top scores.
   * @param score
   *          A new score that will be added to the high score list if it is one of the values that
   *          makes it into the top ten.
   */
  public void add(String name, int score) {
    int counter = 0;
    boolean proceed = true;
    int temp = 0;
    String tempName = "";
    int position = 0;
    while (counter < scores.length && proceed) {
      if (score > scores[counter]) {
        proceed = false;
        position = counter;
        temp = scores[counter];
        for (int i = scores.length - 1; i > position; i--) {
          temp = scores[i];
          scores[i] = scores[i - 1];
          tempName = names[i];
          names[i] = names[i - 1];
        }
        scores[position] = score;
        names[position] = name;
      }
      counter++;
    }
  }

  /**
   * TODO This method inserts new high scores along with the name and time that go with the new high
   * scores in each corresponding array. The scores that are less than the new high score are moved
   * down in the list by one space and the tenth best score is removed entirely if the new score is
   * not the tenth best score.
   * 
   * @param position
   *          The position in the top ten where the new high score, name, and time will be inserted.
   * @param name
   *          The corresponding name that goes with the new high score.
   * @param score
   *          The new high score that will be inserted into the top 10 high scores.
   * @param time
   *          The corresponding time that goes with the new high score.
   */
  public void insertScore(String name) {
    int temp = 0;
    String tempName = "";
    String tempTime = "";
    for (int i = scores.length - 1; i > position; i--) {
      temp = scores[i];
      scores[i] = scores[i - 1];
      tempName = names[i];
      names[i] = names[i - 1];
      tempTime = times[i];
      times[i] = times[i - 1];
    }
    scores[position] = score;
    names[position] = name;
    times[position] = time;
  }

  /**
   * TODO This method checks to see if a new score qualifies to be in the top 10 high scores and if
   * it does qualify then the index where it will be inserted will be returned otherwise a -1 will
   * be returned indicating that it is not a new high score.
   * 
   * @param score
   *          The score that the method will compare to the top 10 high scores to see if it
   *          qualifies to be in the top 10 high scores.
   * @return Either the index where the new high score will be inserted or a -1 indicating that it
   *         is not a new high score.
   */
  public int newHighScore(int score) {
    for (int i = 0; i < scores.length; i++) {
      if (score > scores[i]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * TODO The method that prompts the user to enter their name if their score is in the top 10.
   * 
   * @return The name that was entered into the input box.
   */
  public String enterName() {
    String name = JOptionPane.showInputDialog("Please enter your name.");
    return name;
  }

  /**
   * TODO This method reads the text file "HighScores.txt" and fills the names and scores arrays
   * with the appropriate date to show the top scores and the names of the people that got these
   * scores.
   * 
   * @throws IOException
   *           Throws an exception if the file doesn't exist.
   */
  public void fill() throws IOException {
    String filename = "HighScores.txt";
    File file = new File(filename);
    Scanner inputFile = new Scanner(file);
    String data = "";
    int i = 0;
    while (inputFile.hasNextLine()) {
      data = inputFile.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(data, " ");
      names[i] = tokenizer.nextToken();
      scores[i] = Integer.parseInt(tokenizer.nextToken());
      times[i] = tokenizer.nextToken();
      i++;
    }
    for (i = 0; i < scores.length; i++) {
      System.out.println(names[i] + " " + scores[i] + " " + times[i]);
    }
    inputFile.close();
  }

  /**
   * TODO This method works as an update method for writing an the top scores in case a new score is
   * added which will ultimately push another value out of the array.
   * 
   * @throws IOException
   */
  public void writeToFile() throws IOException {
    FileWriter fwriter = new FileWriter("HighScores.txt", false);
    PrintWriter outputFile = new PrintWriter(fwriter);
    for (int i = 0; i < scores.length; i++) {
      outputFile.println(names[i] + " " + scores[i] + " " + times[i]);
    }
    outputFile.close();
  }
}