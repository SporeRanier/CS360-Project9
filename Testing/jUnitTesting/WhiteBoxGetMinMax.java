package jUnitTesting;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import sprint.GameBoard;

public class WhiteBoxGetMinMax {
  static GameBoard gameBoard;
  
  @Test
  public void testMinMax(){
    gameBoard = GameBoard.getBoard();
    int[] test1Act = gameBoard.getMinMax(-1, -1);
    int[] test1Exp = {-1,-1,-1,-1};
    Assert.assertArrayEquals(test1Exp, test1Act);
    
    int[] test2Act = gameBoard.getMinMax(0,0);
    int[] test2Exp = {0,0,1,1};
    Assert.assertArrayEquals(test2Exp, test2Act);
    
    int[] test3Act = gameBoard.getMinMax(8, 8);
    int[] test3Exp = {7,7,8,8};
    Assert.assertArrayEquals(test3Exp, test3Act);
  }
}
