package com.huorehu;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		ForexAnalyzer fAnalyzer = new ForexAnalyzer();
		try {
			fAnalyzer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
