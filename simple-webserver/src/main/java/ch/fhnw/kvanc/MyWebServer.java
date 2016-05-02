package ch.fhnw.kvanc;

import java.io.File;
import java.io.IOException;


public class MyWebServer {
	private static final String ROOT = "web";
	private static final int PORT = 8080;
	private static final boolean LOG = true;

	public static void main(String[] args) {
		try {
			SimpleWebServer server = new SimpleWebServer(new File(ROOT), PORT, LOG);
			server.start();
			System.out.println("MyWebServer started on port " + PORT + " ...");
			System.out.println("Serving HTML pages from directory '" + ROOT + "'.");
			System.out.println("Press RETURN or Ctrl+C to stop the server.");
			System.in.read();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
