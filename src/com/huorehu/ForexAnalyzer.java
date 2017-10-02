package com.huorehu;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ForexAnalyzer {
	
	public void start() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("eurusd_year.csv"));
		String[] nextLine;
		while((nextLine = reader.readNext()) != null) {
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			if(nextLine != null) {
				Candle cand = new Candle(nextLine);
				System.out.printf("Time: %s# Volume sells: %s #%s #%d", cand.getTime(), cand.getVolume(), cand.getDate(), cand.getOpen());
				System.out.println();
			}
		}
	}

}
