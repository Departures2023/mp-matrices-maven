package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Kevin Tang
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * width of matrix.
   */
  int widthF;
  /**
   * height of matrix.
   */
  int heightF;
  /**
   * default value of matrix.
   */
  T defF;
  /**
   * arr of matrix.
   */
  T[][] arrF;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if statement

    this.widthF = width;
    this.heightF = height;
    this.defF = def;
    this.arrF = (T[][]) new Object[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.arrF[i][j] = def;
      } // for loop
    } // for loop
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < 0 || row >= this.heightF || col < 0 || col >= this.widthF) {
      throw new IndexOutOfBoundsException("row: " + row + "   col" + col);
    } // if statement
    if (this.arrF[row][col] == null) {
      return this.defF;
    } else {
      return this.arrF[row][col];
    } // if statement
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < 0 || row >= this.heightF || col < 0 || col >= this.widthF) {
      throw new IndexOutOfBoundsException("row: " + row + "   col" + col);
    } // if statement
    this.arrF[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.heightF;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.widthF;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    try {
      this.insertRow(row, (T[]) new Object[this.widthF]);
    } catch (ArraySizeException e) {
      e.printStackTrace();
    } // try-catch
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.heightF) {
      throw new IndexOutOfBoundsException();
    } // if statement

    if (vals.length != this.widthF) {
      throw new ArraySizeException();
    } // if statement

    T[][] arrCopy = (T[][]) new Object[heightF + 1][widthF];

    for (int j = 0; j < this.widthF; j++) {
      for (int i = 0; i < row; i++) {
        arrCopy[i][j] = this.get(i, j);
      } // for loop

      arrCopy[row][j] = vals[j];

      for (int i = row + 1; i < this.heightF + 1; i++) {
        arrCopy[i][j] = this.get(i - 1, j);
      } // for loop
    } // for loop

    this.arrF = arrCopy;
    this.heightF++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    try {
      this.insertCol(col, (T[]) new Object[this.heightF]);
    } catch (ArraySizeException e) {
      e.printStackTrace();
    } // try-catch
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    //System.out.println("Initial width before adding column:: " + this.width);
    if (col < 0 || col > this.widthF) {
      throw new IndexOutOfBoundsException();
    } // if statement

    if (vals.length != this.heightF) {
      throw new ArraySizeException();
    } // if statement

    T[][] arrCopy = (T[][]) new Object[heightF][widthF + 1];

    for (int j = 0; j < this.heightF; j++) {
      for (int i = 0; i < col; i++) {
        arrCopy[j][i] = this.get(j, i);
      } // for loop

      arrCopy[j][col] = vals[j];

      for (int i = col + 1; i < this.widthF + 1; i++) {
        arrCopy[j][i] = this.get(j, i - 1);
      } // for loop
    } // for loop

    this.arrF = arrCopy;
    this.widthF++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row > this.heightF) {
      throw new IndexOutOfBoundsException();
    } // if statement

    T[][] arrCopy = (T[][]) new Object[heightF - 1][widthF];

    for (int j = 0; j < this.widthF; j++) {
      for (int i = 0; i < row; i++) {
        arrCopy[i][j] = this.get(i, j);
      } // for loop

      for (int i = row + 1; i < this.heightF; i++) {
        arrCopy[i - 1][j] = this.get(i, j);
      } // for loop
    } // for loop

    this.arrF = arrCopy;
    this.heightF--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col > this.widthF) {
      throw new IndexOutOfBoundsException();
    } // if statement

    T[][] arrCopy = (T[][]) new Object[heightF][widthF - 1];

    for (int j = 0; j < this.heightF; j++) {
      for (int i = 0; i < col; i++) {
        arrCopy[j][i] = this.get(j, i);
      } // for loop

      for (int i = col + 1; i < this.widthF; i++) {
        arrCopy[j][i - 1] = this.get(j, i);
      } // for loop
    } // for loop

    this.arrF = arrCopy;
    this.widthF--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow < 0 || startRow >= this.heightF || startCol < 0 || startCol >= this.widthF) {
      throw new IndexOutOfBoundsException();
    } // if statement
    if (endRow < 0 || endRow > this.heightF || endCol < 0 || endCol > this.widthF) {
      throw new IndexOutOfBoundsException();
    } // if statement

    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        set(i, j, val);
      } // for loop
    } // for loop
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int row = startRow;
    int col = startCol;

    while (row < endRow && col < endCol) {
      if (row >= 0 && row < this.heightF && col >= 0 && col < this.widthF) {
        set(row, col, val);
      } else {
        throw new IndexOutOfBoundsException();
      } // if statement
      row += deltaRow;
      col += deltaCol;
    } // while loop
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    Matrix<T> newMatrix = new MatrixV0<T>(this.heightF, this.widthF);
    for (int i = 0; i < this.heightF; i++) {
      for (int j = 0; j < this.widthF; j++) {
        newMatrix.set(i, j, this.get(i, j));
      } // for loop
    } // for loop
    return newMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (!(other instanceof MatrixV0<?>)) {
      return false;
    } // if statement
    Matrix otherMatrix = (Matrix) other;
    if (this.widthF != otherMatrix.width() || this.heightF != otherMatrix.height()) {
      return false;
    } // if statement
    for (int i = 0; i < this.heightF; i++) {
      for (int j = 0; j < this.widthF; j++) {
        if (!this.get(i, j).equals(otherMatrix.get(i, j))) {
          return false;
        } // if statement
      } // for loop
    } // for loop
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
