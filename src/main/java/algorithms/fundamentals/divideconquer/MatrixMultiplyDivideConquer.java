package algorithms.fundamentals.divideconquer;

/**
 * split the matrix and recursively do matrix multiplication
 */
public interface MatrixMultiplyDivideConquer {

  Matrix divideAndRecursiveCall(Matrix a, Matrix b, MatrixMultiply multiply);
}
