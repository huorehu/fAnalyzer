package com.huorehu.fanalyzer;

import java.io.IOException;

import com.huorehu.fanalyzer.ui.AnalyzeShower;
import com.huorehu.fanalyzer.ui.WindowAnalyzeShower;
import com.huorehu.fanalyzer.controller.ForexAnalyzer;
import com.huorehu.fanalyzer.strategies.MyStrategy;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		int windowHeight = 800;
		int windowWidth = 1600;
		String analyzedDataPath = "eurusd_year.csv";
		
		MyStrategy strategy = new MyStrategy();
		AnalyzeShower shower = new WindowAnalyzeShower(windowHeight, windowWidth);
		ForexAnalyzer fAnalyzer = new ForexAnalyzer(analyzedDataPath, strategy, shower);
		try {
			fAnalyzer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
