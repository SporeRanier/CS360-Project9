package sprint;

import java.util.Random;

/**
* This class acts as a 5x Queue for use in Sum Fun
* Each space on the queue stores a value 0-9, randomly generated.
* Contains methods to generate the Queue, and interact with it.
* @author  David Bell
* @version 1.0
* @since   2017-1-18 
*/

public class Queue {
	private static Queue queueInstance = new Queue();
	
	private Random random = new Random();
	private int[] queue;
	private boolean refreshLeft = true;
	//constructor for queue, with a length of 5 numbers randomly taken from 0 - 9
	private Queue(){
		generateQueue();
	}

	//as Queue is a singleton (I hope anyway), this method is the manner in which it is accessed
	public static Queue getQueue(){
		return queueInstance;
	}
	//method which generates a queue of 5 random integers(0-9)
	private void generateQueue(){
		queue = new int[5];
		for (int x = 0; x < queue.length; x++){
			queue[x] = random.nextInt(9);
		}
	}
	//returns a copy of the queue
	/** Method returning a copy of the queue for display in a GUI.
	   * @return int[] - The copy of the queue that is returned.
	   */
	public int[] viewQueue(){
		int[] newQueue = new int[5];
		newQueue = queue.clone();
		return newQueue;
	}
	//removes the top value from the queue, replaces the value on the bottom, then returns the value of the old top of the queue
	/** Method which removes the top of the queue, moves all values up, then generates a new value at the bottom.
	   * @return int returns the old top of the queue.
	   */
	public int useQueue(){	
		//save the top of the queue
		int top = queue[0];
		//move the queue up 1 space
		for (int x = 0; x < queue.length - 1; x++){
			queue[x] = queue[x+1];
		}
		//generate a new value at the bottom of the queue
		queue[queue.length-1] = random.nextInt(10);
		return top;
	}
	//returns the first value in the queue.
	public int viewTop(){
		return queue[0];
	}
	//returns if there is a refresh left (true), or if it has already been used (false)
	public boolean refreshLeft(){
		return refreshLeft;
	}
	//if a refresh is remaining, the queue is newly generated, and true is returned. Otherwise, false is returned
	/** Attempts to refresh the queue (generate 5 new values). 
	 *  Will refresh if refreshLeft is True. If it does, this value is set to false.
	   * @return boolean Returns true if the queue was refreshed, false if not.
	   */
	public boolean refreshQueue(){
		if (refreshLeft == true){
			generateQueue();
			refreshLeft = false;
			//notify observers
			return true;
		}
		return refreshLeft;
	}
	
	//Generates a fresh queue
	public void newQueue(){
		generateQueue();
		refreshLeft = true;
	}

	/** This method allows a predetermined queue to be made.
	   * @param values - takes in an array of 5 values for queue. Assumes the format is correct (values 0-9)
	   */
	public void debugQueue(int[] values){ 
		for (int x = 0; x <=4; x++){
			queue[x] = values[x];
		}
	}
}
