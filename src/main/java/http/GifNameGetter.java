package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;

public class GifNameGetter {
	private static String URL = "http://hvdic.thivien.net/query-dict.json.php";

	public static String getGifName(Character c) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(URL);
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("Value", c.toString()));
			urlParameters.add(new BasicNameValuePair("Mode", "2"));
			urlParameters.add(new BasicNameValuePair("Lang", "2"));
			urlParameters.add(new BasicNameValuePair("Page", "0"));
			post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
				Gson gson = new Gson();
				JsonData jsonData = gson.fromJson(result.toString(), JsonData.class);
				Document doc = Jsoup.parse(jsonData.getHtml());
				Element ani = doc.select("img").first();
				Path path = Paths.get(ani.attr("data-original"));
				return path.getFileName().toString();
			}
		} catch (IOException e) {
			System.out.println("Error in GifNameGetter.getGifName function!");
			e.printStackTrace();
		}
		return "";
	}
}
