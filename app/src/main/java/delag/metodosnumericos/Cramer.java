package delag.metodosnumericos;

import android.support.annotation.NonNull;

/**
 * Created by Santino on 13/05/2017.
 */

public class Cramer {

    double[][] A;
    double[][] M;
    double[] res;
    double[] B;
    final int height;
    final int length;
    private final int maxCount = 1000;

    public Cramer(@NonNull double[][] A, double[] res) {
        this.A = A;
        if (A.length == 0 || A[0].length == 0) {
            throw new IllegalArgumentException("Matrix must be at least 1x1");
        }
        if (A.length + 1 != A[0].length) {
            throw new IllegalArgumentException("Matrix must be n x n+1");
        }
        this.res = res == null ? new double[A.length] : res;
        if (this.res.length != A.length) {
            throw new IllegalArgumentException("Guess doesn't match");
        }
        height = A.length;
        length = A[0].length;
        B=new double[A.length];
        M= new double[A.length][A.length];
    }

    public double[] doMethod() {
        for (int i=0;i<height;i++){
            for (int j=0;j<height;j++){
                M[i][j]=A[i][j];
            }
        }

        for (int i=0;i<height;i++){
            B[i]=A[i][length-1];
        }

        double temp[][] = new double[height][height];
        double res[] = new double[height];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<height;j++){
                for(int k=0;k<height;k++){
                    if(k == i)
                        temp[j][k] = B[j];
                    else
                        temp[j][k] = M[j][k];
                }}
            res[i]=determinant(temp,height)/determinant(M,height);


        }
        return res;
    }

    private double determinant(double A[][],int N)
    {

        double det=0;
        double x;


        if(N == 1)
            x = A[0][0];

        else if (N == 2)
        {
            x = A[0][0]*A[1][1] - A[1][0]*A[0][1];
        }

        else
        {
            x=0;
            for(int j1=0;j1<N;j1++)
            {
                double m[][] = new double[N-1][];
                for(int k=0;k<(N-1);k++)
                    m[k] = new double[N-1];
                for(int i=1;i<N;i++)
                {
                    int j2=0;
                    for(int j=0;j<N;j++)
                    {
                        if(j == j1)
                            continue;
                        m[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                x += Math.pow(-1.0,1.0+j1+1.0)* A[0][j1] * determinant(m,N-1);
            }
        }

        return x;

    }

}
