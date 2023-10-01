public class Gauss {

  static void performElimination(Matrix matrix) {
    int singular_flag = forwardElim(matrix);

    /* if matrix is singular */
    if (singular_flag != -1) {
      System.out.println("Singular Matrix.");

      /* if the RHS of equation corresponding to
               zero row  is 0, * system has infinitely
               many solutions, else inconsistent*/
      if (matrix.getElmt(singular_flag, matrix.getRows()) != 0) System.out.print(
        "Inconsistent System."
      ); else System.out.print("May have infinitely many solutions.");

      return;
    }
    
    backSubstitute(matrix);
  }

  // function for elementary operation of swapping two
  // rows
  static void swap_row(Matrix matrix, int i, int j) {
    for (int k = 0; k <= matrix.getRows(); k++) {
      double temp = matrix.getElmt(i, k);
      matrix.setElmt(i, k, matrix.getElmt(j, k));
      matrix.setElmt(i, k, temp);
    }
  }

  // function to reduce matrix to r.e.f.
  static int forwardElim(Matrix matrix) {
    for (int k = 0; k < matrix.getRows(); k++) {
      // Initialize maximum value and index for pivot
      int i_max = k;
      double v_max = matrix.getElmt(i_max, k);

      /* find greater amplitude for pivot if any */
      for (int i = k + 1; i < matrix.getRows(); i++) if (Math.abs(matrix.getElmt(i, k)) > v_max) {
        v_max = matrix.getElmt(i, k);
        i_max = i;
      }

      /* if a principal diagonal element  is zero,
       * it denotes that matrix is singular, and
       * will lead to a division-by-zero later. */
      if (matrix.getElmt(k, i_max)== 0) return k; // Matrix is singular

      /* Swap the greatest value row with current row
       */
      if (i_max != k) swap_row(matrix, k, i_max);

      for (int i = k + 1; i < matrix.getRows(); i++) {
        /* factor f to set current row kth element
         * to 0, and subsequently remaining kth
         * column to 0 */
        double f = matrix.getElmt(i, k) / matrix.getElmt(k, k);

        /* subtract fth multiple of corresponding
                   kth row element*/
        for (int j = k + 1; j <= matrix.getRows(); j++){
          double temp = matrix.getElmt(i, j) - matrix.getElmt(k,j) * f;
          matrix.setElmt(i, j, temp);
        }

        /* filling lower triangular matrix with
         * zeros*/
        matrix.setElmt(i, k, 0);
      }
      // print(mat);        //for matrix state
    }

    // print(mat);            //for matrix state
    return -1;
  }

  // function to calculate the values of the unknowns
  public static void backSubstitute(Matrix matrix) {
    int numRows = matrix.getRows();
    int lastCols = matrix.getCols() - 1;

    for (int i = numRows-1; i >= 0; i--) {
        double factor = matrix.getElmt(i, i);

        matrix.setElmt(i, i, 1);
        matrix.setElmt(i, lastCols, matrix.getElmt(i, lastCols) / factor);

        double temp = matrix.getElmt(i, lastCols);

        for(int j = i - 1; j >= 0; j--){
            double factor2 = matrix.getElmt(j, i);

            matrix.setElmt(j, i, 0);
            matrix.setElmt(j, lastCols, matrix.getElmt(j, lastCols) - factor2 * temp);
        }
    }
}
}
