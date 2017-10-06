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
	Graphics2D gr2;
	
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
		
		public Graphics2D getGraphics2D() {
			return gr2;
		}

		private void drawGraph(Graphics g) {
			gr2 = (Graphics2D) g;
			drawScale();
			drawGraphLine();
		}
		
		private void drawScale() {
			getGraphics2D().setColor(Color.blue);
			getGraphics2D().drawLine(0, height / 2, width, height / 2);
			getGraphics2D().drawLine(5, 0, 5, height);
		}
		
		private void drawGraphLine() {
			int lineSize = trDay.getCandlesList().size();
			int correctiveX = getPrice(0);
			int visualZero = height / 2;
			
			getGraphics2D().setColor(Color.red);
			
			for (int i = 0; i < lineSize; i++) {
				if (isLastPrice(i, lineSize)) {
					return;
				}
				getGraphics2D().drawLine(i + 5, visualZero + getPrice(i) - correctiveX, i + 5 + scaleStep, visualZero + getPrice(i + 1) - correctiveX);
			}
		}
		
		private boolean isLastPrice(int index, int amountPrices) {
			if (index == amountPrices - 1) {
				return true;
			}
			return false;
		}
		
		private int getPrice(int index) {
			checkControlPoint(index);
			return trDay.getCandlesList().get(index).getOpen();
		}

		private void checkControlPoint(int index) {
			if (trDay.getCandlesList().get(index).getTime().equals("17:00")
					|| trDay.getCandlesList().get(index).getTime().equals("18:00")
					|| trDay.getCandlesList().get(index).getTime().equals("19:00")
					|| trDay.getCandlesList().get(index).getTime().equals("21:00")) {
				getGraphics2D().drawLine(index, 0, index, height);
			}
			
		}

	}

}
