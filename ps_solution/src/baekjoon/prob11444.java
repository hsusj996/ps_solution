package baekjoon;

import java.io.*;
import java.math.BigInteger;

public class prob11444 {
  static final int MOD = 1_000_000_007;

  static class Matrix {
    int size;
    long[][] mat;

    public Matrix(int size) {
      this.size = size;
      mat = new long[size][size];
    }

    public Matrix multiply(Matrix m) {
      Matrix ret = new Matrix(this.size);

      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          for (int k = 0; k < size; k++) {
            ret.mat[i][j] += this.mat[i][k] * m.mat[k][j];
            ret.mat[i][j] %= MOD;
          }
        }
      }

      return ret;
    }
  }

  static BigInteger N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = new BigInteger(br.readLine());

    Matrix m = new Matrix(2);
    m.mat[0][0] = 1;
    m.mat[0][1] = 1;
    m.mat[1][0] = 1;
    m.mat[1][1] = 0;

    Matrix res = new Matrix(2);
    res.mat[0][0] = 1;
    res.mat[1][1] = 1;

    while (N.compareTo(BigInteger.valueOf(0)) != 0) {
      if (N.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(1)) == 0) {
        res = res.multiply(m);
        N = N.add(BigInteger.valueOf(-1));
      } else {
        m = m.multiply(m);
        N = N.divide(BigInteger.valueOf(2));
      }
    }

    System.out.println(res.mat[1][0]);
  }
}
