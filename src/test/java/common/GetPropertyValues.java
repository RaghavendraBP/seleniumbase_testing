package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class GetPropertyValues {
	String result = "";
	InputStream inputStream;
	public String getPropValues(String read) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String value = "";
		
		 	try {
			Properties prop = new Properties();

			InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Config/config.properties");
				System.out.println("Path:" +System.getProperty("user.dir"));
			 prop.load(input);
			
			// get the property value and print it out
			value = prop.getProperty(read);
			
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
		return value;
	}
}
	