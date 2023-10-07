package ru.ns.alg_lab;

public class Gen2DArray {

    public static int[][] table;
    public static int target;
    public static int genType = 1;


    /**
     * Generates table where table[i][j] = (n / m * i + j) * 2, target = 2 * n + 1
     **/
    public static void setFirstGenTable(int m, int n) {
        target = 2*n+1;
        table = new int[m][n];;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                table[i][j] = (n / m * i + j) * 2;
    }

    /**
     * Generates table where table[i][j] = (n / m * i * j) * 2, target = 16 * n + 1
     **/
    public static void setSecondGenTable(int m, int n) {
        target = 16*n+1;
        table = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                table[i][j] = (n / m * i * j) * 2;
    }

    public static void genTable(int m, int n) {
        if(genType == 1) {
            setFirstGenTable(m, n);
        } else {
            setSecondGenTable(m, n);
        }
    }
}
