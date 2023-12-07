package myPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

/**
 * Gatherer
 */
public class Gatherer {
    public URL[] sites;
    public String[] urls;
    private String dateString = "Date: ";
    private Factory f;
    public Gatherer() {}
    public static void main(String[] args) {
        Gatherer g = new Gatherer();
        g.init();
        g.start();
    }
    public void init(){
        sites = new URL[1];
        try {
            sites[0] = new URL("https://www.iltalehti.fi");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        f = new Factory();
    }
    public void start(){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) +1;
        int year = c.get(Calendar.YEAR);
        dateString += day + "." + month + "." + year;
        
        System.out.println(dateString);
        for(URL url : sites){
            System.out.println("Finding links from " +url);
            //Data[] data = findData(url);
        }
        f.formHTML(null);
    }
    public Data[] findData(URL url){
        Data[] array = new Data[1];
        Data d = new Data(url,"Iltalehti");
        array[0] = d;
        try {
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return array;
    }
    
}