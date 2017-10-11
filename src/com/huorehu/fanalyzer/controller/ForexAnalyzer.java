package com.huorehu.fanalyzer.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.huorehu.fanalyzer.ui.AnalyzeShower;
import com.huorehu.fanalyzer.model.Candle;
import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.MyStrategy;
import com.huorehu.fanalyzer.strategies.StrategyModel;
import com.opencsv.CSVReader;

public class ForexAnalyzer implements DataLoader {
	
	private List<TradingDay> bankTradingDay = new ArrayList<>();
	private MyStrategy strategy;
	private AnalyzeShower shower;
	
	public ForexAnalyzer(final String analyzedDataPath, MyStrategy strategy, AnalyzeShower shower) throws IOException {
		this.strategy = strategy;
		this.shower = shower;
		loadData(analyzedDataPath);
	}
	
	public void start() throws IOException {
		
		TradingDay trDay = bankTradingDay.get(0);
		strategy.analyze(trDay);
		System.out.print("Current day " + trDay.getDate());
		System.out.println(" " + strategy.getMaxPricePerControl() + " " + strategy.getTimeMaxControl());
		shower.showDayGraph(trDay, strategy);
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	@Override
	public void loadData(final String analyzedDataPath) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(analyzedDataPath));
		String[] nextLine;
		Candle candle;
		TradingDay trDay = new TradingDay();
		
		while((nextLine = reader.readNext()) != null) {
			candle = new Candle(nextLine);
			if (trDay.getCandlesList().size() > 0 && checkFormationTradingDay(trDay, candle)) {
				trDay.initializeDayDate();
				strategy.analyze(trDay);
				bankTradingDay.add(trDay);
				trDay = new TradingDay();
			}
				
			trDay.addCandle(candle);
		}
	}
	
	private boolean checkFormationTradingDay(final TradingDay trDay, final Candle candle) {
		if (getCandleHours(trDay.getLastCandle()).equals("23") 
				&& getCandleHours(candle).equals("00")) {
			return true;
		}
		return false;
	}
	
	private String getCandleHours(final Candle candle) {
		return candle.getTime().substring(0, 2);
	}


}
