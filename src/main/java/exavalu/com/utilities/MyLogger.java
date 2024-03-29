package exavalu.com.utilities;


import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;




public class MyLogger {
	
	private static Logger logger = Logger.getLogger("MyLog");
	
	public static void init() {
		try {
			FileHandler fh = new FileHandler("src\\applog.log");
			
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Logger Initialized");
		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception ::", e);
		}
	}
}