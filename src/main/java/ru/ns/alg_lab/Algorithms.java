package ru.ns.alg_lab;

import java.util.Arrays;

public class Algorithms {
    /**
     * Searching for the specified value in each line in the specified array.
     *
     * @param arr   the array to be searched
     * @param value the value to be searched for
     */
    public static void basicBinary(int[][] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            int index = Arrays.binarySearch(arr[i], value);
            if (index >= 0 && index < arr[i].length && arr[i][index] == value)
                return;
        }
    }

    /**
     * Searching for the specified value in the specified array, moving from the top right corner to the bottom left.
     *
     * @param arr   the array to be searched
     * @param value the value to be searched for
     */
    public static void ladder(int[][] arr, int value) {
        int x = arr[0].length - 1;
        int y = 0;
        while (x >= 0 && y < arr.length) {
            if (arr[y][x] > value)
                --x;
            else if (arr[y][x] < value)
                ++y;
            else return;
        }
    }

    /**
     * Searching for the specified value in the specified array, moving from the top right corner to the bottom left using exponential search.
     * Result as same as if c++ lower_bound function was called.
     *
     * @param arr   the array to be searched
     * @param value the value to be searched for
     */
    public static void expLadder(int[][] arr, int value) {
        int x = arr[0].length - 1;
        int y = 0;
        while (x >= 0 && y < arr.length) {
            if (arr[y][x] > value)
                x = goLeft(arr, y, x, value);
            else if (arr[y][x] < value)
                ++y;
            else return;
        }
    }

    /**
     * Exponential search on the horizontal axis of specified array.
     * Result equals result of c++ (lower_bound() - 1) function was called.
     *
     * @param arr   the array to be searched
     * @param y     current vertical position
     * @param x     current horizontal position
     * @param value the value to be searched for
     */
    public static int goLeft(int[][] arr, int y, int x, int value) {
        int i = 1;
        while ((x - i) >= 0 && arr[y][x - i] > value)
            i *= 2;

        int right = x - i / 2 + 1;
        int left = Math.max(0, x - i);

        int index = Arrays.binarySearch(arr[y], left, right, value);
        return Math.max(index, 0);
    }
}
