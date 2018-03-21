package backend.logger;

import java.io.*;
import java.util.logging.*;

public class RestaurantLogger {
  // Logger.getLogger() follows Factory Pattern.
  private static final Logger logger = Logger.getLogger(RestaurantLogger.class.getName());

  private static FileHandler fileHandler = null;

  public static void init() {
    // Disables default root logger's console handler.
    logger.setUseParentHandlers(false);

    try {
      fileHandler = new FileHandler("logFile.log", true);
    } catch (IOException e) {
      e.printStackTrace();
    }
    // If fileHandler format is not set to SimpleFormat, then the log is kept in XML format.
    // SimpleFormat makes the log to be kept in text format.
    fileHandler.setFormatter(new SimpleFormatter());

    //    Logger rootLogger = Logger.getLogger("");
    //    rootLogger.addHandler(fileHandler);
    logger.addHandler(fileHandler);
    // Note that Log levels INFO and higher will be automatically written to the console.

    //    PrintStream outPS = null;
    //    try {
    //      outPS = new PrintStream(new BufferedOutputStream(new FileOutputStream("logFile.log",
    // true)));
    //    } catch (FileNotFoundException e) {
    //      e.printStackTrace();
    //    }
    //    System.setOut(outPS); // redirect System.out
    //    System.setErr(outPS); // redirect System.err
  }

  // TODO: Delete before finalizing.
  //  public static void main(String[] args) {
  //    init();
  //    System.err.println("serr");
  //    backend.logger.log(Level.INFO, "message 1"); // INFO: Default level.
  //    System.out.println("sout");
  //
  //    // CONFIG Level is lower than INFO level, so it doesn't show on log.
  //    backend.logger.log(Level.CONFIG, "message 2");
  //    System.out.println("sout2");
  //    LoggerTest2.thing();
  //    System.err.println("serr2");
  //  }
}
