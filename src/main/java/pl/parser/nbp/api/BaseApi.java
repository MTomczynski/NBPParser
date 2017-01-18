package pl.parser.nbp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by maciek on 17.01.17.
 */
public abstract class BaseApi {
    public static final String NBP_BASE_URL = "http://www.nbp.pl/kursy/xml/";

    protected Retrofit retrofit;

    public BaseApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(NBP_BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }
}
