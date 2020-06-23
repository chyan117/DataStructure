import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hug.
 */
public class Experiments {
    static List<Double> yValues = new ArrayList<>();
    static List<Double> y2Values = new ArrayList<>();
    static List<Integer> xValues = new ArrayList<>();
    static BST<Integer>    BT  =   new BST<>();
    static int size;
    static double thisY, thisY2;
    public static void experiment1() {
        size = 0;
        int x_ran;
        /** x is number of item*/
        for (int x = 0; x < 5000; x += 1) {
            size = size+1;
            thisY = ExperimentHelper.optimalAverageDepth(size);
            xValues.add(x);
            x_ran = RandomGenerator.getRandomInt(5000);
            yValues.add(thisY);
            BT.add(x_ran);
            thisY2 = BT.return_TD();
            y2Values.add(thisY2);
        }
        /*
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Optimal situation", xValues, yValues);
        chart.addSeries("random situation", xValues, y2Values);

        new SwingWrapper(chart).displayChart();

         */
    }

    public static void experiment2() {
        int x_ran;
        boolean r;
        /** x is number of item*/
        for (int x = 5000; x < 10000; x += 1) {
            r = RandomGenerator.getRandomBoolean();
            xValues.add(x);
            x_ran = RandomGenerator.getRandomInt(5000);
            if(r==true) {
                size= size+1;
                thisY = ExperimentHelper.random_insetion(size);
                yValues.add(thisY);
                BT.add(x_ran);
                thisY2 = BT.return_TD();
                y2Values.add(thisY2);
            }
            else if(r==false){
                size = size-1;
                thisY = ExperimentHelper.random_deletion(size);
                yValues.add(thisY);
                BT.deleteTakingSuccessor(x_ran);
                thisY2 = BT.return_TD();
                y2Values.add(thisY2);
            }
        }

        /*
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Optimal situation", xValues, y2Values);
        chart.addSeries("random situation", xValues, yValues);

        new SwingWrapper(chart).displayChart();

         */
    }

    public static void experiment3() {
        int x_ran;
        boolean r;
        /** x is number of item*/
        for (int x = 10000; x < 20000; x += 1) {
            r = RandomGenerator.getRandomBoolean();
            x_ran = RandomGenerator.getRandomInt(5000);
            xValues.add(x);
            if(x<2000){
                size = size +1;
                thisY = ExperimentHelper.optimalAverageDepth(size);
                yValues.add(thisY);
                BT.add(x_ran);
                thisY2 = BT.return_TD();
                y2Values.add(thisY2);
            }
            else if(r==true) {
                size = size +1;
                thisY = ExperimentHelper.random_insetion(size);
                yValues.add(thisY);
                BT.add(x_ran);
                thisY2 = BT.return_TD();
                y2Values.add(thisY2);
            }
            else if(r==false){
                size= size -1;
                boolean randomd = RandomGenerator.getRandomBoolean();
                thisY = ExperimentHelper.random_deletion(size);
                yValues.add(thisY);
                if(randomd) {
                    BT.deleteTakingSuccessor(x_ran);
                }
                else{
                    BT.deleteTakingRandom(x_ran);
                }
                thisY2 = BT.return_TD();
                y2Values.add(thisY2);
            }
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Optimal situation", xValues, y2Values);
        chart.addSeries("random situation", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment1();
        experiment2();
        experiment3();
    }
}
