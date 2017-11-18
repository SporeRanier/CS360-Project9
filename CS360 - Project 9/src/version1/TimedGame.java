package version1;
//Author David Bell
/*holds all information for a single, untimed game 
 *(including Gameboard, Queue, Moves, and Score) 
 */
import java.util.*;

public class TimedGame extends Observable
{
	private static TimedGame timedInstance = new TimedGame();
	
	private GameBoard gameBoard;
	private Queue queue;
	//TODO: private __ time; //3:00 = 180 seconds
	private int totalScore = 0;
	private int moveScore = 0;
	//constructor for a singleton TimedGame
	private TimedGame()
	{
		gameBoard = GameBoard.getBoard();
		queue = Queue.getQueue();
		//TODO: Time = 2:00;
	}
	//accessor for a TimedGame
	public static TimedGame getTimedGame()
	{
		return timedInstance;
	}
	//returns a copy of the board
	public int[][] viewBoard()
	{
		return gameBoard.viewBoard();
	}
	/* Places a tile, using the top of the queue
	 * Return value of -1 means the tile is occupied, 
	 * value 0 means the tile was simply place,
	 * value >= 1 means a tile was placed, and the returned value is the score
	 */
	public int placeTile(int x, int y)
	{
		moveScore = gameBoard.placeTile(x, y, queue.viewTop());
		//score of -1 indicates that tile is already occupied, so nothing is done
		if (moveScore == -1)
		{
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
	public int[] viewQueue()
	{
		return queue.viewQueue();
	}
	//refreshes the queue, true means the refresh happened
	public boolean refreshQueue()
	{
		return queue.refreshQueue();
	}
	//checks to see if there is a refresh remaining, value true means there is
	public boolean refreshLeft()
	{
		return queue.refreshLeft();
	}
	//returns the value of totalScore
	public int getScore()
	{
		return totalScore;
	}
}
