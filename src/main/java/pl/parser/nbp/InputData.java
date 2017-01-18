package pl.parser.nbp;

import org.joda.time.DateTime;

/**
 * Created by maciek on 17.01.17.
 */
public class InputData {

    private DateTime startDate;
    private DateTime endDate;

    public InputData(String[] args) {
        startDate = DateTime.parse(args[1]);
        endDate = DateTime.parse(args[2]);
        if(!endDate.isBefore(startDate)) {
            DateTime temp = endDate;
            endDate = startDate;
            startDate = temp;
        }
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }


    public String[] getYearsBetween() {
        String[] years = new String[startDate.getYear() - endDate.getYear() + 1];
        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(endDate.getYear() + i);
        }
        return years;
    }
}
