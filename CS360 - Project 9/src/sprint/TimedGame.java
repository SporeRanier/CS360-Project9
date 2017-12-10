package sprint;

/**
* Class acts as the game logic for an timed game in Sum Fun.
* Contains a board, a queue, variables for time, and for Score.
* Also works with the clock class to keep track of time. This is done through a separate thread.
* Updates all variables as it is told by a GUI.
* @author  David Bell
* @version 1.0
* @since   2017-13-18 
*/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

public class TimedGame extends Observable {
  private static TimedGame timedInstance = new TimedGame();

  private GameBoard gameBoard;
  private Queue queue;
  private HighScores highScores;
  private HighScoresTimed timedScores;

  private int rawSeconds;
  private int minutes;
  private int seconds;

  private int totalScore = 0;
  private int moveScore = 0;
  private boolean tileRemove = true;
  private int hints = 3;

  private GameTimer gameTimer;
  // private static Clock clock;
  // static Thread thread1;

  // constructor for a singleton TimedGame
  private TimedGame() {
    gameBoard = GameBoard.getBoard();
    queue = Queue.getQueue();
    rawSeconds = 180;
    highScores = new HighScores();
    timedScores = new HighScoresTimed();
    gameTimer = new GameTimer(1000, taskPerformer);
    gameTimer.start();

  }

  /**
   * Returns the instance of this class, as well as starting the thread for the clock.
   * 
   * @return TimedGame The instance of this game that is returned.
   */
  public static TimedGame getTimedGame() {
    // starts the thread for the timer
    // gameTimer.start();
    return timedInstance;
  }

  // returns a copy of the board
  public int[][] viewBoard() {
    return gameBoard.viewBoard();
  }

  /**
   * Method taking care of the placement of a tile. Takes care of all logic with board and queue.
   * 
   * @param x
   *          The y coordinate of the placed tile
   * @param y
   *          The x coordinate of the placed tile
   * @return int returns the score of the move to the caller. Value -1 means the tile was placed on
   *         an occupied space.
   */
  public int placeTile(int x, int y) {
    moveScore = gameBoard.placeTile(x, y, queue.viewTop());
    // score of -1 indicates that tile is already occupied, so nothing is done
    if (moveScore == -1) {
      return moveScore;
    }
    // remove the top of the queue and generate a new value
    queue.useQueue();
    totalScore += moveScore;
    // notify observers
    setChanged();
    notifyObservers();

    return moveScore;
  }

  // returns a copy of the queue
  public int[] viewQueue() {
    return queue.viewQueue();
  }

  // Allows the caller to view what the top of the queue is without using it
  public int viewTop() {
    return queue.viewTop();
  }

  /**
   * Used to refresh the queue. Only really calls the same method in Queue.
   * 
   * @return boolean - Returns true if successful, return false if not.
   */
  public boolean refreshQueue() {
    boolean refreshed = queue.refreshQueue();
    setChanged();
    notifyObservers();
    return refreshed;
  }

  // checks to see if there is a refresh remaining, value true means there is
  public boolean refreshLeft() {
    return queue.refreshLeft();
  }

  // returns the value of totalScore
  public int getScore() {
    return totalScore;
  }

  // returns the score acquired from a single move, before it is added to total
  public int getMoveScore() {
    return moveScore;
  }

  // returns the number of non-null (non 11) tiles on the board, to determine how close a player is
  // to winning
  public int getBoardStatus() {
    return gameBoard.boardStatus();
  }

  public int getMinutes() {
    return minutes;
  }

  public int getSeconds() {
    return seconds;
  }

  /**
   * Accessor for the raw time
   * 
   * @return int - the raw time remaining (seconds left)
   */
  public int getRawTime() {
    return rawSeconds;
  }

  // Future
  public int getTiles() {
    return gameBoard.boardStatus();
  }

  /**
   * Method used in debug mode, that generates a 'winable' gameboard and queue. The queue and
   * gameBoard are predetermined, and the time is set to a shorter time.
   */
  public void debugGame() {
    int[][] newBoard = new int[9][9];
    int[] newQueue = { 7, 2, 3, 4, 5 };
    // fill the new board with 11s (empty spaces)
    for (int x = 0; x <= 8; x++) {
      for (int y = 0; y <= 8; y++) {
        newBoard[x][y] = 11;
      }
    }
    // set up an easily won situation
    newBoard[2][3] = 6;
    newBoard[2][4] = 4;
    newBoard[3][3] = 4;
    newBoard[4][4] = 3;
    gameBoard.debugBoard(newBoard);
    queue.debugQueue(newQueue);
    // reset the clock to a shorter time
    rawSeconds = 30;
    setChanged();
    notifyObservers();
  }

  /**
   * Method which generates a fresh board
   */
  public void newGame() {
    gameBoard.newBoard();
    queue.newQueue();
    tileRemove = true;
    hints = 3;
    rawSeconds = 180;
    setChanged();
    notifyObservers();
  }

  /**
   * Method which removes all instances of the value passed into it from the board.
   * 
   * @param value
   *          The value that will be removed from the board.
   * @return boolean - returns true if there was a remove left, and tiles were removed, and false
   *         elsewise.
   */
  public boolean removeTiles(int value) {
    if (tileRemove == true) {
      gameBoard.removeTiles(value);
      setChanged();
      notifyObservers();
      tileRemove = false;
      return true;
    }
    return false;
  }

  /**
   * Tells the caller what the 'best' moves are, defined as removing most tiles.
   * 
   * @return int[][] An array where holding a group of 2 ints (an x & y) that are the 'best' moves.
   */
  public ArrayList<int[]> getHint() {
    if (hints > 0) {
      hints--;
      return gameBoard.getHint(viewTop());
    } else {
      ArrayList<int[]> noHint = new ArrayList<>();
      return noHint;
    }
  }

  // returns the number of hints remaining
  public int hintsRemaining() {
    return hints;
  }

  /**
   * Method which tests if a score is a high score. Return value tells the caller to get a name and
   * give it to insertScore().
   * 
   * @param score
   *          the score of the winner
   * @return boolean - returns true if a high score, false if not.
   */
  public boolean isHighScore(int score) {
    int position = highScores.newHighScore(score);
    if (position == -1) {
      return false;
    }
    return true;
  }

  /**
   * Method that inserts a score (saved locally to high score via isHighScore() with the name.
   * 
   * @param name
   *          the name of the winner to insert into the list.
   */
  public void insertScore(String name) {
    highScores.insertScore(name);
  }

  public String[] getScores() {
    String[] scores = new String[10];
    for (int i = 0; i < 10; i++) {
      if (highScores.getName(i) == null) {
        scores[i] = " ";
      } else {
        scores[i] = String.format("%20s   %10s    %s", highScores.getName(i),
            highScores.getScore(i), highScores.getDate(i));
      }
    }
    return scores;
  }

  /**
   * Method which tests if a time is a high score time. Return value tells the caller to get a name
   * and give it to insertTimedScore().
   * 
   * @param score
   *          the score of the winner
   * @return boolean - returns true if a high score, false if not.
   */
  public boolean isTimedHighScore(int score) {
    int position = timedScores.newHighScore(score);
    if (position == -1) {
      return false;
    }
    return true;
  }

  /**
   * Method that inserts a time (saved locally to high score via isTimedHighScore() with the name.
   * 
   * @param name
   *          the name of the winner to insert into the list.
   */
  public void insertTimedScore(String name) {
    timedScores.insertScore(name);
  }

  public String[] getTimedScores() {
    String[] scores = new String[10];
    for (int i = 0; i < 10; i++) {
      if (highScores.getName(i) == null) {
        scores[i] = " ";
      } else {
        scores[i] = String.format("%20s   %10s    %s", highScores.getName(i),
            highScores.getScore(i), highScores.getDate(i));
      }

    }
    return scores;
  }

  /**
   * Method which saves the scores in highScores and timedScore to their respective files
   */
  public void saveScores() {
    highScores.writeToFile();
    timedScores.writeToFile();
  }

  // private class for the GameTimer (which is used to keep track of time)
  private class GameTimer extends Timer {
    public GameTimer(int arg0, ActionListener arg1) {
      super(arg0, arg1);
      // TODO Auto-generated constructor stub
    }
  }

  // action listener that decreases rawSeconds, calculates minutes and seconds, and notifies
  // observers
  ActionListener taskPerformer = new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
      rawSeconds--;
      seconds = rawSeconds % 60;
      minutes = rawSeconds / 60;
      setChanged();
      notifyObservers();
    }
  };
}