package main;

import frm.Connecting;
import frm.Frm;
import frm.Getting;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements logger.Entry {
    private static Main main = null;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    String time;
    private List<String> numbers = new ArrayList<>();
    private List<String> special = new ArrayList<>();

    private Main(){}

    public void start() {
        Main.get().run();
    }

    public static Main get(){
        if (main == null){
            main = new Main();
        }
        return main;
    }

    public void run(){
        try {
            //connect
            Connecting.get().show();
            logger.log(Level.INFO,"[Bill checker]Connecting...");
            Document document = Jsoup.connect("https://invoice.etax.nat.gov.tw/").get();
            Connecting.get().hide();
            logger.log(Level.INFO,"[Bill checker]Connected.");

            //get
            Getting.get().show();
            logger.log(Level.INFO,"[Bill checker]Getting data...");

            //get time
            Elements elements = document.select("a.etw-on");
            Element element = elements.get(0);
            time = element.text().substring(0,element.text().length()-1);

            //get number
            Elements elements1 = document.select("div.container-fluid");
            Elements elements2 = elements1.select("span.font-weight-bold");
            {
                for (Element element1 : elements2){
                    if(element1.text().length() == 3 || element1.text().length() == 8) {
                        if(element1.text().length() == 8){
                            special.add(element1.text());
                        }
                        if(numbers.size()<6){
                            String text = element1.text();
                            numbers.add(text.substring(text.length()-3));
                        }
                    }
                }
            }
            Getting.get().hide();
            logger.log(Level.INFO,"[Bill checker]Got data.");

            logger.log(Level.INFO,"Starting frm...");
            Frm frm = new Frm(time,numbers);

        } catch (Exception e) {
            logger.log(Level.WARNING,"[Bill checker]We have a exception while bill checker running.",e);
        }
    }

    public List<String> getSpecial() {
        return special;
    }
}
