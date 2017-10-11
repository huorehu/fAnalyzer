package com.huorehu.fanalyzer.strategies;

import java.util.List;

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
		List<Candle> candleList = trDay.getCandlesList();
		for (Candle candle : candleList) {
			if (!candle.getTime().equals("17:00") && maxPrice < candle.getOpen()) {
				if (candle.getTime().equals("17:01")) {
					return maxPrice;
				}
				maxPrice = candle.getOpen();
				System.out.println(maxPrice + "%%%");
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
