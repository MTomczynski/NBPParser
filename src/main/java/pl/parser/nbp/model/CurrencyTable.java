package pl.parser.nbp.model;

import org.simpleframework.xml.*;

import java.util.List;

/**
 * Created by maciek on 17.01.17.
 */
@Root(name = "tabela_kursow")
public class CurrencyTable {



    @Attribute(name = "typ")
    private String type;

    @Attribute(required=false, name = "uid")
    private String uid;

    @Element(name = "numer_tabeli")
    private String tabelName;

    @Element(name = "data_notowania")
    private String dateOfListing;

    @Element(name = "data_publikacji")
    private String dateOfPublication;

    @ElementList(name = "pozycja", inline = true)
    private List<CurrencyData> positions;

    public String getType() {
        return type;
    }

    public String getTabelName() {
        return tabelName;
    }

    public String getDateOfListing() {
        return dateOfListing;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public List<CurrencyData> getPositions() {
        return positions;
    }
}
