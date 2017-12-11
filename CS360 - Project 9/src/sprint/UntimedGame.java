package sprint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Class acts as the game logic for an untimed game in Sum Fun. Contains a board, a queue, a
 * variable for moves, and for Score. Updates all variables as it is told by a GUI.
 * 
 * @author David Bell
 * @version 1.0
 * @since 2017-13-18
 */

public class UntimedGame extends Observable {
  private static UntimedGame untimedInstance = new UntimedGame();

  private GameBoard gameBoard;
  private Queue queue;
  private HighScores highScores;
  private int moves;
  private int totalScore = 0;
  private int moveScore = 0;
  private boolean tileRemove = true;
  private int hints = 3;

  // Constructor for a singleton UntimedGame
  private UntimedGame() {
    gameBoard = GameBoard.getBoard();
    queue = Queue.getQueue();
    highScores = new HighScores();
    moves = 50;
  }

  // access to the UntimedGame
  /**
   * Returns the instance of this class, as well as starting the thread for the clock.
   * 
   * @return UntimedGame The instance of this game that is returned.
   */
  public static UntimedGame getUntimedGame() {
    return untimedInstance;
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
    moves--;
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
   * @return boolean Returns true if successful, return false if not.
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

  // returns the remaining moves
  public int getMoves() {
    return moves;
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

  /**
   * Method which generates a new game, resetting all necessary values
   */
  public void newGame() {
    gameBoard.newBoard();
    queue.newQueue();
    moves = 50;
    totalScore = 0;
    moveScore = 0;
    tileRemove = true;
    hints = 3;
    setChanged();
    notifyObservers();
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
    moves = 5;
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
   *         Returns an array holding 9, 9 if no hints were left.
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

  public int hintsRemaining() {
    return hints;
  }

  /**
   * Method which checks if a score is a high score. This is used in conjuction with insertScore(),
   * which supplies the name to highScores, which has stored the score given here.
   * 
   * @param score
   *          - the integer of the score to be tested
   * @return boolean - returns true if it is a high score (so GUI can get a name, and call
   *         insertScore(), or false if not.
   */
  public boolean isHighScore(int score) {
    int position = highScores.newHighScore(score);
    if (position == -1) {
      return false;
    }
    return true;
  }

  /**
   * Method which takes a name, and inserts that name into the score that it has been assigned to
   * 
   * @param name
   *          - the String containing the name of the scorer
   */
  public void insertScore(String name) {
    highScores.insertScore(name);
  }

  /**
   * Method which returns the scores in the High Scores list.
   * 
   * @return String[][] - returns an array of the scores. [0] is names, [1] is scores, [2] is dates.
   */
  public String[] getScores() {
    String[] scores = new String[10];
    for(int i = 0; i < 10; i++){
     if(highScores.getName(i) == null){
       scores[i] = " ";
     }
     else{
       scores[i] = String.format("%20s   %10s    %s", highScores.getName(i), highScores.getScore(i), highScores.getDate(i)); 
     }
    
    }
    
    
    return scores;
  }

  /**
   * Method which saves the scores in highScores to a file before it is potentially destroyed in the
   * change from
   */
  public void saveScores() {
    highScores.writeToFile();
  }

}