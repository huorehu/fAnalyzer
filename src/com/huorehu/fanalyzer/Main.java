package com.huorehu.fanalyzer;

import java.io.IOException;

import com.huorehu.fanalyzer.strategies.MyStrategy;
import com.huorehu.fanalyzer.strategies.StrategyModel;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		final String analyzedData = "eurusd_year.csv";
		
		StrategyModel strategy = new MyStrategy();
		ForexAnalyzer fAnalyzer = new ForexAnalyzer(analyzedData, strategy);
		try {
			fAnalyzer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
