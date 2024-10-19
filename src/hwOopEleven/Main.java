package hwOopEleven;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String link = "https://javarush.com/en/";

		try {
			String htmlText = HtmlPageGetter.getHtmlPage(link, "UTF-8");

			System.out.println(HtmlPageGetter.findAllLinks(htmlText));

		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File pagesTextFile = new File("webPages.txt");
		HtmlPageGetter.requestHeader(pagesTextFile);

	}

}
