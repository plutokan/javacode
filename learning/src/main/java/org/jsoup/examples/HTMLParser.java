package org.jsoup.examples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * Java Program to parse/read HTML documents from File using Jsoup library.
 * Jsoup is an open source library which
 * allows Java developer to parse HTML
 * files and extract elements, manipulate data, change style using DOM, CSS and
 * JQuery like method.
 *
 * @author Javin Paul
 * @author <a href="mailto:pluto.kan@gmail.com">Pluto Kan</a>
 */
public class HTMLParser {

    /**
     * @param args
     */
    public static void main(String[] args) {
        parseString();
 
        parseURI();
 
        parseFile();

    }

    /**
     * Parse HTML String using JSoup library
     */
    public static void parseString() {
        String HTMLSTring = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>JSoup Example</title>"
                + "</head>"
                + "<body>"
                + "<table><tr><td><h1>HelloWorld</h1></tr>"
                + "</table>"
                + "</body>"
                + "</html>";
 
        Document html = Jsoup.parse(HTMLSTring);
        String title = html.title();
        String h1 = html.body().getElementsByTag("h1").text();
 
        System.out.println("Input HTML String to JSoup :" + HTMLSTring);
        System.out.println("After parsing, Title : " + title);
        System.out.println("Afte parsing, Heading : " + h1);
    }

    /**
     * JSoup Example 2 - Reading HTML page from URL
     */
    public static void parseURI() {
        String title;
        Document doc;
        try {
            // It’s recommended to specify a “userAgent” in Jsoup, to avoid HTTP 403 error messages.
            doc = Jsoup.connect("http://google.com/").userAgent("Mozilla").get();
            title = doc.title();
            System.out.println("Jsoup Can read HTML page from URL, title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                // get the value from href attribute
                System.out.println("\nlink : " + link.attr("href"));
                System.out.println("text : " + link.text());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JSoup Example 3 - Parsing an HTML file in Java
     */
    public static void parseFile() {
        String title;
        //Document htmlFile = Jsoup.parse("login.html", "ISO-8859-1"); // wrong
        Document htmlFile = null;
        try {
            htmlFile = Jsoup.parse(new File("login.html"), "ISO-8859-1");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // right
        title = htmlFile.title();
        Element div = htmlFile.getElementById("login");
        String cssClass = div.className(); // getting class form HTML element
 
        System.out.println("Jsoup can also parse HTML file directly");
        System.out.println("title : " + title);
        System.out.println("class of div tag : " + cssClass);
    }

    public static void getMetaElements() {
        StringBuffer html = new StringBuffer();
        
        html.append("<!DOCTYPE html>");
        html.append("<html lang=\"en\">");
        html.append("<head>");
        html.append("<meta charset=\"UTF-8\" />");
        html.append("");
        html.append("<meta name=\"description\" content=\"The latest entertainment news\" />");
        html.append("<meta name=\"keywords\" content=\"hollywood gossip, hollywood news\" />");
        html.append("</head>");
        html.append("<body>");
        html.append("<div id='color'>This is red</div> />");
        html.append("</body>");
        html.append("</html>");
            
        Document doc = Jsoup.parse(html.toString());

        //get meta description content
        String description = doc.select("meta[name=description]").get(0).attr("content");
        System.out.println("Meta description : " + description);
            
        //get meta keyword content
        String keywords = doc.select("meta[name=keywords]").first().attr("content");
        System.out.println("Meta keyword : " + keywords);
        
        String color1 = doc.getElementById("color").text();
        String color2 = doc.select("div#color").get(0).text();
            
        System.out.println(color1);
        System.out.println(color2);
    }

    public void getFormParams(String html){
        
        Document doc = Jsoup.parse(html);
     
        //HTML form id
        Element loginform = doc.getElementById("your_form_id");

        Elements inputElements = loginform.getElementsByTag("input");

        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
        }
     
    }

    @Test
    public void grabAllHyperLinks() {
        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect("http://google.com").get();

            // get page title
            String title = doc.title();
            System.out.println("title : " + title);

            // get all links
            Elements links = doc.select("a[href]");
            for (Element link : links) {

                // get the value from href attribute
                System.out.println("\nlink : " + link.attr("href"));
                System.out.println("text : " + link.text());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void grabAllImages() {
        Document doc;
        try {

            //get all images
            doc = Jsoup.connect("http://yahoo.com").get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {

                System.out.println("\nsrc : " + image.attr("src"));
                System.out.println("height : " + image.attr("height"));
                System.out.println("width : " + image.attr("width"));
                System.out.println("alt : " + image.attr("alt"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getImage() {
        StringBuffer html = new StringBuffer();

        html.append("<html lang=\"en\">");
        html.append("<head>");
        html.append("<link rel=\"icon\" href=\"http://example.com/image.ico\" />");     
        //html.append("<meta content=\"/images/google_favicon_128.png\" itemprop=\"image\">");
        html.append("</head>");
        html.append("<body>");
        html.append("something");
        html.append("</body>");
        html.append("</html>");

        Document doc = Jsoup.parse(html.toString());

        String fav = "";
                
        Element element = doc.head().select("link[href~=.*\\.(ico|png)]").first();
        if(element==null){
                
            element = doc.head().select("meta[itemprop=image]").first();
            if(element!=null){
                fav = element.attr("content");
            }
        }else{
            fav = element.attr("href");
        }
        System.out.println(fav);
    }

}
