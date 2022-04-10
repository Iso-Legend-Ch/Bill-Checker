package logger.configs;

import java.util.logging.*;

public enum LoggerLevelConfig {
    //the min level of log to show in console
    Console_Level(Level.ALL),

    //the min level of log to show in log.xml
    File_Level(Level.ALL);
    ;

    private Level level;
    LoggerLevelConfig(Level level){
       this.level = level;
    }
    public Level getLevel(){return level;}
}
