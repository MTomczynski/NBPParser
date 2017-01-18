package pl.parser.nbp;

import pl.parser.nbp.api.NBPApi;
import pl.parser.nbp.model.CurrencyData;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by maciek on 17.01.17.
 */
public class MainClass {


    private final static String REGEX = "c\\d{3}z\\d{6}";
    private final static int ROUND_TO = 4;
    private final static NumberFormat nf = NumberFormat.getInstance();
    private static List<Double> sellingData = new LinkedList<>();
    private static List<Double> buyingData = new LinkedList<>();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        int N_CPUS = Runtime.getRuntime().availableProcessors();
        int Nthreads = (int) (N_CPUS * 0.5);
        ExecutorService executorService = Executors.newFixedThreadPool(Nthreads);

        String currencyCode = args[0];
        InputData inputData = new InputData(args);
        NBPApi nbpApi = new NBPApi();

        CatalogCaller catalogCaller = new CatalogCaller(inputData.getYearsBetween(), nbpApi);
        Future<List<String>> catalogFilter = executorService.submit(new CatalogFilter(catalogCaller.getDirectories(),
                REGEX, inputData.getEndDate(), inputData.getStartDate()));
        Future<List<CurrencyData>> currencyFilter = executorService.submit(
                new CurrencyFilter(catalogCaller.getFiles(catalogFilter.get()), currencyCode));

        currencyFilter.get().forEach(c -> {
            try {
                buyingData.add(nf.parse(c.getBuyingRate()).doubleValue());
                sellingData.add(nf.parse(c.getSellingRate()).doubleValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Statistics sellingStats = new Statistics(sellingData);
        Statistics buyingStats = new Statistics(buyingData);

        System.out.println(Statistics.round(buyingStats.getMean(), ROUND_TO));
        System.out.println(Statistics.round(sellingStats.getStandardDeviation(), ROUND_TO));
        executorService.shutdownNow();
    }
}
