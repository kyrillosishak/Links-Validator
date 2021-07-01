package HTMLLinks;

import java.io.IOException;
import static java.lang.System.currentTimeMillis;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author ahmed
 */
public class Validation extends Extraction{

 
        static  int count = 0;
        static  int count_invalid = 0;
        static  int Threads = 0 ;
        static long endTime = 0;
        static ExecutorService es;    
        
    public static void validateURL(String link, int currentdepth, int totaldepth , int threads , String text) throws IOException, InterruptedException {
    
         es = Executors.newFixedThreadPool(threads);
         Threads t1 = null;
         Threads = threads;
        if (validateSingleURL(link)) {
            System.out.println("Valid Link " + link);
            System.out.println("Text: "+ text);
            if (currentdepth == totaldepth) {
                endTime =  currentTimeMillis();
                return;
            }
            Document doc = Jsoup.connect(link).get();
            Elements e = doc.select("a[href]"); 
            System.err.println("Count of Links:" + e.size());
            count = e.size();
            
            URL u = new URL(link);
            for (int i = 0; i < e.size(); i++) {
                String x = e.get(i).attr("href");
                String baseLINK = u.getProtocol() + "://" + u.getHost();
                if (!x.startsWith("http")) {
                    x = baseLINK + x;
                }
                t1 = new Threads(x, currentdepth + 1, totaldepth ,threads, e.get(i).text());
                es.execute(t1);
            }   
        } else {
            URL u = new URL(link);
            URLConnection x = u.openConnection();
            System.out.println(x.getContentType());
//            if(x.getContentType() ==null){
                System.err.println("Invalid Link " + link);
                System.out.println("Text: "+text);
                count_invalid++;
//            }else{
//                System.out.println("valid Link " + link);
//                System.out.println("Text: "+text);
//            }
        }
    }
    public static void validateURL(String link, int currentdepth, int totaldepth, String text ) throws IOException, InterruptedException {
         validateURL( link,  currentdepth,  totaldepth,100 , text);
    }
    public static void validateURL(String link, int currentdepth, int totaldepth ) throws IOException, InterruptedException {
         validateURL( link,  currentdepth,  totaldepth,100 , null);
    }
    public static void validateURL(String link, int currentdepth, int totaldepth,int threads) throws IOException, InterruptedException {
         validateURL( link,  currentdepth,  totaldepth,threads , null);
    }
    public static boolean validateSingleURL(String link) throws IOException {
        boolean valid = false;
        try {
            Document doc = Jsoup.connect(link).get();
            valid = true;
        } catch (HttpStatusException ex) {  // when returns 404 page not found
            valid = false;
        } catch (IOException ex) { // when timeout to connect to server not found
            valid = false;
        }
        return valid;
    }
    
    
}


    

