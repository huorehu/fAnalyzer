package com.huorehu.fanalyzer.ui;

import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.StrategyModel;

public interface AnalyzeShower {
	
	void showDayGraph(TradingDay trDay, StrategyModel strategy);

}
