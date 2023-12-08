package myPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        sites = new URL[2];
        try {
            sites[0] = new URL("https://www.iltalehti.fi");
            sites[1] = new URL("https://www.iltasanomat.fi");
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
        ArrayList<Data> allData = new ArrayList<Data>();
        System.out.println(dateString);
        for(URL url : sites){
            if(url.toString().contains("iltalehti")){
                ArrayList<Data> data = findDataFromIL(url);
                allData.addAll(data);
            }else if(url.toString().contains("iltasanomat")){
                ArrayList<Data> data = findDataFromIS(url);
                allData.addAll(data);
            }
        }
        f.formHTML(allData);
    }
    public ArrayList<Data> findDataFromIL(URL url){
        ArrayList<Data> array = new ArrayList<Data>();
        try {
            Document doc = Jsoup.parse(url.openStream(), "UTF-8", url.toString());
            Element element = doc.getElementById("news-container");
            Elements e = element.select("div main div div.main-column-content.show-true div.front div");
            Elements links = e.select("a");
            
            for (Element child : links) {
                String link = child.attributes().get("href");
                String title = child.select("div.front-title").text();
                System.out.println("title:  " + title);
                System.out.println("link:  " + link);
                Data d = new Data(new URL("https:/www.iltalehti.fi"+link),title);
                if(title.length()!=0){
                    array.add(d);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return array;
    }
    public ArrayList<Data> findDataFromIS(URL url){
        ArrayList<Data> array = new ArrayList<Data>();
        try {
            Document doc = Jsoup.parse(url.openStream(), "UTF-8", url.toString());
            
            Elements articles = doc.select("article");
            
            for (Element child : articles) {
                String link = child.select("a").attr("href");
                String title = child.select("a section div h2 span").text();
                if(title.length()==0){
                    title = child.select("a section div div h2 span").text();
                }
                System.out.println("title:  " + title);
                System.out.println("link:  " + link);
                Data d = new Data(new URL("https:/www.iltasanomat.fi"+link),title);
                if(title.length()!=0){
                    array.add(d);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return array;
    }
}