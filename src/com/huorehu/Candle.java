package com.huorehu;

public class Candle {
	
	private static final int BEGIN_DATE_INDEX = 0;
	private static final int END_DATE_INDEX = 10;
	
	private static final int BEGIN_TIME_INDEX = 11;
	private static final int END_TIME_INDEX = 16;
	
	private static final int DOT_NUMBER_INDEX = 2;
	
	private final String date;
	private final String time;
	
	private final int open;
	private final int close;
	private final int high;
	private final int low;
	
	private final int volume;

	public Candle(final String[] timeframe) {
		this.date = timeframe[0].substring(BEGIN_DATE_INDEX, END_DATE_INDEX);
		this.time = timeframe[0].substring(BEGIN_TIME_INDEX, END_TIME_INDEX);
		this.open = Integer.parseInt(deleteDot(timeframe[1]));
		this.close = Integer.parseInt(deleteDot(timeframe[2]));
		this.high = Integer.parseInt(deleteDot(timeframe[3]));
		this.low = Integer.parseInt(deleteDot(timeframe[4]));
		this.volume = Integer.parseInt(timeframe[5]);
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
	
	private String deleteDot(String str) {
		return "1" + str.substring(DOT_NUMBER_INDEX);
	}
	
}
