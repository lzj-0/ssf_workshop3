package com.example.workshop3;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// java -jar workshop3-0.0.1-SNAPSHOT.jar --dataDir=../src/main/resources/static/contact


@SpringBootApplication
public class Workshop3Application {

	public static String PATH;

	public static void main(String[] args) {


		// ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		// if (!cliOpts.containsOption("dataDir")) {
		// 	System.err.println("Input --dataDir=<dir>");
		// 	System.exit(-1);
		// }

		
		// String dir = cliOpts.getOptionValues("dataDir").get(0);
		// File directory = new File(dir);

		// if (!directory.exists()) {
		// 	directory.mkdir();
		// }

		// PATH = dir;

		SpringApplication.run(Workshop3Application.class, args);
		
	}

}
