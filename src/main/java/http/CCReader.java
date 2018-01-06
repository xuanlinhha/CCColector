package http;

import java.io.File;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class CCReader {
	public static String readChars(String input) {
		String text = "";
		try {
			text = FileUtils.readFileToString(new File(input));
		} catch (IOException e) {
			System.out.println("Error in GifDownloader.readChars function!");
			e.printStackTrace();
		}
		Set<Character> cs = new HashSet<Character>();
		final CharacterIterator it = new StringCharacterIterator(text);
		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
				cs.add(c);
			}
		}
		StringBuilder postText = new StringBuilder();
		for (Character c : cs) {
			postText.append(c);
		}
		return postText.toString();
	}

}
