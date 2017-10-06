package com.huorehu.fanalyzer.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.huorehu.fanalyzer.ui.AnalyzeShower;
import com.huorehu.fanalyzer.model.Candle;
import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.StrategyModel;
import com.opencsv.CSVReader;

public class ForexAnalyzer implements DataLoader {
	
	private List<TradingDay> bankTradingDay = new ArrayList<>();
	private StrategyModel strategy;
	private AnalyzeShower shower;
	
	public ForexAnalyzer(final String analyzedDataPath, final StrategyModel strategy, AnalyzeShower shower) throws IOException {
		this.strategy = strategy;
		this.shower = shower;
		loadData(analyzedDataPath);
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
		
		shower.showDayGraph(trDay, strategy);
		
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
