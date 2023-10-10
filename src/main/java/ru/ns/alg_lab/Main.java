package ru.ns.alg_lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Draw a chart with test data of 3 different search algorithms.
 * To test on different input data pass into program arguments '1' or '2':
 * 1) A[i][j] = (N / M * i + j) * 2, target = 2 * n + 1;
 * 2) A[i][j] = (N / M * i * j) * 2, target = 16 * n + 1;
 **/
public class Main extends Application {
    /**
     * Launch start() method JavaFX application
     **/
    public static void main(String[] args) {
        if (args.length > 0) {
            if (Objects.equals(args[0], "1"))
                Gen2DArray.genType = 1;
            else
                Gen2DArray.genType = 2;
        }
        launch(args);
    }

    /**
     * Starts the JavaFX application in order to draw graphics which reflects algorithms tests
     **/
    @Override
    public void start(Stage stage) {
        LineChart<Number, Number> lineChart = createLineChart();
        testAlgosAndAddResultToChart(lineChart);
        drawChart(lineChart, stage);
    }

    /**
     * Creates line chart
     **/
    private static LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("y");
        xAxis.setLabel("x");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Algorithms test");
        return lineChart;
    }

    /**
     * Draws line chart
     */
    private static void drawChart(LineChart<Number, Number> lineChart, Stage stage) {
        stage.setScene(new Scene(lineChart, 800, 600));
        stage.show();
    }

    /**
     * Makes a line on the chart, based on specified data, with specified name.
     *
     * @param name name for new line on chart
     * @param data (input data amount - execution time) data
     **/
    private static XYChart.Series<Number, Number> getOneLineAtChart(String name, List<Pair<Number, Number>> data) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (Pair<Number, Number> dot : data) {
            series.getData().add(new XYChart.Data<>(dot.getKey(), dot.getValue()));
        }
        return series;
    }

    /**
     * Test each search algorithm and results into chart.
     **/
    private static void testAlgosAndAddResultToChart(LineChart<Number, Number> lineChart) {
        int times = 5000;
        int n = (int) Math.pow(2, 13);
        int m = (int) Math.pow(2, 8);
        Gen2DArray.genTable(m, n);
        var list = Algorithms.expLadder(Gen2DArray.table, Gen2DArray.target);
        lineChart.getData().add(getOneLineAtChart("x", list));

//        runTestsForAlgo(Algorithms::basicBinary, 1000);  // Warming up algorithms
//        List<Pair<Number, Number>> binaryChart = runTestsForAlgo(Algorithms::basicBinary, times);
//        runTestsForAlgo(Algorithms::ladder, 1000);
//        List<Pair<Number, Number>> ladderChart = runTestsForAlgo(Algorithms::ladder, times);
//        runTestsForAlgo(Algorithms::expLadder, 1000);
//        List<Pair<Number, Number>> expLadderChart = runTestsForAlgo(Algorithms::expLadder, times);
//
//        lineChart.getData().add(getOneLineAtChart("Binary search", binaryChart));
//        lineChart.getData().add(getOneLineAtChart("Ladder search", ladderChart));
//        lineChart.getData().add(getOneLineAtChart("Exponential ladder search", expLadderChart));
    }

    /**
     * Runs test for given search algorithm on different amount of input data.
     * @param algo the search algorithm to be tested
     * @param times how many times, to calculate average time
     * @return list of (input data amount -> algorithm's execution time)
     */
    private static List<Pair<Number, Number>> runTestsForAlgo(SearchAlgo algo, int times) {
        int maxDegree = 13;
        int n = (int) Math.pow(2, maxDegree);

        List<Pair<Number, Number>> chart = new ArrayList<>();
        for (int i = 1; i <= maxDegree; ++i) {
            long overallTime = 0;
            int m = (int) Math.pow(2, i);
            Gen2DArray.genTable(m, n);
            for (int test = 0; test < times; test++) {
                long time = getNanoExecutionTime(() -> algo.search(Gen2DArray.table, Gen2DArray.target));
                overallTime += time;
            }
            chart.add(new Pair<>(m, overallTime / times));
        }
        return chart;
    }

    /**
     * Calculates function's execution time in nanoseconds.
     * @param func the function to be tested
     * @return specified function's execution time
     */
    private static long getNanoExecutionTime(Runnable func) {
        long start = System.nanoTime();
        func.run();
        return System.nanoTime() - start;
    }
}