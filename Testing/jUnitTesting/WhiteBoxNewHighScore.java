package jUnitTesting;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import sprint.HighScores;

public class WhiteBoxNewHighScore {
  static HighScores highScores;
  
  @Test
  public void testMinMax(){
    highScores = new HighScores();
    
    int[] testScores1 = new int[10];
    for (int x = 0; x < 10; x++){
      testScores1[x] = (11 - x) * 10;
    }
     highScores.addScores(testScores1);
     int test1 = highScores.newHighScore(97);
     assertEquals(test1, 2);
     
     int[] testScores2 = new int[10];
     for (int x = 0; x < 10; x++){
       testScores2[x] = (11 - x) * 10;
     }
     highScores.addScores(testScores2);
     int test2 = highScores.newHighScore(4);
     assertEquals(test2, -1);
  }
}
