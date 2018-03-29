package backend.logger;

import java.io.*;
import java.util.logging.*;

public class BillLogger {
    // Logger.getLogger() follows Factory Pattern.
    private static final Logger logger = Logger.getLogger(BillLogger.class.getName());

    private static FileHandler fileHandler = null;

    public static void init() {
        // Disables default root logger's console handler.
        logger.setUseParentHandlers(false);

        try {
            fileHandler = new FileHandler("trackBill.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // If fileHandler format is not set to SimpleFormat, then the log is kept in XML format.
        // SimpleFormat makes the log to be kept in text format.
        fileHandler.setFormatter(new SimpleFormatter());

        //    Logger rootLogger = Logger.getLogger("");
        //    rootLogger.addHandler(fileHandler);
        logger.addHandler(fileHandler);
    }
}
