package com.huorehu.fanalyzer.strategies;

import com.huorehu.fanalyzer.model.Candle;
import com.huorehu.fanalyzer.model.TradingDay;

public class MyStrategy implements StrategyModel {
	
	private int startPrice = -1;
	private int maxPricePerControl = -1;
	private String timeMaxControl;
	private int minPricePerControl = -1;
	
	private int maxPricePerWork = -1;
	private int minPricePerWork = -1;
	
	private TradingDay trDay;
	
	@Override
	public void analyze(TradingDay trDay) {
		this.trDay = trDay;
	}
	
	public int getMaxPricePerControl() {
		if (maxPricePerControl != -1) {
			return maxPricePerControl;
		}
		int maxPrice = trDay.getCandlesList().get(0).getOpen();
		
		for (Candle candle : trDay.getCandlesList()) {
			if ((Integer.parseInt(candle.getTime().substring(0, 2)) < 17) && maxPrice < candle.getOpen()) {
				maxPrice = candle.getOpen();
				timeMaxControl = candle.getTime();
			}
		}
		return maxPrice;
	}
	
	public String getTimeMaxControl() {
		return timeMaxControl;
	}

	public int getMinPricePerControl() {
		if (minPricePerControl != -1) {
			return minPricePerControl;
		}
		return 0;
	}
	
	public int getMaxPricePerWork () {
		if (maxPricePerWork != -1) {
			return maxPricePerWork;
		}
		return 0;
	}
	
	public int getMinPricePerWork () {
		if (minPricePerWork != -1) {
			return minPricePerWork;
		}
		return 0;
	}
	
}
