package pl.parser.nbp.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by maciek on 17.01.17.
 */
@Root(name = "pozycja")
public class CurrencyData {

    @Element(name = "nazwa_waluty", required = false)
    private String name;

    @Element(name = "przelicznik")
    private int converter;

    @Element(name = "kod_waluty")
    private String code;

    @Element(name = "kurs_kupna")
    private String buyingRate;

    @Element(name = "kurs_sprzedazy")
    private String sellingRate;

    @Element(name = "nazwa_kraju", required = false)
    private String countryName;

    @Element(name = "symbol_waluty", required = false)
    private String currencySymbol;


    public String getName() {
        return name;
    }

    public int getConverter() {
        return converter;
    }

    public String getCode() {
        return code;
    }

    public String getBuyingRate() {
        return buyingRate;
    }

    public String getSellingRate() {
        return sellingRate;
    }
}
