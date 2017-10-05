package com.huorehu.fanalyzer.controller;

import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.StrategyModel;

public interface DayAnalyzer {
	
	void analyzeDay(TradingDay trDay, StrategyModel strategy);

}
