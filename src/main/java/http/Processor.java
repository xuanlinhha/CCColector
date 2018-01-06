package http;

import java.util.List;

public class Processor {

	public static void process(String input) {
		String postText = CCReader.readChars(input);
		List<String> tails = GifPathExtractor.extractGifPaths(postText);
		assert (postText.length() == tails.size());
		for (int i = 0; i < postText.length(); i++) {
			Character cc = postText.charAt(i);
			GifDownloader.download(cc, tails.get(i));
		}
	}
}
