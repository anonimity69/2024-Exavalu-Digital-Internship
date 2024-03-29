
package exavalu.com.utilities;
 


import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.Properties;

public class PropertyHandler {

	public static String getProperty(String key)

	{

    	 FileReader reader;

			String val = null;
 
		try {

			reader = new FileReader("src/config.properties");
			

			Properties prop = new Properties();

			try {

				prop.load(reader);

			} catch (IOException e) {

				e.printStackTrace();

			}

			val = prop.getProperty(key);
 
 
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}  
 
	

		return val;

	}

}
