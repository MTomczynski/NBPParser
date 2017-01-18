package pl.parser.nbp.api;

import pl.parser.nbp.model.CurrencyTable;
import retrofit2.Call;

import java.io.IOException;

/**
 * Created by maciek on 17.01.17.
 */
public class NBPApi extends BaseApi {
    private NBPService service;

    public NBPApi() {
        createService();
    }

    private void createService() {
        service = retrofit.create(NBPService.class);
    }

    public Call<String> getDirectories (String year) {
        return service.getDirectories(year);
    }

    public Call<CurrencyTable> getCurrencyTable (String fileName) {
        return service.getCurrencyTable(fileName);
    }

    public String executeCatalog(Call<String> call) throws IOException {
        return call.execute().body();
    }

    public CurrencyTable executeFile(Call<CurrencyTable> call) throws IOException {
        return call.execute().body();
    }
}
