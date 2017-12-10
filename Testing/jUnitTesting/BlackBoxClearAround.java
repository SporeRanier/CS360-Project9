package jUnitTesting;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import sprint.GameBoard;

public class BlackBoxClearAround {
  static GameBoard gameBoard;
  
  @Test
  public void testMinMax(){
    gameBoard = GameBoard.getBoard();
    int[][] boardState1 = gameBoard.viewBoard();
    gameBoard.clearAround(-1, 9);
    int[][] test1Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState1, test1Act);
    
    gameBoard = GameBoard.getBoard();
    int[][] boardState2 = gameBoard.viewBoard();
    gameBoard.clearAround(9, -1);
    int[][] test2Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState2, test2Act);
    
    gameBoard = GameBoard.getBoard();
    int[][] boardState3 = gameBoard.viewBoard();
    boardState3[0][5] = 11; boardState3[0][6] = 11; boardState3[0][7] = 11;
    boardState3[1][5] = 11; boardState3[1][6] = 11; boardState3[1][7] = 11;
    //boardState3[0][5] = 11; boardState3[0][6] = 11; boardState3[0][7] = 11;
    gameBoard.clearAround(0, 6);
    int[][] test3Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState3, test3Act);
    
    gameBoard = GameBoard.getBoard();
    int[][] boardState4 = gameBoard.viewBoard();
    boardState4[7][2] = 11; boardState4[7][8] = 11;
    boardState4[7][3] = 11; boardState4[7][3] = 11;
    boardState4[7][4] = 11; boardState4[7][4] = 11;
    gameBoard.clearAround(8, 3);
    int[][] test4Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState4, test4Act);
    
    gameBoard = GameBoard.getBoard();
    int[][] boardState5 = gameBoard.viewBoard();
    boardState5[7][0] = 11; boardState5[8][0] = 11;
    boardState5[7][1] = 11; boardState5[8][1] = 11;
    gameBoard.clearAround(8, 0);
    int[][] test5Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState5, test5Act);
    
    gameBoard = GameBoard.getBoard();
    int[][] boardState6 = gameBoard.viewBoard();
    gameBoard.clearAround(-1, 7);
    int[][] test6Act = gameBoard.viewBoard();
    Assert.assertArrayEquals(boardState6, test6Act);
  }
}