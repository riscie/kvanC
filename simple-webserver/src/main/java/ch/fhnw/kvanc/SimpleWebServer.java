package ch.fhnw.kvanc;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

import ch.fhnw.kvanc.handler.EmailHttpHandler;
import ch.fhnw.kvanc.handler.IndexHttpHandler;
import ch.fhnw.kvanc.util.HttpLog;

public class SimpleWebServer {
	private static final String LOG_FILE = "http.log";
	private HttpServer server;

	public SimpleWebServer(File root, int port, boolean log) throws IOException {
		server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/", new IndexHttpHandler(root, log));
		server.createContext("/emails", new EmailHttpHandler(root, log));
		server.setExecutor(Executors.newCachedThreadPool());

		if (log)
			HttpLog.initializeLogger(new File(LOG_FILE));

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				shutdown();
			}
		});
	}

	public void start() {
		if (server != null)
			server.start();
	}

	public void shutdown() {
		if (server != null) {
			server.stop(0);
		}

		if (HttpLog.logger != null) {
			HttpLog.logger.close();
		}
	}
}
