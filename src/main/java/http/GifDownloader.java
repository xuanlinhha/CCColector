package http;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

public class GifDownloader {
	private static String URL = "http://hvdic.thivien.net/anim/";
	private static String FOLDER = "/home/xuanlinhha/CCGif/";

	public static void download(String fileName) {
		DataInputStream di = null;
		FileOutputStream fo = null;
		byte[] b = new byte[1];
		try {
			// input
			URL url = new URL(URL + fileName);
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
			di = new DataInputStream(urlConnection.getInputStream());
			// output
			fo = new FileOutputStream(FOLDER + fileName);
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
