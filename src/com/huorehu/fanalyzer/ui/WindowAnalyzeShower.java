package com.huorehu.fanalyzer.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import com.huorehu.fanalyzer.model.TradingDay;
import com.huorehu.fanalyzer.strategies.StrategyModel;

public class WindowAnalyzeShower implements AnalyzeShower {
	
	private int height;
	private int width;
	
	private TradingDay trDay;
	
	private int scaleStep;
	
	public WindowAnalyzeShower(final int height, final int width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public void showDayGraph(TradingDay trDay, StrategyModel strategy) {
		JFrame frame = new JFrame();
		frame.setSize(height, width);
		frame.add(new DayGraph());
		frame.setVisible(true);
		this.scaleStep = width / trDay.getCandlesList().size();
		this.trDay = trDay;
		
	}
	
	public class DayGraph extends Component {
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			drawGraph(g);
		}

		private void drawGraph(Graphics g) {
			Graphics2D gr2 = (Graphics2D) g;
			drawScale(gr2);
			drawGraphLine(gr2);
		}
		
		private void drawScale(Graphics2D gr2) {
			gr2.setColor(Color.blue);
			gr2.drawLine(0, height / 2, width, height / 2);
			gr2.drawLine(5, 0, 5, height);
		}
		
		private void drawGraphLine(Graphics2D gr2) {
			int lineSize = trDay.getCandlesList().size();
			int correctiveX = getPrice(0);
			int visualZero = height / 2;
			
			gr2.setColor(Color.red);
			
			for (int i = 0; i < lineSize; i++) {
				if (isLastPrice(i, lineSize)) {
					return;
				}
				gr2.drawLine(i + 5, visualZero + getPrice(i) - correctiveX, i + 5 + scaleStep, visualZero + getPrice(i + 1) - correctiveX);
			}
		}
		
		private boolean isLastPrice(int index, int amountPrices) {
			if (index == amountPrices - 1) {
				return true;
			}
			return false;
		}
		
		private int getPrice(int index) {
			return trDay.getCandlesList().get(index).getOpen();
		}

	}

}
