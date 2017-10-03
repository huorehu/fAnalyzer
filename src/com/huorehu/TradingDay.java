package com.huorehu;

import java.util.ArrayList;
import java.util.List;

public class TradingDay {
	
	private List<Candle> candlesList = new ArrayList<>();
	
	public TradingDay() {
		
	}
	
	public void addCandle(final Candle candle) {
		candlesList.add(candle);
	}
	
	public Candle getCandle(int index) {
		return candlesList.get(index);
	}
	
	public List<Candle> getCandlesList() {
		return candlesList;
	}

}
