package main.utils;

import java.io.*;

import main.entity.statics.Tree;

//This class is a helper class in order to load files, parse different data types etc.
public class Utils {
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void writeObjectToFile(String path, Object obj) {
//		try (FileOutputStream fos = new FileOutputStream("path");
//			     ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//
//			    // write object to file
//			    oos.writeObject(obj);
//
//			} catch (IOException ex) {
//			    ex.printStackTrace();
//			}

		try {
			FileOutputStream f = new FileOutputStream(new File(path));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(obj);

			o.close();
			f.close();
			
			System.out.println("Saved Object: " + obj.getClass());
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

	}
	
	public static Object readObjectFromFile(String path) {
		try {
		FileInputStream fi = new FileInputStream(new File(path));
		ObjectInputStream oi = new ObjectInputStream(fi);

		// Read objects
		Object obj = null;
		
		try {
			obj = (Object)oi.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(pr1.toString());
//		System.out.println(pr2.toString());

		oi.close();
		fi.close();
		return obj;
		
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
		return null;
	}
	
	public static void writeStringToFile(String path, String value) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write(value);
			bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
