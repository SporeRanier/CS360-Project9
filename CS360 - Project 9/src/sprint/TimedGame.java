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

import java.util.Observable;
import java.util.Observer;


public class TimedGame extends Observable implements Observer{
	private static TimedGame timedInstance = new TimedGame();
	
	private GameBoard gameBoard;
	private Queue queue;
	private int rawSeconds;
	private int minutes;
	private int seconds;
	private int totalScore = 0;
	private int moveScore = 0;
	private static Clock clock;
	static Thread thread1;
	
	//constructor for a singleton TimedGame
	private TimedGame(){
		gameBoard = GameBoard.getBoard();
		queue = Queue.getQueue();
		clock = new Clock(180);
		clock.addObserver(this);
		thread1 = new Thread(clock);
	}
	//accessor for a TimedGame
	/**  Returns the instance of this class, as well as starting the thread for the clock.
	   * @return TimedGame The instance of this game that is returned.
	   */
	public static TimedGame getTimedGame(){
		//starts the thread for the timer
		thread1.start();
		return timedInstance;
	}
	//returns a copy of the board
	public int[][] viewBoard(){
		return gameBoard.viewBoard();
	}
	/* Places a tile, using the top of the queue
	 * Return value of -1 means the tile is occupied, 
	 * value 0 means the tile was simply place,
	 * value >= 1 means a tile was placed, and the returned value is the score
	 */
	/**  Method taking care of the placement of a tile.
	 *   Takes care of all logic with board and queue.
	 * @param x  The y coordinate of the placed tile
	 * @param y  The x coordinate of the placed tile
	 * @return int returns the score of the move to the caller. Value -1 means the tile was placed on an occupied space.
	 */
	public int placeTile(int x, int y){
		moveScore = gameBoard.placeTile(x, y, queue.viewTop());
		//score of -1 indicates that tile is already occupied, so nothing is done
		if (moveScore == -1){
			return moveScore;
		}
		//remove the top of the queue and generate a new value
		queue.useQueue();
		totalScore += moveScore;
		//notify observers
		setChanged();
		notifyObservers();
		return moveScore;
	}
	//returns a copy of the queue
	public int[] viewQueue(){
		return queue.viewQueue();
	}
	//Allows the caller to view what the top of the queue is without using it
	public int viewTop(){
		return queue.viewTop();
	}
	//refreshes the queue, true means the refresh happened
	/**  Used to refresh the queue. Only really calls the same method in Queue.
	   * @return boolean Returns true if successful, return false if not.
	   */
	public boolean refreshQueue(){
		boolean refreshed = queue.refreshQueue();
		setChanged();
		notifyObservers();
		return refreshed;
	}
	//checks to see if there is a refresh remaining, value true means there is
	public boolean refreshLeft(){
		return queue.refreshLeft();
	}
	//returns the value of totalScore
	public int getScore(){
		return totalScore;
	}
	//returns the score acquired from a single move, before it is added to total
	public int getMoveScore(){
		return moveScore;
	}
	//returns the number of non-null (non 11) tiles on the board, to determine how close a player is to winning
	public int getBoardStatus(){
		return gameBoard.boardStatus();
	}
	public int getMinutes(){
		return minutes;
	}
	public int getSeconds(){
		return seconds;
	}
	public int getRawTime(){
		return rawSeconds;
	}
	
	@Override
	//This method updates the class when the number of seconds remaining is decreased
	public void update(Observable arg0, Object arg1) {
		rawSeconds = clock.getSeconds();
		//calculates minutes/seconds from the raw seconds
		minutes = rawSeconds / 60;
		seconds = rawSeconds % 60;
		setChanged();
		notifyObservers();
	}
	//Future
	public int getTiles(){
		return gameBoard.boardStatus();
	}
	//works as a game to test the functionality of the timed game
	/** Method used in debug mode, that generates a 'winable' gameboard and queue.
	 *  The queue and gameBoard are predetermined, and the time is set to a shorter time.
	 */
	public void debugGame(){
		int [][] newBoard = new int[9][9];
		int[] newQueue = {7,2,3,4,5};
		//fill the new board with 11s (empty spaces)
		for (int x = 0; x<=8; x++){
			for (int y = 0; y<=8; y++){
				newBoard[x][y] = 11;
			}
		}
		//set up an easily won situation
		newBoard[2][3] = 6; 
		newBoard[2][4] = 4;
		newBoard[3][3] = 4;
		newBoard[4][4] = 3;
		gameBoard.debugBoard(newBoard);
		queue.debugQueue(newQueue);
		//reset the clock to a shorter time
		clock = new Clock(30);
		thread1 = new Thread(clock);
		thread1.start();
		setChanged();
		notifyObservers();
	}
	public void newBoard()
    {
      gameBoard.newBoard();
      setChanged();
      notifyObservers();
    }
	
	 /** Method which removes all instances of the value passed into it from the board
   * @param value The value that will be removed from the board
   */
  public void removeTiles(int value){
    gameBoard.removeTiles(value);
    setChanged();
    notifyObservers();
  }
}
