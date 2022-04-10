package logger.configs;

import logger.Entry;
import main.Main;

public enum EntryConfig {
    //input your entry object
    Entry(Main.get());

    EntryConfig(Entry entry){
        this.entry = entry;
    }
    public Entry getEntry(){return entry;}
    Entry entry;
}
