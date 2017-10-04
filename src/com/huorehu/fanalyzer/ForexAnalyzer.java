package com.huorehu.fanalyzer;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.huorehu.fanalyzer.model.Candle;
import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.StrategyModel;
import com.opencsv.CSVReader;

public class ForexAnalyzer {
	
	private List<TradingDay> bankTradingDay = new ArrayList<>();
	private String analyzedData;
	private StrategyModel strategy;
	
	public ForexAnalyzer(final String analyzedData, final StrategyModel strategy) throws IOException {
		this.analyzedData = analyzedData;
		this.strategy = strategy;
		loadDataToBankTradingDay();
	}
	
	public void start() throws IOException {
		
		TradingDay trDay;
		
		System.out.println("Formed TradingDay");
		trDay = bankTradingDay.get(4);
		int i = 0;
		for (Candle c : trDay.getCandlesList()) {
			System.out.println("TickNumber " + i + " #" + c.getVolume());
			i++;
		}
		
		System.out.println(trDay.getCandleByTime("21:00").getOpen());
		
	}
	
	private void loadDataToBankTradingDay() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(analyzedData));
		String[] nextLine;
		Candle candle;
		TradingDay trDay = new TradingDay();
		
		while((nextLine = reader.readNext()) != null) {
			candle = new Candle(nextLine);
			if (trDay.getCandlesList().size() > 0 && checkFormationTradingDay(trDay, candle)) {
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
