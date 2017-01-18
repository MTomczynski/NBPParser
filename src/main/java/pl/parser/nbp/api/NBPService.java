package pl.parser.nbp.api;

import pl.parser.nbp.model.CurrencyTable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maciek on 17.01.17.
 */
public interface NBPService {
    @GET("{fileName}.xml")
    Call<CurrencyTable> getCurrencyTable(@Path("fileName") String fileName);

    @GET("dir{year}.txt")
    Call<String> getDirectories(@Path("year") String year);
}
