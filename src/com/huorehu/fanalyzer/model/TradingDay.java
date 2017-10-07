package com.huorehu.fanalyzer.model;

import java.util.ArrayList;
import java.util.List;

public class TradingDay {
	
	private List<Candle> candlesList = new ArrayList<>();
	private String date;
	
	public void addCandle(final Candle candle) {
		candlesList.add(candle);
	}
	
	public Candle getCandleByTime(final String time) {
		for (Candle candle : candlesList) {
			if (candle.getTime().equals(time)) {
				return candle;
			}
		}
		return null;
	}
	
	public Candle getLastCandle() {
		if (candlesList.size() > 0) {
			return candlesList.get(candlesList.size() -1);
		}
		return null;
	}
	
	public List<Candle> getCandlesList() {
		return candlesList;
	}
	
	public void initializeDayDate() {
		this.date = candlesList.get(0).getDate();
	}
	
	public String getDate() {
		return date;
	}

}
