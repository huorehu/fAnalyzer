package com.huorehu.fanalyzer.controller;

import java.io.IOException;

public interface DataLoader {
	
	void loadData(final String analyzedDataPath) throws IOException;

}
