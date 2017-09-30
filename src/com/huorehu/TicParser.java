package com.huorehu;

public class TicParser {
	
	private static final int BEGIN_TIME_INDEX = 11;
	private static final int END_TIME_INDEX = 16;

	public static String getTime(String ticStr) {
		return ticStr.substring(BEGIN_TIME_INDEX, END_TIME_INDEX);
	}

}
