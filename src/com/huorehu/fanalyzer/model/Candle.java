package com.huorehu.fanalyzer.model;

public class Candle {
	
	private static final int BEGIN_DATE_INDEX = 0;
	private static final int END_DATE_INDEX = 10;
	
	private static final int BEGIN_TIME_INDEX = 11;
	private static final int END_TIME_INDEX = 16;
	
	private static final int DOT_NUMBER_INDEX = 2;
	
	private String date;
	private String time;
	
	private int open;
	private int close;
	private int high;
	private int low;
	
	private int volume;

	public Candle(String[] timeframe) {
		initializeCandle(timeframe);
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getOpen() {
		return open;
	}

	public int getClose() {
		return close;
	}

	public int getHigh() {
		return high;
	}

	public int getLow() {
		return low;
	}

	public int getVolume() {
		return volume;
	}
	
	private void initializeCandle(String[] timeframe) {
		this.date = timeframe[0].substring(BEGIN_DATE_INDEX, END_DATE_INDEX);
		this.time = timeframe[0].substring(BEGIN_TIME_INDEX, END_TIME_INDEX);
		this.open = formationPrice(timeframe[1]);
		this.close = formationPrice(timeframe[2]);
		this.high = formationPrice(timeframe[3]);
		this.low = formationPrice(timeframe[4]);
		this.volume = Integer.parseInt(timeframe[5]);
	}
	
	private int formationPrice(String str) {
		return correctQuantityChar("1" + str.substring(DOT_NUMBER_INDEX));
	}
	
	private int correctQuantityChar(String price) {
		int surplusChars = 0;
		if (price.length() < 6) {
			surplusChars = 6 - price.length();
		}
		return (Integer.parseInt(price) * (int)(Math.pow(10, surplusChars)) / 10);
	}
	
}
