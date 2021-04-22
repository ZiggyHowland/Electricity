package nu.hovland.electricity.components;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Date;

public class ReadingsCsvFromGoogleDrive extends CsvBean {
    @CsvBindByPosition(position = 0)
    private String description;

    @CsvBindByPosition(position = 1)
    private Date date;

    @CsvBindByPosition(position = 2)
    private long meterValueMainFrom;

    @CsvBindByPosition(position = 3)
    private long meterValueMainTo;

    @CsvBindByPosition(position = 4)
    private long meterValueMainDiff;

    @CsvBindByPosition(position = 5)
    private long meterValueAppartmentFrom;

    @CsvBindByPosition(position = 6)
    private long meterValueAppartmentTo;
}
