import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;


class PageTagger {
	MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
	
////////////////This method tags each word of the passed string for part of speech
private String tagText(String s)throws IOException{
//	MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
	String s1=null;
	System.out.println("here starts the process");
	s1 = tagger.tagString(s);
	return s1;
}	
	/////////Deriving headings and text from a url
private String getText(URL url){
	String parsedHtml=null;
	try{
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		String line = null;
		StringBuilder tmp = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		while ((line = in.readLine()) != null) {
		  tmp.append(line);
		}
		 
		Document doc = Jsoup.parse(tmp.toString());
		parsedHtml = doc.title() + doc.body().text();
		
	}
	catch(IOException e){
		System.out.println("Page not reached for: "+url );
	}
	finally{
		return parsedHtml;
	}
	
}

///////////////////////////////////////////////////////////////
public static void main(String args[]) throws IOException{
	String parsedURL = null;
	String taggedText = null;
	URL inputURL;
	
	PageTagger ob1 = new PageTagger();


	String input = "This is test text.";
	PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
	System.setOut(out);
	

	
	try{
		System.gc();
		inputURL = new URL("http://gumgum.com/");
		parsedURL = ob1.getText(inputURL);
		taggedText = ob1.tagText(parsedURL);
		System.out.println("This is the tagged content for a) http://gumgum.com/");
		System.out.println(taggedText);
	}
	catch(NullPointerException e){
		System.out.println("Null pointer exception in main method as nothing could be read from the webpage");
	}
	
	
	try{
		System.out.println("\n");
		System.gc();
		inputURL = new URL("http://www.popcrunch.com/jimmy-kimmel-engaged/");
		parsedURL = ob1.getText(inputURL);
		taggedText = ob1.tagText(parsedURL);
		System.out.println("This is the tagged content for b) http://www.popcrunch.com/jimmy-kimmel-engaged/");
		System.out.println(taggedText);
	}
	catch(NullPointerException e){
		System.out.println("Null pointer exception in main method as nothing could be read from the webpage");
	}
	
	try{
		System.out.println("\n");
		System.gc();
		inputURL = new URL("http://www.windingroad.com/articles/reviews/quick-drive-2012-bmw-z4-sdrive28i/");
		parsedURL = ob1.getText(inputURL);
		taggedText = ob1.tagText(parsedURL);
		System.out.println("This is the tagged content for d) http://www.windingroad.com/articles/reviews/quick-drive-2012-bmw-z4-sdrive28i/");
		System.out.println(taggedText);
	}
	catch(NullPointerException e){
	System.out.println("Null pointer exception in main method as nothing could be read from the webpage");
	}
	
	
	try{
		System.out.println("\n");
		System.out.println("This is the tagged content for c) http://gumgum-public.s3.amazonaws.com/numbers.html");
		System.gc();
		inputURL = new URL("http://gumgum-public.s3.amazonaws.com/numbers.html");
		parsedURL = ob1.getText(inputURL);	
		taggedText = ob1.tagText(parsedURL);
		System.gc();
		System.out.println(taggedText);
	}
		
	catch(NullPointerException e){
		System.out.println("Null pointer exception in main method as nothing could be read from the webpage");
	}
}

}

