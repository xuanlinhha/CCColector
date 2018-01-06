package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GifPathExtractor {
	private static String URL = "https://www.chineseconverter.com/en/convert/chinese-stroke-order-tool";

	public static List<String> extractGifPaths(String text) {
		List<String> result = new ArrayList<String>();
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(URL);
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("text", text));
			urlParameters.add(new BasicNameValuePair("speed", "normal"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer html = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				html.append(line);
			}
			Document doc = Jsoup.parse(html.toString());
			Elements strokeElements = doc.select("img[class=stroke_order]");
			for (Element e : strokeElements) {
				result.add(StringEscapeUtils.unescapeHtml4(e.attr("data-original")));
			}
		} catch (IOException e) {
			System.out.println("Error in GifNameGetter.getGifName function!");
			e.printStackTrace();
		}
		return result;
	}
}
