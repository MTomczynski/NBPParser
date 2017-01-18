package pl.parser.nbp;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by maciek on 17.01.17.
 */
public class CatalogFilter implements Callable<List<String>> {
    private final List<String> directories;
    private final static DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyMMdd");
    private final DateTime endDate;
    private final DateTime startDate;
    private final String regex;


    public CatalogFilter(List<String> directories, String regex, DateTime startDate, DateTime endDate) {
        this.directories = directories;
        this.regex = regex;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private DateTime getDateFromDirName(String directory) {
        return DateTime.parse(directory.substring(5), FORMATTER);
    }

    @Override
    public List<String> call() throws Exception {
        List<String> filteredDirs = new LinkedList<>();
        for(String directory : directories) {
            if(directory.matches(regex)) {
                DateTime date = getDateFromDirName(directory);
                if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                    filteredDirs.add(directory);
                }
            }
        }
        return Collections.unmodifiableList(filteredDirs);
    }
}
