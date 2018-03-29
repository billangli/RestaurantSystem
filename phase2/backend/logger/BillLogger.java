package backend.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
    fileHandler.setFormatter(new SimpleFormatter());

    logger.addHandler(fileHandler);
  }
}
