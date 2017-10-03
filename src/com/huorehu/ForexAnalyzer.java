package com.huorehu;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ForexAnalyzer {
	
	private static List<TradingDay> bankTradingDay = new ArrayList<>();
	
	public void start() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("eurusd_year.csv"));
		String[] nextLine;
		Candle cand;
		TradingDay trDay = new TradingDay();
		
		while((nextLine = reader.readNext()) != null) {
			if(nextLine != null) {
				cand = new Candle(nextLine);
				if (cand.getTime().equals("00:00") && trDay.getCandlesList().size() != 0) {
					bankTradingDay.add(trDay);
					trDay = new TradingDay();
				}
				
				trDay.addCandle(cand);
			}
		}
		
		System.out.println("Formed TradingDay");
		trDay = bankTradingDay.get(0);
		int i = 0;
		for (Candle c : trDay.getCandlesList()) {
			System.out.println("TickNumber " + i + " #" + c.getVolume());
			i++;
		}
	}

}
