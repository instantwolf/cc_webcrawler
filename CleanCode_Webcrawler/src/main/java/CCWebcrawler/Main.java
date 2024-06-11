package CCWebcrawler;

import CCWebcrawler.Structure.Website;
import HtmlParser.JsoupParserAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        String url = readAndValidateURL();
        int depth = readAndValidateDepth();

        JsoupParserAdapter parser = new JsoupParserAdapter();
        Crawler crawler = new Crawler(url, depth, parser);

        try{
            crawler.crawlPages();
        }catch(Exception e){
            e.printStackTrace();
        }
        Website crawledSite = crawler.getResults();


        String markDown =  MarkDownGenerator.generateMarkDownForWebsite(
                             crawledSite.getUrl(), depth,crawledSite);
        System.out.println(markDown);


    }

    private static String readAndValidateURL(){
       String url;

       do{
          url = readInputLine("Enter the desired URL like: http://www.example.com  ");
       }while(!validateUrl(url));

       if(!URLValidator.hasProtocol(url))
           url = URLValidator.addProtocol(url);

       return url;
    }

    private static boolean validateUrl(String url){
        if(URLValidator.isValidURL(url))
            return true;
        else
            System.err.println("Invalid URL. Please provide a valid URL: \n ");

        return false;

    }


    private static int readAndValidateDepth(){
        String input;
        do{
            input = readInputLine("Enter the depth of the websites to crawl:");
        }
        while(!validateDepth(input));

        return Integer.parseInt(input);
    }

    private static boolean validateDepth(String depth){
        if(isNumeric(depth) || Integer.parseInt(depth) >= 0)
            return true;
        else
            System.err.println("Invalid depth, please provide a positive  number");

        return false;
    }

    private static String readInputLine(String prompt) {
        System.out.println(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String var;
        try {
            var = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input variable: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return var;
    }



    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }





}




