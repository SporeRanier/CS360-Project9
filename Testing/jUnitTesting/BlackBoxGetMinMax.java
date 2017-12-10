package jUnitTesting;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import sprint.GameBoard;

public class BlackBoxGetMinMax {
  static GameBoard gameBoard;
  
  @Test
  public void testMinMax(){
    gameBoard = GameBoard.getBoard();
    int[] test1Act = gameBoard.getMinMax(-1, 9);
    int[] test1Exp = {-1,-1,-1,-1};
    Assert.assertArrayEquals(test1Exp, test1Act);
    
    int[] test2Act = gameBoard.getMinMax(9,-1);
    int[] test2Exp = {-1,-1,-1,-1};
    Assert.assertArrayEquals(test2Exp, test2Act);
    
    int[] test3Act = gameBoard.getMinMax(0, 6);
    int[] test3Exp = {0,5,1,7};
    Assert.assertArrayEquals(test3Exp, test3Act);
    
    int[] test4Act = gameBoard.getMinMax(3, 8);
    int[] test4Exp = {2,7,4,8};
    Assert.assertArrayEquals(test4Exp, test4Act);
    
    int[] test5Act = gameBoard.getMinMax(8, 0);
    int[] test5Exp = {7,0,8,1};
    Assert.assertArrayEquals(test5Exp, test5Act);
    
    int[] test6Act = gameBoard.getMinMax(-1, 7);
    int[] test6Exp = {-1,-1,-1,-1};
    Assert.assertArrayEquals(test6Exp, test6Act);
  }
}
