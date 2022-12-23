package trab3.bubbles;

import trab3.bubbles.strategies.Strategy;

import java.io.IOException;

public interface Game {
	void setStrategy(Strategy s);
	void addListener(GameListener v);
	
	void select(int l, int c);
	void start();
	void stop();
	void help() throws IOException;
	
	int getNumberOfLines();
	int getNumberOfColumns();
	Score getScore();
	int getTime();
}
