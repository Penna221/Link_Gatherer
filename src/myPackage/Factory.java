package myPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Factory {
    
    public void formHTML(ArrayList<Data> array){
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
        String newContent = "<div id=\"container\">\n";

        //Generate data from array
        for(Data d : array){
            newContent += "\t\t<div class=\"data\">\n";
            
            newContent += "\t\t\t<h1>"+d.header + "</h1>\n";
            newContent += "\t\t\t<a href=\""+ d.link +"\">Read</a>\n";
            
            newContent += "\t\t</div>\n";
        }


        newContent += "</div>\n";
        html = html.replace("<div id=\"data\"></div>", newContent);
        System.out.println(html);
        write(html,new File("res\\todaysTopics.html"));
    }
    private void write(String txt, File f){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f,Charset.forName("UTF-8")));
            writer.write(txt);

            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
