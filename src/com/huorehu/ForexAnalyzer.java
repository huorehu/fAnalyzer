package com.huorehu;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class ForexAnalyzer {
	
	public void start() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("eurusd_year.csv"));
		String[] nextLine;
		while((nextLine = reader.readNext()) != null) {
			if(nextLine != null) {
				System.out.printf("Time: %s# Volume sells: %s", TicParser.getTime(nextLine[0]), nextLine[5]);
				System.out.println();
			}
		}
	}

}
