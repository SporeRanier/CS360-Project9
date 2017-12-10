package sprint;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class acts as a 9x9 gameboard. Each "tile" stores an int 0-9 randomly generated. Contains
 * methods to generate the board, and interact with it
 * 
 * @author David Bell
 * @version 1.0
 * @since 2017-1-18
 */
public class GameBoard {
  private static GameBoard boardInstance = new GameBoard();
  // stores the actual board
  private int[][] gameBoard;
  private int tilesPlaced;

  /**
   * Constructor for gameboard, which invokes generateBoard(), creating a fresh board
   */
  private GameBoard() {
    generateBoard();
  }

  /**
   * method used to generate a fresh board (9x9, inner 7x7 populated with 0-9)
   */
  private void generateBoard() {
    Random random = new Random();
    tilesPlaced = 0;
    // instantiates gameBoard of 9x9
    gameBoard = new int[9][9];
    // for rows 1 - 7 (leaving 0 and 8 empty)
    for (int x = 0; x <= 8; x++) {
      // for columns 1 - 7 (leaving 0 and 8 empty)
      for (int y = 0; y <= 8; y++) {
        if (x == 0 || x == 8 || y == 0 || y == 8) {
          // 11 will be the stand-in for empty
          gameBoard[x][y] = 11;
          // place a random number from 0 to 9 in the spot
        } else {
          gameBoard[x][y] = random.nextInt(10);
          tilesPlaced++;
        }
      }
    }
  }

  /**
   * Is the only way to access the singleton GameBoard
   * 
   * @return GameBoard - the instance of the gameboard
   */
  public static GameBoard getBoard() {
    return boardInstance;
  }

  /**
   * Returns a copy of the values on the gameBoard, so they may be displayed.
   * 
   * @return int[][] Returns the actual values of the board
   */
  public int[][] viewBoard() {
    int[][] copyBoard = new int[9][];
    // create a copy of the gameBoard to show the player
    for (int i = 0; i < 9; i++) {
      copyBoard[i] = gameBoard[i].clone();
    }
    return copyBoard;
  }

  /**
   * Method which returns an array holding the min and max values for the x & y axis. This is used
   * to keep the caller from trying to access a space which is out of bonds.
   * 
   * @param x
   *          - The X coordinate of the tile in question.
   * @param y
   *          - The Y coordinate of the tile in question.
   * @return int[] - Returns [0] min for x, [1] min for y, [2] max for x, [3] max for y.
   */
  private int[] getMinMax(int x, int y) {
    int[] minMax = new int[4];
    // indicates the bounds of checked area (being 1 away from the center (x,y))
    // minMax[0] is xMin, [1] is yMin, [2] is xMax, [3] is yMax
    minMax[0] = x - 1;
    minMax[1] = y - 1;
    minMax[2] = x + 1;
    minMax[3] = y + 1;
    // if the min is out of bounds (-1), shift the min to stay in bounds
    // shift the x min up
    if (minMax[0] == -1) {
      minMax[0]++;
    }
    // shift the y min up
    if (minMax[1] == -1) {
      minMax[1]++;
    }
    // if the max is out of bounds (9), shift the max to stay in bounds
    // shift the x max down
    if (minMax[2] == 9) {
      minMax[2]--;
    }
    // shift the y max down
    if (minMax[3] == 9) {
      minMax[3]--;
    }
    // return
    return minMax;
  }

  /**
   * Method takes care of the placing of a tile, returning the score if successful, -1 if not.
   * 
   * @param x
   *          The X coordinate of the placed tile.
   * @param y
   *          The Y coordinate of the placed tile.
   * @param value
   *          This is the value of the tile placed.
   * @return int Returns the score resulting from the placement of the tile. Score is -1 if the tile
   *         is occupied.
   */
  public int placeTile(int x, int y, int value) {
    // if the space is already occupied (being not 11), return an error code (-1)
    if (gameBoard[x][y] != 11) {
      return -1;
    }
    // place the newly input value (parameter) into the gameboard
    gameBoard[x][y] = value;
    tilesPlaced++;
    // values to track total value of surrounding tiles, and the number of those tiles
    int total = 0;
    int tileCount = 0;
    // get an array of bounds
    int[] minMax = getMinMax(x, y);
    // iterate through from min to max of x & y to determine total
    for (int j = minMax[0]; j <= minMax[2]; j++) {
      for (int k = minMax[1]; k <= minMax[3]; k++) {
        // ignore a space if it is empty (11), or is the newly placed tile (j==x, k==y)
        if (gameBoard[j][k] != 11 && (!(j == x && k == y))) {
          total += gameBoard[j][k];
          tileCount += 1;
        }
      }
    }

    // check to see if the total is equal to the value of the placed tile
    if ((total % 10) == value) {
      clearAround(x, y);
      // tilesPlaced = tilesPlaced - tileCount - 1;
      // calculates the score if there are three+ tiles removed (that aren't the
      if (tileCount >= 3) {
        return total + (total * tileCount);
      }
      return total;

      // This line removes 0 placed on the board if they don't remove other 0s
    } else if (value == 0) {
      gameBoard[x][y] = 11;
      tilesPlaced--;
    }
    // Result of 0 means no tiles removed
    return 0;
  }

  /**
   * Method takes care of the placing of a tile, returning the score if successful, -1 if not.
   * 
   * @param x
   *          The X coordinate of the placed tile.
   * @param y
   *          The Y coordinate of the placed tile.
   * @param value
   *          This is the value of the tile placed.
   * @return int - Returns the number of tiles the placement of value in this position would remove.
   */
  public int getTilesRemoved(int x, int y, int value) {
    // if the space is already occupied (being not 11), return an error code (-1)
    if (gameBoard[x][y] != 11) {
      return -1;
    }
    // values to track total value of surrounding tiles, and the number of those tiles
    int total = 0;
    int tileCount = 0;
    // get an array of bounds
    int[] minMax = getMinMax(x, y);
    // iterate through from min to max of x & y to determine total
    for (int j = minMax[0]; j <= minMax[2]; j++) {
      for (int k = minMax[1]; k <= minMax[3]; k++) {
        // ignore a space if it is empty (11), or is the tile itself (x,y in parameters)
        if (gameBoard[j][k] != 11 && (!(j == x && k == y))) {
          total += gameBoard[j][k];
          tileCount += 1;
        }
      }
    }
    if ((total % 10) == value) {
      return tileCount;
    }
    return 0;
  }

  /**
   * Method which returns an array of the .
   * 
   * @return ArrayList<int[]> Returns an ArrayList of the best moves. The first(and only) value if
   *         there are no moves is {-1, -1}.
   */
  public ArrayList<int[]> getHint(int value) {
    ArrayList<int[]> potential = new ArrayList<>();
    int[] coordinates = new int[2];
    // marker for most number of tiles removed
    int topTiles = 1;
    System.out.println("---------------------------");
    // iterate through entire board
    for (int x = 0; x <= 8; x++) {
      for (int y = 0; y <= 8; y++) {
        int current = getTilesRemoved(x, y, value);
        // if new best
        if (current > topTiles) {
          coordinates[0] = x;
          coordinates[1] = y;
          potential.add(coordinates);
          topTiles = current;
          System.out.println("---");
          System.out.printf("%d,%d\n", x, y);
          // if equivalent best
        } else if (current == topTiles) {
          coordinates[0] = x;
          coordinates[1] = y;
          potential.add(coordinates);
          System.out.printf("%d,%d\n", x, y);
        }
      }
    }
    if (potential.isEmpty()) {
      coordinates[0] = -1;
      coordinates[1] = -1;
      potential.add(coordinates);
    }
    return potential;
  }

  /**
   * Method which removes (changes value to 11) all the tiles around the specified tile
   * 
   * @param x
   *          The X coordinate of the placed tile.
   * @param y
   *          The Y coordinate of the placed tile.
   */
  private void clearAround(int x, int y) {
    // get the array of bounds
    int[] minMax = getMinMax(x, y);
    // iterate through from min to max of x & y (bounds) to replace with empty spaces (11)
    for (int j = minMax[0]; j <= minMax[2]; j++) {
      for (int k = minMax[1]; k <= minMax[3]; k++) {
        if (gameBoard[j][k] != 11) {
          gameBoard[j][k] = (int) 11;
          tilesPlaced--;
        }
      }
    }
  }

  /**
   * Method which allows the caller to see how many tiles have been placed on the board
   * 
   * @return int - the number of nonempty tiles which are currently on the board
   */
  public int boardStatus() {
    return tilesPlaced;
  }

  /**
   * Method which is used to create a fresh gameBoard for a new game
   */
  public void newBoard() {
    generateBoard();
  }

  /**
   * Method which removes all instances of the value passed into it from the board
   * 
   * @param value
   *          The value that will be removed from the board
   */
  public void removeTiles(int value) {
    for (int x = 0; x <= 8; x++) {
      for (int y = 0; y <= 8; y++) {
        if (gameBoard[x][y] == value) {
          gameBoard[x][y] = 11;
        }
        tilesPlaced--;
      }
    }
  }

  /**
   * This method allows a specially constructed board to be input. Method assumes the input array is
   * of correct format (9x9, values 0-9).
   * 
   * @param values
   *          The 9x9 array of new values to be used as a board
   */
  public void debugBoard(int[][] values) {
    tilesPlaced = 0;
    for (int x = 0; x <= 8; x++) {
      for (int y = 0; y <= 8; y++) {
        gameBoard[x][y] = values[x][y];
        if (values[x][y] != 11) {
          tilesPlaced++;
        }
      }
    }
  }
}