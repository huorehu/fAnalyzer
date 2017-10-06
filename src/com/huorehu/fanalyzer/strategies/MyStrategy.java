package com.huorehu.fanalyzer.strategies;

import com.huorehu.fanalyzer.model.TradingDay;

public class MyStrategy implements StrategyModel {
	
	private int startPrice;
	private int maxPricePerControl;
	private int minPricePerControl;
	
	private int maxPricePerWork;
	private int minPricePerWork;

	@Override
	public void analyze(TradingDay trDay) {
		// TODO
		
	}
	
	private int searchMaxPricePerControl() {
		return 0;
	}

	private int searchMinPricePerControl() {
		return 0;
	}
	
	private int searchMaxPricePerWork () {
		return 0;
	}
	
	private int searchMinPricePerWork () {
		return 0;
	}
	
	private int searchmaxPricePerControl() {
		return 0;
	}
	
}
