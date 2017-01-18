package pl.parser.nbp;

import pl.parser.nbp.api.NBPApi;
import pl.parser.nbp.model.CurrencyTable;

import java.io.IOException;
import java.util.*;

/**
 * Created by maciek on 17.01.17.
 */
public class CatalogCaller {
    private final List<String> rawDirectories = new LinkedList<>();
    private final static String SPLITTER = "\r\n";
    private final NBPApi nbpApi;

    public CatalogCaller(String[] years, NBPApi nbpApi) throws IOException {
        this.nbpApi = nbpApi;
        for (String year : years) {
            rawDirectories.add(nbpApi.executeCatalog(nbpApi.getDirectories(year)));
        }
    }

    public List<String> getDirectories(){
        List<String> directories = new LinkedList<>();
        try {
            rawDirectories.forEach(s -> directories.addAll(Arrays.asList(s.split(SPLITTER))));
        } catch (NullPointerException e) {
            System.out.println("Wrong data!");
        }
        return Collections.unmodifiableList(directories);
    }

    public List<CurrencyTable> getFiles(List<String> fileNames) {
        List<CurrencyTable> files = new LinkedList<>();
        fileNames.forEach(s -> {
            try {
                files.add(nbpApi.executeFile(nbpApi.getCurrencyTable(s)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Collections.unmodifiableList(files);
    }
}
