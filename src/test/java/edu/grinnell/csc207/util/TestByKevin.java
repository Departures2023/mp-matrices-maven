package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



/**
 * Tests for the MatrixV0.
 *
 * @author Kevin Tang
 */

class TestByKevin {

  // test Constructor
  @Test
  public void testConstructor() {
    MatrixV0<Boolean> matrix1 = new MatrixV0<>(6, 6, false);
    MatrixV0<Integer> matrix2 = new MatrixV0<>(4, 4);
    // test Constructor not null
    assertEquals(false, matrix1.get(0, 0));
    assertEquals(false, matrix1.get(2, 2));
    // test Constructor null
    assertNull(matrix2.get(0, 0));
  } // testConstructor



  // Test set method
  @Test
  public void testSet() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 4, 0);
    matrix.set(1, 2, 6);
    assertEquals(Integer.valueOf(6), matrix.get(1, 2));
  } // testSet



  // Test height and width method
  @Test
  public void testHeight() {
    MatrixV0<Integer> matrix = new MatrixV0<>(4, 5, 3);
    assertEquals(5, matrix.height());
    assertEquals(4, matrix.width());
  } // testHeight



  // Test insert and delete Row method
  @Test
  public void testInsertRow() throws ArraySizeException {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 2);
    Integer[] vals = {4, 5, 6};
    matrix.insertRow(1, vals);
    assertEquals(4, matrix.height());
    assertEquals(Integer.valueOf(4), matrix.get(1, 0));
    assertEquals(Integer.valueOf(5), matrix.get(1, 1));
    assertEquals(Integer.valueOf(6), matrix.get(1, 2));

    matrix.deleteRow(1);
    assertEquals(3, matrix.height());
    assertEquals(2, matrix.get(1, 0));
  } // testInsertRow



  // Test insertCol method
  @Test
  public void testInsertCol() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 2);
    matrix.insertCol(2);
    assertEquals(4, matrix.width());
    assertEquals(2, matrix.get(0, 2));

    matrix.deleteCol(2);
    assertEquals(3, matrix.width());
    assertEquals(2, matrix.get(0, 2));
  } // testInsertCol




  // Test fillRegion() method
  @Test
  public void testFillRegion() {
    MatrixV0<Integer> matrix = new MatrixV0<>(4, 4, 0);
    matrix.fillRegion(1, 1, 3, 3, 5);
    assertEquals(5, matrix.get(2, 2));
    assertEquals(0, matrix.get(0, 0));
  } // testFillRegion




  @Test
  public void testFillLine() {
    MatrixV0<Integer> matrix = new MatrixV0<>(3, 3, 0);
    matrix.fillLine(0, 0, 1, 1, 3, 3, 5);
    assertEquals(5, matrix.get(0, 0));
    assertEquals(5, matrix.get(1, 1));
    assertEquals(5, matrix.get(2, 2));
    assertEquals(0, matrix.get(0, 1));
  } // testFillLine

} // TestByKevin
