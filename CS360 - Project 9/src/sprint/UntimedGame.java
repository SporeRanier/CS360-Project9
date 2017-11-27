package sprint;

import java.util.Observable;

/**
* Class acts as the game logic for an untimed game in Sum Fun.
* Contains a board, a queue, a variable for moves, and for Score.
* Updates all variables as it is told by a GUI.
* @author  David Bell
* @version 1.0
* @since   2017-13-18 
*/

public class UntimedGame extends Observable{
	private static UntimedGame untimedInstance = new UntimedGame();
	
	private GameBoard gameBoard;
	private Queue queue;
	private int moves;
	private int totalScore = 0;
	private int moveScore = 0;
	//Constructor for a singleton UntimedGame
	private UntimedGame(){
		gameBoard = GameBoard.getBoard();
		queue = Queue.getQueue();
		moves = 50;
	}
	//access to the UntimedGame
	/**  Returns the instance of this class, as well as starting the thread for the clock.
	   * @return UntimedGame The instance of this game that is returned.
	   */
	public static UntimedGame getUntimedGame(){
		return untimedInstance;
	}
	//returns a copy of the board
	public int[][] viewBoard(){
		return gameBoard.viewBoard();
	}
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
		moves--;
		totalScore += moveScore;
		//notify observers
		System.out.printf("Score: %d  MoveScore: %d\n", totalScore, moveScore);
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
	//returns the remaining moves
	public int getMoves(){
		return moves;
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
	public void newBoard()
    {
      gameBoard.newBoard();
      setChanged();
      notifyObservers();
    }
}