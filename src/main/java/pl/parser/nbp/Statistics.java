package pl.parser.nbp;

import java.util.List;

/**
 * Created by maciek on 17.01.17.
 */
public class Statistics {

    private List<Double> data;

    public Statistics(List<Double> data) {
        this.data = data;
    }

    public double getMean() {
        double sum = 0.0;
        for(double n : data)
            sum += n;
        return sum/data.size();
    }

    private double getVariance() {
        double mean = getMean();
        double temp = 0;
        for(double n : data)
            temp += (n - mean) * (n - mean);
        return temp/data.size();
    }

    public double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
