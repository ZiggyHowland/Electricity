package nu.hovland.electricity;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LoggerTests {


    // Source: https://www.baeldung.com/log4j2-appenders-layouts-filters#async-file-appender-with-jsonlayout-and-burstfilter


    @Test
    public void givenLoggerWithDefaultConfig_whenLogToConsole_thanOK()
            throws Exception {
        Logger logger = LogManager.getLogger(getClass());
        Exception e = new RuntimeException("This is only a test!");

        logger.info("Simple log at INFO level");
        logger.error("Simple log at ERROR level", e);
        logger.warn("Simple log at WARN level");
    }

    @Test
    public void givenLoggerWithConsoleConfig_whenLogToConsoleInColors_thanOK()
            throws Exception {
        Logger logger = LogManager.getLogger("CONSOLE_PATTERN_APPENDER_MARKER");
        logger.trace("This is a colored message at TRACE level.");
        logger.warn("Simple log at WARN level");
        logger.fatal("Oh, this is serious shit...");

    }


    @Test
    public void givenLoggerWithAsyncConfig_whenLogToJsonFile_thanOK()
            throws Exception {
        Logger logger = LogManager.getLogger("ASYNC_JSON_FILE_APPENDER");

        final int count = 88;
        for (int i = 0; i < count; i++) {
            logger.warn("This is async JSON message #{} at WARN level.", count);
            logger.info("This is async JSON message #{} at INFO level.", count);
            logger.debug("This is async JSON message #{} at DEBUG level.", count);
        }

        long logEventsCount
                = Files.lines(Paths.get("logs/logfile.json")).count();

        assertTrue(logEventsCount > 0 && logEventsCount <= count);
    }



    @Test
    public void givenLoggerWithRollingFileConfig_whenLogToXMLFile_thanOK()
            throws Exception {
        Logger logger = LogManager.getLogger("XML_ROLLING_FILE_APPENDER");
        final int count = 200;
        for (int i = 0; i < count; i++) {
            logger.info(
                    "This is rolling file XML message #{} at INFO level.", i);
        }
    }

}
