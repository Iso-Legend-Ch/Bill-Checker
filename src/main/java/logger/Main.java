package logger;

import logger.configs.EntryConfig;
import logger.configs.LoggerLevelConfig;

import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static Main main = null;

    private Logger logger;

    public static void main(String[] args) {
        try {
            Main.get().start();
        }catch (Exception e){
            Main.get().logger.log(Level.WARNING,"[Logger]Oh no,we just have a exception while program running!",e);
        }
        Main.get().logger.log(Level.INFO,"[Logger]Program ending...");
    }

    public static Main get(){
        if(main == null){
            main = new Main();
        }
        return main;
    }

    public void start(){
        try {
            setup();
        } catch (Exception e) {
            logger.log(Level.WARNING,"[Logger]Oh no,we just have a exception while setuping!",e);
        }
        logger.log(Level.INFO,"[Logger]Start running entry.start()...");
        EntryConfig.Entry.getEntry().start();
        logger.log(Level.INFO,"[Logger]Entry.start() ended.");
    }

    public void setup() throws IOException {
        LogManager.getLogManager().reset();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(LoggerLevelConfig.Console_Level.getLevel());
        logger.addHandler(consoleHandler);

        FileHandler fileHandler = new FileHandler("Log.xml");
        fileHandler.setLevel(LoggerLevelConfig.File_Level.getLevel());
        fileHandler.setFormatter(new XMLFormatter());
        logger.addHandler(fileHandler);

        logger.log(Level.INFO,"[Logger]Setup complete.");
    }

}
