package nu.hovland.electricity.components;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class MeterReadingsParser {

    public static void importFromCsvFile(String resourceFile) {
        try {
            Reader reader = getReaderFromResourceFile(resourceFile);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

            allData.stream()
                .forEach(d -> System.out.println(d.toString()));

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


    private static Reader getReaderFromResourceFile(String resourceFile) throws URISyntaxException, IOException {
        URL resource = ClassLoader.getSystemResource(resourceFile);
        if ( resource == null ) {
            return null;
        }
        else {
            URI fileURI = resource.toURI();
            return Files.newBufferedReader(Paths.get(fileURI));
        }
    }

}
