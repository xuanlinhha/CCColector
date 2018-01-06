package http;

import java.util.Set;

public class Processor {

	public static void process(String input) {
		Set<Character> cs = InputReader.readChars(input);
		for (Character c : cs) {
			String gifName = GifNameGetter.getGifName(c);
			GifDownloader.download(gifName);
		}
	}

}
