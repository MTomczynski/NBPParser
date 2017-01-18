package pl.parser.nbp;

import pl.parser.nbp.model.CurrencyData;
import pl.parser.nbp.model.CurrencyTable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by maciek on 17.01.17.
 */
public class CurrencyFilter implements Callable<List<CurrencyData>> {

    private final List<CurrencyTable> currencyTables;
    private final String currencyCode;

    public CurrencyFilter(List<CurrencyTable> currencyTables, String currencyCode) {
        this.currencyTables = currencyTables;
        this.currencyCode = currencyCode;
    }

    @Override
    public List<CurrencyData> call() throws Exception {
        List<CurrencyData> data = new LinkedList<>();
        currencyTables.forEach(c -> c.getPositions().forEach(d -> {
            if(d.getCode().matches(currencyCode))
                data.add(d);
        }));
        return Collections.unmodifiableList(data);
    }
}
