package negocio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurador {

	private static Configurador singleton;
	
	public static boolean STOPWORDS;
	public static boolean STEMMING;
	
	public static Configurador getInstance() {
		if(singleton == null)
			singleton = new Configurador();
		return singleton;
	}
	
	private Configurador() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			STOPWORDS = Boolean.parseBoolean(prop.getProperty("STOPWORDS"));
			STEMMING  = Boolean.parseBoolean(prop.getProperty("STEMMING"));

		} catch (IOException ex) {
//			ex.printStackTrace();
			System.out.println("OPS! " + Configurador.class);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
//					e.printStackTrace();
					System.out.println("OPS! " + Configurador.class);
				}
			}
		}

	}
	
	public void finalizar() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("STOPWORDS", ""+STOPWORDS);
			prop.setProperty("STEMMING", ""+STEMMING);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
//			io.printStackTrace();
			System.out.println("OPS! " + Configurador.class);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
//					e.printStackTrace();
					System.out.println("OPS! " + Configurador.class);
				}
			}

		}
	}

}
