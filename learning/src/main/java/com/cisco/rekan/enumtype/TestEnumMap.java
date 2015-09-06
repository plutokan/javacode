package com.cisco.rekan.enumtype;

import java.io.IOException;
import java.io.PrintStream;
import java.util.EnumMap;


public class TestEnumMap {
	enum AntStatus {
		  INITIALIZING,
		  COMPILING,
		  COPYING,
		  JARRING,
		  ZIPPING,
		  DONE,
		  ERROR
	}

	public static void testEnumMap(PrintStream out) throws IOException {
		  // Create a map with the key and a String message
		  EnumMap<AntStatus, String> antMessages =
		    new EnumMap<AntStatus, String>(AntStatus.class);
		  // Initialize the map
		  antMessages.put(AntStatus.INITIALIZING, "Initializing Ant...");
		  antMessages.put(AntStatus.COMPILING,    "Compiling Java classes...");
		  antMessages.put(AntStatus.COPYING,      "Copying files...");
		  antMessages.put(AntStatus.JARRING,      "JARring up files...");
		  antMessages.put(AntStatus.ZIPPING,      "ZIPping up files...");
		  antMessages.put(AntStatus.DONE,         "Build complete.");
		  antMessages.put(AntStatus.ERROR,        "Error occurred.");
		  // Iterate and print messages
		  for (AntStatus status : AntStatus.values() ) {
		    out.println("For status " + status + ", message is: " +
		                antMessages.get(status));
		  }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			testEnumMap(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
