package myPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Factory {
    
    public void formHTML(Data[] array){
        String html = "";
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {
            BufferedReader reader = new BufferedReader(new FileReader("res\\base.html"));
            String line = "";
            while((line = reader.readLine())!=null){
                html += "\n"+line;
                //System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        html = html.replace("<div id=\"data\"></div", "<div>Test</div>");
        System.out.println(html);
        write(html,new File("res\\todaysTopics.html"));
    }
    private void write(String txt, File f){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(txt);

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
