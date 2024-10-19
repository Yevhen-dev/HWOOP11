package hwOopEleven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlPageGetter {

	public static String getHtmlPage(String link, String code) throws IOException, URISyntaxException {
		URI uri = new URI(link);
		StringBuilder sb = new StringBuilder();
		URL url = uri.toURL();
		URLConnection connection = url.openConnection();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), code))) {
			for (;;) {
				String text = br.readLine();
				if (text == null) {
					break;
				}
				sb.append(text).append(System.lineSeparator());
			}
			return sb.toString();
		}
	}

	public static List findAllLinks(String text) {

		List links = new ArrayList();

		Pattern reg = Pattern.compile("(https?:\\/\\/[\\w\\-\\.!~?&=+\\*'(),\\/\\#\\:]+)((?!\\<\\/\\w\\>))*?");

		Matcher match = reg.matcher(text);

		while (match.find()) {

			links.add(text.substring(match.start(), match.end()));

		}

		return links;

	}

	public static void requestHeader(File file) {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line;

			while ((line = br.readLine()) != null) {

				URI uri;
				try {
					uri = new URI(line);
					URL url = uri.toURL();
					URLConnection connection = url.openConnection();
					if (connection.getHeaderField(0) != null) {
						System.out
								.println(line + " => server response = " + connection.getHeaderField(0).split(" ")[1]);
					} else {
						System.out.println(line + " server not found");
					}
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
