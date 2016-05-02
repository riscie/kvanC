package ch.fhnw.kvanc;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import processing.core.PApplet;

public class App extends PApplet {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Configuration config;
	
	private int x;
	private int y;

	
	private String hello;

	@Override
	public void settings() {
		try {
			config = new PropertiesConfiguration("application.properties");
			hello = config.getString("client.hello");
			x = 0;
			y = 0;
			logger.debug("configuration loaded");
			size(600, 600);
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
			exit();
		}
	}

	public void setup() {
		frameRate(60);
	}

	public void draw() {
		clear();
		text(hello, x++, (float) (Math.sin(x)*2+y++));
		x = x % width;
		y = y % height;
		
	}
	
	public static void main(String args[]) {
		String[] a = { "MAIN" };
		PApplet.runSketch(a, new App());
	}
}