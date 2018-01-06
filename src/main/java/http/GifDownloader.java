package http;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class GifDownloader {
	private static String URL = "https://www.chineseconverter.com";
	private static String FOLDER = "/home/xuanlinhha/CCGif/";

	public static void download(Character cc, String tail) {
		DataInputStream di = null;
		FileOutputStream fo = null;
		byte[] b = new byte[1];
		try {
			// input
			URL url = new URL(URL + tail);
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
			di = new DataInputStream(urlConnection.getInputStream());
			// output
			fo = new FileOutputStream(FOLDER + cc + ".gif");
			// copy the actual file
			// (it would better to use a buffer bigger than this)
			while (-1 != di.read(b, 0, 1))
				fo.write(b, 0, 1);
			di.close();
			fo.close();
		} catch (Exception ex) {
			System.out.println("Error in GifDownloader.download function!");
			ex.printStackTrace();
		}
	}
}
