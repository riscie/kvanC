package ch.fhnw.kvanc.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import ch.fhnw.kvanc.util.HttpLog;

public class EmailHttpHandler implements HttpHandler {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd.MM.yyyy HH:mm:ss");
	private File root;
	private boolean log;

	public EmailHttpHandler(File root, boolean log) throws IOException {
		this.root = root.getCanonicalFile();
		this.log = log;

		if (!this.root.isDirectory()) {
			throw new IOException("No directory");
		}
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if (log) {
			HttpLog.logger.log("[" + simpleDateFormat.format(new Date()) + " "
					+ exchange.getRemoteAddress() + "] "
					+ exchange.getRequestMethod() + " "
					+ exchange.getRequestURI());
		}

		String method = exchange.getRequestMethod();
		if (!method.equals("GET")) {
			sendError(exchange, HttpURLConnection.HTTP_NOT_IMPLEMENTED,
					"Not Implemented");
			return;
		}

		URI uri = exchange.getRequestURI();
		String path = uri.getPath();

		File file = new File(root, URLDecoder.decode(path, "UTF-8"))
				.getCanonicalFile();

		if (!file.getCanonicalPath().startsWith(root.getCanonicalPath())) {
			sendError(exchange, HttpURLConnection.HTTP_FORBIDDEN, "Forbidden");
			return;
		}

		if (!file.exists()) {
			sendError(exchange, HttpURLConnection.HTTP_NOT_FOUND, "Not Found");
			return;
		}

		try (InputStream is = new FileInputStream(file);
				OutputStream os = exchange.getResponseBody()) {

			String contentType = URLConnection.getFileNameMap()
					.getContentTypeFor(file.getName());

			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			sendHeader(exchange, HttpURLConnection.HTTP_OK, contentType,
					file.length(), file.lastModified());

			byte[] buffer = new byte[8192];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}

			os.flush();
		} catch (FileNotFoundException e) {
			System.err.println(e);
			sendError(exchange, 400, "Is a directory");
		}
	}

	private void sendError(HttpExchange exchange, int status, String msg)
			throws IOException {

		sendHeader(exchange, status, "text/html", msg.length(),
				System.currentTimeMillis());
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(msg.getBytes());
			os.flush();
		}
	}

	private void sendHeader(HttpExchange exchange, int status,
			String contentType, long length, long time) throws IOException {

		Headers headers = exchange.getResponseHeaders();
		headers.add("Date", getTime(System.currentTimeMillis()));
		headers.add("Server", "com.sun.net.httpserver.HttpServer");
		headers.add("Content-Type", contentType);
		headers.add("Last-Modified", getTime(time));
		exchange.sendResponseHeaders(status, length);
	}

	private static String getTime(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return dateFormat.format(calendar.getTime());
	}
}